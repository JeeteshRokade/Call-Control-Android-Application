package com.example.felix.callcontrol.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.felix.callcontrol.Fragment1;
import com.example.felix.callcontrol.Fragment2;
import com.example.felix.callcontrol.Fragment3;
import com.example.felix.callcontrol.Fragment4;

/**
 * Created by Felix on 10/15/2016.
 */

public class FragmentsAdapter extends FragmentStatePagerAdapter {


    int mNumOfTabs;

    public FragmentsAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment1 tab1 = new Fragment1();
                return tab1;
            case 1:
                Fragment2 tab2 = new Fragment2();
                return tab2;
            case 2:
                Fragment3 tab3 = new Fragment3();
                return tab3;
            case 3:
                Fragment4 tab4 = new Fragment4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Calls";
            case 1:
                return "Contacts";
            case 2:
                return "Search";
            case 3:
                return "Block";
            default:
                return "";

        }
    }
}
