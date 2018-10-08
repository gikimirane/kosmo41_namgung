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
        Log.d(TAG,"탭 개수 : "+mNumOfTabs);
    }
    @Override
    public int getItemPosition(Object object) {
        int position = 0;

        if (object instanceof Fragment1) {
            position = 0;
        } else if (object instanceof Fragment2) {
            position = 1;
        }else if(object instanceof Fragment3){
            position = 2;
        }
        Log.d(TAG,"나의 포지선 : "+position);
        return (position >= 0) ? position : POSITION_NONE;
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
            case 2:
                Log.d(TAG,"Position 2 : "+position);
                return new Fragment3();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
