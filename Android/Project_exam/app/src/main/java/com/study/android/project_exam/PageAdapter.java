package com.study.android.project_exam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "lecture";
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Log.d(TAG,"position 0 : "+position);
                return new Fragment1();
            case 1:
                Log.d(TAG,"position 1 : "+position);
                return new Fragment2();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
