package com.example.smkhkandanghaur;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.FragmentDrawer.KehadiranFragment;
import com.example.smkhkandanghaur.FragmentDrawer.LatihanFragment;
import com.example.smkhkandanghaur.FragmentDrawer.LokerFragment;
import com.example.smkhkandanghaur.FragmentDrawer.NilaiFragment;
import com.example.smkhkandanghaur.FragmentDrawer.PelajaranFragment;
import com.example.smkhkandanghaur.FragmentDrawer.PengumumanFragment;
import com.example.smkhkandanghaur.FragmentDrawer.ProfilFragment;
import com.example.smkhkandanghaur.Internet.ConnectionDetector;
import com.example.smkhkandanghaur.Modal.SiswaModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String userID;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private static final String TAG ="Profil";

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        ProfilFragment profilFragment = new ProfilFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, profilFragment);
        fragmentTransaction.commit();

        connectionDetector = new ConnectionDetector(Profil.this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("smkh").child("users");
        FirebaseUser usr = firebaseAuth.getCurrentUser();
        userID = usr.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tampilFotoProfil(dataSnapshot);
                tampilNamaProfil(dataSnapshot);
                tampilEmailProfil(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        setupFirebaseListener();
    }

    private void tampilFotoProfil(DataSnapshot dataSnapshot) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        ImageView imgView = header.findViewById(R.id.imageView);

        String sFoto = dataSnapshot.child(userID).child("foto").getValue().toString();
        if (sFoto.equals("2131165353")){
            imgView.setImageResource(R.drawable.lk);
        }else {
            Picasso.get().load(sFoto).into(imgView);
        }
    }

    private void tampilNamaProfil(DataSnapshot dataSnapshot) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView NamaProfil = header.findViewById(R.id.nama);

        SiswaModal informasi = new SiswaModal();
        informasi.setNAMA(dataSnapshot.child(userID).getValue(SiswaModal.class).getNAMA());
        NamaProfil.setText(informasi.getNAMA());
    }

    private void tampilEmailProfil(DataSnapshot dataSnapshot) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView EmailProfil = header.findViewById(R.id.email);

        SiswaModal informasi = new SiswaModal();
        informasi.setEMAIL(dataSnapshot.child(userID).getValue(SiswaModal.class).getEMAIL());
        EmailProfil.setText(informasi.getEMAIL());
    }

    private void setupFirebaseListener() {
        Log.d(TAG, "setupFirebaseListener: setting up the auth state listener.");
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usr = firebaseAuth.getCurrentUser();
                if (usr == null){
                    Log.d(TAG, "onAuthStateChanged: Sigged out");
                    Toast.makeText(Profil.this, "Anda berhasil logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Profil.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed(); return;
            } else {
                Toast.makeText(getBaseContext(), "Tekan sekali lagi untuk Keluar", Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.homeProfil) {
            ProfilFragment profilFragment = new ProfilFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, profilFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.pelajaran) {
            PelajaranFragment pelajaranFragment = new PelajaranFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, pelajaranFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.kehadiran) {
            KehadiranFragment kehadiranFragment = new KehadiranFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, kehadiranFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nilai) {
            NilaiFragment nilaiFragment = new NilaiFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, nilaiFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.latihansoal) {
            LatihanFragment latihanFragment = new LatihanFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, latihanFragment);
            fragmentTransaction.commit();
        }  else if (id == R.id.loker) {
            LokerFragment lokerFragment = new LokerFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, lokerFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.pengumuman) {
            PengumumanFragment pengumumanFragment = new PengumumanFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, pengumumanFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.keluar){
            firebaseAuth.signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
