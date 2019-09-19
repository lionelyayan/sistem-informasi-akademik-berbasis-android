package com.example.smkhkandanghaur.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.smkhkandanghaur.Admin.AdminLokerAddFragment;
import com.example.smkhkandanghaur.FragmentDrawer.LokerFragment;

public class AdminLokerAdapter extends FragmentStatePagerAdapter {

    int intTab;

    public AdminLokerAdapter(FragmentManager fm, int NomorTab){
        super(fm);
        this.intTab = NomorTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LokerFragment LF = new LokerFragment();
                return LF;
            case 1:
                AdminLokerAddFragment ALAF = new AdminLokerAddFragment();
                return ALAF;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return intTab;
    }
}
