package com.example.smkhkandanghaur.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.smkhkandanghaur.Admin.AdminPengumumanAddFragment;
import com.example.smkhkandanghaur.FragmentDrawer.PengumumanFragment;

public class AdminPengumumanAdapter extends FragmentStatePagerAdapter {

    int intTab;

    public AdminPengumumanAdapter(FragmentManager fm, int NomorTab){
        super(fm);
        this.intTab = NomorTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PengumumanFragment PF = new PengumumanFragment();
                return PF;
            case 1:
                AdminPengumumanAddFragment APAF = new AdminPengumumanAddFragment();
                return APAF;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return intTab;
    }
}
