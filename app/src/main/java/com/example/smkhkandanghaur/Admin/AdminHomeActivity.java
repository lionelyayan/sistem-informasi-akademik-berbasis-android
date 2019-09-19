package com.example.smkhkandanghaur.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smkhkandanghaur.LoginActivity;
import com.example.smkhkandanghaur.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Logout = findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(AdminHomeActivity.this, "Anda berhasil logout", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminHomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    public void siswa(View view) {
        startActivity(new Intent(AdminHomeActivity.this, AdminSiswaActivity.class));
    }

    public void jadwal(View view) {
        startActivity(new Intent(AdminHomeActivity.this, AdminJadwalActivity.class));
    }

    public void loker(View view) {
        startActivity(new Intent(AdminHomeActivity.this, AdminLokerActivity.class));
    }

    public void pengumuman(View view) {
        startActivity(new Intent(AdminHomeActivity.this, AdminPengumumanActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getApplication(), "Tekan sekali lagi untuk Keluar", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
