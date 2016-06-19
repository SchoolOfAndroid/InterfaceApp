package module.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sumod.interfaceapp.R;

import java.util.List;

import module.base.ApplicationPrefs;
import module.bean.Message;

/**
 * Created by Bharti on 6/6/2016.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    Activity activity;
    private List<Message> list;

    public MessageAdapter(List<Message> list, Context context,Activity mactivity) {
        this.list = list;
        this.context = context;
        this.activity=mactivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_msg, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ViewHolder VHitem = (ViewHolder) holder;

        VHitem.mnickname.setText(list.get(position).nickname);
        VHitem.mMsg.setText(list.get(position).text);

        if (list.get(position).nickname.equals(ApplicationPrefs.getInstance(context).getNickName())) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) VHitem.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            VHitem.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) VHitem.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            VHitem.content.setLayoutParams(lp);

            layoutParams = (LinearLayout.LayoutParams) VHitem.mnickname.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((ViewHolder) holder).mnickname.setLayoutParams(layoutParams);
            if (((ViewHolder) holder).mMsg != null) {
                VHitem.contentWithBG.setBackgroundResource(R.drawable.incoming_message_bg);
                layoutParams = (LinearLayout.LayoutParams) VHitem.mMsg.getLayoutParams();
                layoutParams.gravity = Gravity.RIGHT;
                VHitem.mMsg.setLayoutParams(layoutParams);
            } else {
                VHitem.contentWithBG.setBackgroundResource(android.R.color.transparent);
            }
        } else {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) VHitem.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            VHitem.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) VHitem.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            VHitem.content.setLayoutParams(lp);

            layoutParams = (LinearLayout.LayoutParams) VHitem.mnickname.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            VHitem.mnickname.setLayoutParams(layoutParams);

            if (VHitem.mMsg != null) {
                VHitem.contentWithBG.setBackgroundResource(R.drawable.outgoing_message_bg);
                layoutParams = (LinearLayout.LayoutParams) VHitem.mMsg.getLayoutParams();
                layoutParams.gravity = Gravity.LEFT;
                VHitem.mMsg.setLayoutParams(layoutParams);
            } else {
                VHitem.contentWithBG.setBackgroundResource(android.R.color.transparent);
            }
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mnickname,mMsg;
        public LinearLayout contentWithBG,content;

        public ViewHolder(View v) {
            super(v);
            mnickname = (TextView) v.findViewById(R.id.mnickname);
            mMsg = (TextView) v.findViewById(R.id.mMsg);
            contentWithBG= (LinearLayout) v.findViewById(R.id.contentWithBackground);
            content= (LinearLayout) v.findViewById(R.id.content);
        }

    }
}
