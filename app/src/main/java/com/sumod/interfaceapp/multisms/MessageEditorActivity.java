package com.sumod.interfaceapp.multisms;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.asimov.app.AsimovActivity;
import com.sumod.interfaceapp.asimov.app.DetachableHandler;
import com.sumod.interfaceapp.asimov.parcelable.PairParcelable;
import com.sumod.interfaceapp.multisms.views.MessageEditText;

public class MessageEditorActivity extends AsimovActivity {

	private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		configureUI();
	}

	private void configureUI() {
		setContentView(R.layout.activity_message_editor);

		Intent intent = getIntent();
		String messageToBeSent = intent.getStringExtra("occupation") + " needed for "
				+ intent.getStringExtra("job") + ". Download Interface App now to know more.";

		((MessageEditText) findViewById(R.id.message)).setText(messageToBeSent);

		configureSendButton();
		configureAddNameButton();
		configureAddSurnameButton();
	}

	private void configureSendButton() {
		findViewById(R.id.sendMessage).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
					requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
					//After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
				} else {
					// Android version is lesser than 6.0 or the permission is already granted.
					submit(new ContactsFetcher(MessageEditorActivity.this));
				}

			}
		});
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions,
										   int[] grantResults) {
		if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// Permission is granted
				submit(new ContactsFetcher(MessageEditorActivity.this));
			} else {
				Toast.makeText(this, "Can't Refer until Permissions are granted", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void configureAddNameButton() {
		findViewById(R.id.addName).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MessageEditText) findViewById(R.id.message)).addName();
			}
		});
	}

	private void configureAddSurnameButton() {
		findViewById(R.id.addSurname).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MessageEditText) findViewById(R.id.message)).addSurname();
			}
		});
	}

	private static class ContactsFetcher extends DetachableHandler<MessageEditorActivity, Integer, SortedSet<Contact>> {

		private final ContentResolver contentResolver;

		/**
		 * The progress bar used to show the progress of the computation.
		 */

		private ProgressDialog progressBar;
		private Cursor contactsCursor;
		private int progress;
		private boolean cancelled;

		private ContactsFetcher(MessageEditorActivity context) {
			this.contentResolver = context.getContentResolver();
		}

		@Override
		protected void onAttach(MessageEditorActivity context) {
			super.onAttach(context);

			progressBar = new ProgressDialog(context);
			progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressBar.setIndeterminate(false);
			progressBar.setMessage("Loading contacts");
			progressBar.setProgress(progress);

			if (contactsCursor != null)
				progressBar.setMax(contactsCursor.getCount());

			progressBar.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface bar) {
					// if the user cancels the progress bar (for instance through the back key)
					// the task gets cancelled as well
					cancelled = true;
				}
			});

			progressBar.show();
		}

		@Override
		protected void onDetach() {
			progressBar.dismiss();
		}

		@Override
		protected SortedSet<Contact> run() {
	        contactsCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
			progressBar.setMax(contactsCursor.getCount());
			SortedSet<Contact> contacts = new TreeSet<Contact>();

	        while (contactsCursor.moveToNext() && !cancelled) {
	        	notifyProgress(Integer.valueOf(++progress));
	        	addContactsFor(contactsCursor, contacts);
	        }

	        contactsCursor.close();

	        return contacts;
		}

		@Override
		protected void onProgressUpdate(MessageEditorActivity context, Integer progress) {
			progressBar.setProgress(progress);
		}

		@Override
		protected void onPostExecute(MessageEditorActivity context, SortedSet<Contact> result) {
			progressBar.dismiss();
			if (!cancelled) {
				MessageEditText.Message message = ((MessageEditText) context.findViewById(R.id.message)).getMessage();
				context.call(ContactSelectionActivity.class, new PairParcelable<SortedSet<Contact>, MessageEditText.Message>(result, message));
			}
		}

		private void addContactsFor(Cursor contactsCursor, Set<Contact> contacts) {
			String contactName = null, contactSurname = null;
	        String contactID = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts._ID));

	        // we determine the name and surname of the contact
	        String structuredNameWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"; 
	        String[] structuredNameWhereParams = new String[]{ contactID, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE}; 
	        Cursor names = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, structuredNameWhere, structuredNameWhereParams, null); 

	        if (names.moveToFirst()) { 
	            contactName = names.getString(names.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
	            contactSurname = names.getString(names.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
	        }

	        names.close();

	        if (contactName == null || contactSurname == null)
	        	return;

	        // we iterate over all phone numbers of the contact
	    	Cursor numbers = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
	       		ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { contactID }, null);

	    	if (numbers != null)
	            while (numbers.moveToNext()) {
	            	boolean isMobile = numbers.getInt(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)) == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
	            	String contactPhone = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	            	contacts.add(new Contact(isMobile, contactName, contactSurname, contactPhone));
	            }

	    	numbers.close();
		}
	}
}