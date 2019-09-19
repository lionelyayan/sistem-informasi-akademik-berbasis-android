package com.example.smkhkandanghaur;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smkhkandanghaur.Admin.AdminHomeActivity;
import com.example.smkhkandanghaur.Internet.ConnectionDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    ConnectionDetector connectionDetector;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progbar);
        progressBar.setVisibility(View.VISIBLE);

        connectionDetector = new ConnectionDetector(this);

        if(connectionDetector.isConnectied()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user != null) {
                        if (user.getUid().equals("vQmTfG5d5KQcRtQIiRU2yOOUOpK2")){
                            Intent i = new Intent(SplashActivity.this, AdminHomeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }else {
                            Intent i = new Intent(SplashActivity.this, Profil.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
//                        String userID = user.getUid();
  //                      DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("smkh").child("users").child(userID);
    //                    db.addValueEventListener(new ValueEventListener() {
      //                      @Override
        //                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          //                      String Tipe = dataSnapshot.child("tipe").getValue().toString();
            //                    if (Tipe.equals("siswa")){
              //                      Intent i = new Intent(SplashActivity.this, Profil.class);
                //                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  //                  startActivity(i);
                    //            } else {
                      //              Intent i = new Intent(SplashActivity.this, AdminHomeActivity.class);
                        //            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                          //          startActivity(i);
                            //    }
//                            }
  //                          @Override
    //                        public void onCancelled(@NonNull DatabaseError databaseError) {
      //                      }
        //                });

                    } else {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }
            }, 4000);
        }else {
            Toast.makeText(SplashActivity.this, "Tidak ada koneksi internet", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
