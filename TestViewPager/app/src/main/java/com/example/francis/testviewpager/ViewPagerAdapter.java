package com.example.francis.testviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Francis on 2017-05-07.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private static int NUM_ITEMS = 2;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return EcranPrincipal.newInstance(0, "EcranPrincipal");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return Colnet.newInstance(1, "Col.net");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Vie";
        else
            return "Col.net";
    }


}
