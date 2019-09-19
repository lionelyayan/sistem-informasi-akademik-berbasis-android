package com.example.smkhkandanghaur.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.smkhkandanghaur.Admin.AdminSiswaAddFragment;
import com.example.smkhkandanghaur.Admin.AdminSiswaReadFragment;

public class AdminSiswaAdapter extends FragmentStatePagerAdapter {

    int intTab;

    public AdminSiswaAdapter(FragmentManager fm, int NomorTab){
        super(fm);
        this.intTab = NomorTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AdminSiswaReadFragment ASRF = new AdminSiswaReadFragment();
                return ASRF;
            case 1:
                AdminSiswaAddFragment ASAF = new AdminSiswaAddFragment();
                return ASAF;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return intTab;
    }
}
