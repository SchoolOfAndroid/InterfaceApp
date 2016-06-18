package com.sumod.interfaceapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blunderer.materialdesignlibrary.activities.ViewPagerActivity;
import com.blunderer.materialdesignlibrary.fragments.ListViewFragment;
import com.blunderer.materialdesignlibrary.fragments.ScrollViewFragment;
import com.blunderer.materialdesignlibrary.fragments.ViewPagerFragment;
import com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerStyleHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.interfaces.NavigationDrawer;
import com.blunderer.materialdesignlibrary.models.Account;
import com.sumod.interfaceapp.fragments.HomeFragment;
import com.sumod.interfaceapp.fragments.HomeFragmentWithTabs;
import com.sumod.interfaceapp.fragments.MessagesFragment;


public class NavigationDrawerActivity extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity {


    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    public NavigationDrawerStyleHandler getNavigationDrawerStyleHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return new NavigationDrawerAccountsHandler(this)
                .enableSmallAccountsLayout()
                .addAccount("Blunderer", "blundererandroid@gmail.com",
                        R.drawable.profile1, R.drawable.profile1_background)
                .addAccount("Blunderer's cat", "cat@gmail.com",
                        R.drawable.profile2, R.drawable.profile2_background)
                .addAccount("Blunderer's dog", "dog@gmail.com",
                        R.drawable.profile3, R.color.cyan)
                .addAccount("Blunderer's monkey", "monkey@gmail.com",
                        R.drawable.profile4, R.color.gray);
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return new NavigationDrawerAccountsMenuHandler(this)
                .addAddAccount(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    }

                })
                .addManageAccounts(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    }

                });
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(getApplicationContext())
                .addItem(R.string.home,new HomeFragment())
                .addItem(R.string.business, new HomeFragmentWithTabs())
                .addItem(R.string.messages, new MessagesFragment());
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return null;
    }

    @Override
    public boolean overlayActionBar() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return true;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 0;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }
    
}
