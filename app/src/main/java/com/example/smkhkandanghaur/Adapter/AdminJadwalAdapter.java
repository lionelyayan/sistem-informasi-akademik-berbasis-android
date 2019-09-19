package com.example.smkhkandanghaur.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.smkhkandanghaur.Admin.AdminJadwalReadKelas_1_Fragment;
import com.example.smkhkandanghaur.Admin.AdminJadwalReadKelas_2_Fragment;
import com.example.smkhkandanghaur.Admin.AdminJadwalReadKelas_3_Fragment;

public class AdminJadwalAdapter extends FragmentStatePagerAdapter {

    int intTab;

    public AdminJadwalAdapter(FragmentManager fm, int NomorTab){
        super(fm);
        this.intTab = NomorTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AdminJadwalReadKelas_1_Fragment AJRKF1 = new AdminJadwalReadKelas_1_Fragment();
                return AJRKF1;
            case 1:
                AdminJadwalReadKelas_2_Fragment AJRKF2 = new AdminJadwalReadKelas_2_Fragment();
                return AJRKF2;
            case 2:
                AdminJadwalReadKelas_3_Fragment AJRKF3 = new AdminJadwalReadKelas_3_Fragment();
                return AJRKF3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return intTab;
    }
}
