package com.sumod.interfaceapp.fragments;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.sumod.interfaceapp.R;


public class HomeFragmentWithTabs
        extends com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment {

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(getActivity())
                .addPage(R.string.proposals,
                        ProposalsFragment.newInstance("sampleString", "sampleString"))
                .addPage(R.string.leads,
                        LeadsFragment.newInstance("sampleString", "sampleString"))
                .addPage(R.string.refs, ReferencesFragments.newInstance("sampleString", "sampleString"));
    }

    @Override
    public boolean expandTabs() {
        return false;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
