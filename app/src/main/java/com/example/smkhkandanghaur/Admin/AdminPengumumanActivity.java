package com.example.smkhkandanghaur.Admin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.smkhkandanghaur.Adapter.AdminJadwalAdapter;
import com.example.smkhkandanghaur.Adapter.AdminPengumumanAdapter;
import com.example.smkhkandanghaur.FragmentDrawer.PengumumanFragment;
import com.example.smkhkandanghaur.R;

public class AdminPengumumanActivity extends AppCompatActivity implements PengumumanFragment.OnFragmentInteractionListener
        , AdminPengumumanAddFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pengumuman);

        final TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("List Pengumuman"));
        tabLayout.addTab(tabLayout.newTab().setText("Tambah Pengumuman"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final AdminPengumumanAdapter adapter = new AdminPengumumanAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
