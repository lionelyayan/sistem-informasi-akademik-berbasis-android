package com.example.smkhkandanghaur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smkhkandanghaur.Admin.AdminHomeActivity;
import com.example.smkhkandanghaur.Internet.ConnectionDetector;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEMAIL, editTextPASSWORD;
    Button BtnLogin;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEMAIL = findViewById(R.id.email);
        editTextPASSWORD = findViewById(R.id.password);
        BtnLogin = findViewById(R.id.btnlogin);
        connectionDetector = new ConnectionDetector(LoginActivity.this);
        progressDialog = new ProgressDialog(this);

        progressDialog.dismiss();
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (connectionDetector.isConnectied()) {
                    final String sEmail = editTextEMAIL.getText().toString();
                    final String sPassword = editTextPASSWORD.getText().toString();

                    if (sEmail.isEmpty()) {
                        editTextEMAIL.setError("Email harus diisi");
                        editTextEMAIL.requestFocus();
                        return;
                    } else if (sPassword.isEmpty()) {
                        editTextPASSWORD.setError("Passowrd harus diisi");
                        editTextPASSWORD.requestFocus();
                        return;
                    } else {
                        progressDialog.setTitle("Please wait...");
                        progressDialog.show();
                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signInWithEmailAndPassword(sEmail, sPassword)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if(sEmail.equals("admin@gmail.com")){
                                                progressDialog.dismiss();
                                                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }else {
                                                progressDialog.dismiss();
                                                Intent intent = new Intent(LoginActivity.this, Profil.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
//                                            final FirebaseUser user = firebaseAuth.getCurrentUser();
  //                                          String userID = user.getUid();
    //                                        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("users").child(userID);
      //                                      databaseReference.addValueEventListener(new ValueEventListener() {
        //                                        @Override
          //                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //                                        String userTipe = dataSnapshot.child("tipe").getValue().toString();
              //                                      if (userTipe.equals("guru")) {
                //                                        progressDialog.dismiss();
                  //                                      Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                    //                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      //                                  startActivity(intent);
                        //                            }
                          //                          if (userTipe.equals("siswa")) {
                            //                            progressDialog.dismiss();
                              //                          Intent intent = new Intent(LoginActivity.this, Profil.class);
                                //                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                  //                      startActivity(intent);
                                    //                }
                                      //          }
                                        //        @Override
                                          //      public void onCancelled(@NonNull DatabaseError databaseError) {
                                            //        Toast.makeText(LoginActivity.this, "Masalah database", Toast.LENGTH_SHORT).show();
                                              //  }
//                                            });
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT)
                                                    .show();
                                            progressDialog.dismiss();
                                        }
                                        // ...
                                    }
                                });
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Tidak ada koneksi internet", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Tekan sekali lagi untuk Keluar", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
