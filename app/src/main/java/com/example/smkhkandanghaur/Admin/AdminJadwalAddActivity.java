package com.example.smkhkandanghaur.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.Modal.Jadwal;
import com.example.smkhkandanghaur.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminJadwalAddActivity extends AppCompatActivity {

    Button SubmitJadwalKelas_1, SubmitJadwalKelas_2, SubmitJadwalKelas_3;
    EditText SenSatu, SenDua, SenTiga, SenEmpat, SelSatu, SelDua, SelTiga, SelEmpat, RabSatu, RabDua, RabTiga, RabEmpat
            ,KamSatu, KamDua, KamTiga, KamEmpat, JumSatu, JumDua, JumTiga, JumEmpat, SabSatu, SabDua, SabTiga, SabEmpat;
    TextView Senin, Selasa, Rabu, Kamis, Jumat, Sabtu;

    AlertDialog.Builder alertDialogBuilder;
    ProgressDialog progressDialog;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_jadwal_add);

        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Senin = findViewById(R.id.senin);
        SenSatu = findViewById(R.id.sensatu);
        SenDua = findViewById(R.id.sendua);
        SenTiga = findViewById(R.id.sentiga);
        SenEmpat = findViewById(R.id.senempat);

        Selasa = findViewById(R.id.selasa);
        SelSatu = findViewById(R.id.selsatu);
        SelDua = findViewById(R.id.seldua);
        SelTiga = findViewById(R.id.seltiga);
        SelEmpat = findViewById(R.id.selempat);

        Rabu = findViewById(R.id.rabu);
        RabSatu = findViewById(R.id.rabsatu);
        RabDua = findViewById(R.id.rabdua);
        RabTiga = findViewById(R.id.rabtiga);
        RabEmpat = findViewById(R.id.rabempat);

        Kamis = findViewById(R.id.kamis);
        KamSatu = findViewById(R.id.kamsatu);
        KamDua = findViewById(R.id.kamdua);
        KamTiga = findViewById(R.id.kamtiga);
        KamEmpat = findViewById(R.id.kamempat);

        Jumat = findViewById(R.id.jumat);
        JumSatu = findViewById(R.id.jumsatu);
        JumDua = findViewById(R.id.jumdua);
        JumTiga = findViewById(R.id.jumtiga);
        JumEmpat = findViewById(R.id.jumempat);

        Sabtu= findViewById(R.id.sabtu);
        SabSatu = findViewById(R.id.sabsatu);
        SabDua = findViewById(R.id.sabdua);
        SabTiga = findViewById(R.id.sabtiga);
        SabEmpat = findViewById(R.id.sabempat);


        alertDialogBuilder = new AlertDialog.Builder(AdminJadwalAddActivity.this);
        progressDialog.dismiss();

        SubmitJadwalKelas_1 = findViewById(R.id.submitjadwalkelas_1);
        SubmitJadwalKelas_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Please wait...");
                progressDialog.show();

                progressDialog.dismiss();
                // set title dialog
                alertDialogBuilder.setTitle("Perhatian!");
                // set pesan dari dialog
                alertDialogBuilder.setMessage("Periksa kembali jadwal sebelum diinput!").setCancelable(true)
                        .setNegativeButton("Input langsung",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String sSenSatu = SenSatu.getText().toString();
                                String sSenDua = SenDua.getText().toString();
                                String sSenTiga = SenTiga.getText().toString();
                                String sSenEmpat = SenEmpat.getText().toString();
                                String sSenin = Senin.getText().toString();
                                String sSelSatu = SelSatu.getText().toString();
                                String sSelSDua = SelDua.getText().toString();
                                String sSelTiga = SelTiga.getText().toString();
                                String sSelEmpat = SelEmpat.getText().toString();
                                String sSelasa = Selasa.getText().toString();
                                String sRabSatu = RabSatu.getText().toString();
                                String sRabDua = RabDua.getText().toString();
                                String sRabTiga = RabDua.getText().toString();
                                String sRabEmpat = RabDua.getText().toString();
                                String sRabu = Rabu.getText().toString();
                                String sKamSatu = KamSatu.getText().toString();
                                String sKamDua = KamDua.getText().toString();
                                String sKamTiga = KamTiga.getText().toString();
                                String sKamEmpat = KamEmpat.getText().toString();
                                String sKamis = Kamis.getText().toString();
                                String sJumSatu = JumSatu.getText().toString();
                                String sJumDua = JumDua.getText().toString();
                                String sJumTiga = JumTiga.getText().toString();
                                String sJumEmpat = JumEmpat.getText().toString();
                                String sJumat = Jumat.getText().toString();
                                String sSabSatu = SabSatu.getText().toString();
                                String sSabDua = SabDua.getText().toString();
                                String sSabTiga = SabTiga.getText().toString();
                                String sSabEmpat = SabEmpat.getText().toString();
                                String sSabtu = Sabtu.getText().toString();

                                sumbitSenin_1(new Jadwal(sSenSatu, sSenDua, sSenTiga, sSenEmpat, sSenin));
                                sumbitSelasa_1(new Jadwal(sSelSatu, sSelSDua, sSelTiga, sSelEmpat, sSelasa));
                                sumbitRabu_1(new Jadwal(sRabSatu, sRabDua, sRabTiga, sRabEmpat, sRabu));
                                sumbitKamis_1(new Jadwal(sKamSatu, sKamDua, sKamTiga, sKamEmpat, sKamis));
                                sumbitJumat_1(new Jadwal(sJumSatu, sJumDua, sJumTiga, sJumEmpat, sJumat));
                                sumbitSabtu_1(new Jadwal(sSabSatu, sSabDua, sSabTiga, sSabEmpat, sSabtu));

                                progressDialog.dismiss();
                                Toast.makeText(AdminJadwalAddActivity.this, "Berhasil menabahkan jadwal kelas 1", Toast.LENGTH_SHORT).show();
                                SenSatu.getText().clear();
                                SenDua.getText().clear();
                                SenTiga.getText().clear();
                                SenEmpat.getText().clear();
                                SelSatu.getText().clear();
                                SelDua.getText().clear();
                                SelTiga.getText().clear();
                                SelEmpat.getText().clear();
                                RabSatu.getText().clear();
                                RabDua.getText().clear();
                                RabTiga.getText().clear();
                                RabEmpat.getText().clear();
                                KamSatu.getText().clear();
                                KamDua.getText().clear();
                                KamTiga.getText().clear();
                                KamEmpat.getText().clear();
                                JumSatu.getText().clear();
                                JumDua.getText().clear();
                                JumTiga.getText().clear();
                                JumEmpat.getText().clear();
                                SabSatu.getText().clear();
                                SabDua.getText().clear();
                                SabTiga.getText().clear();
                                SabEmpat.getText().clear();
                            }
                        })
                        .setPositiveButton("Periksa lagi",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                progressDialog.dismiss();
                                dialog.cancel();
                            }
                        });
                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();
                // menampilkan alert dialog
                alertDialog.show();
            }
        });

        SubmitJadwalKelas_2 = findViewById(R.id.submitjadwalkelas_2);
        SubmitJadwalKelas_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Please wait...");
                progressDialog.show();

                progressDialog.dismiss();
                // set title dialog
                alertDialogBuilder.setTitle("Perhatian!");
                // set pesan dari dialog
                alertDialogBuilder.setMessage("Periksa kembali jadwal sebelum diinput!").setCancelable(true)
                        .setNegativeButton("Input langsung",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String sSenSatu = SenSatu.getText().toString();
                                String sSenDua = SenDua.getText().toString();
                                String sSenTiga = SenTiga.getText().toString();
                                String sSenEmpat = SenEmpat.getText().toString();
                                String sSenin = Senin.getText().toString();
                                String sSelSatu = SelSatu.getText().toString();
                                String sSelSDua = SelDua.getText().toString();
                                String sSelTiga = SelTiga.getText().toString();
                                String sSelEmpat = SelEmpat.getText().toString();
                                String sSelasa = Selasa.getText().toString();
                                String sRabSatu = RabSatu.getText().toString();
                                String sRabDua = RabDua.getText().toString();
                                String sRabTiga = RabDua.getText().toString();
                                String sRabEmpat = RabDua.getText().toString();
                                String sRabu = Rabu.getText().toString();
                                String sKamSatu = KamSatu.getText().toString();
                                String sKamDua = KamDua.getText().toString();
                                String sKamTiga = KamTiga.getText().toString();
                                String sKamEmpat = KamEmpat.getText().toString();
                                String sKamis = Kamis.getText().toString();
                                String sJumSatu = JumSatu.getText().toString();
                                String sJumDua = JumDua.getText().toString();
                                String sJumTiga = JumTiga.getText().toString();
                                String sJumEmpat = JumEmpat.getText().toString();
                                String sJumat = Jumat.getText().toString();
                                String sSabSatu = SabSatu.getText().toString();
                                String sSabDua = SabDua.getText().toString();
                                String sSabTiga = SabTiga.getText().toString();
                                String sSabEmpat = SabEmpat.getText().toString();
                                String sSabtu = Sabtu.getText().toString();

                                sumbitSenin_2(new Jadwal(sSenSatu, sSenDua, sSenTiga, sSenEmpat, sSenin));
                                sumbitSelasa_2(new Jadwal(sSelSatu, sSelSDua, sSelTiga, sSelEmpat, sSelasa));
                                sumbitRabu_2(new Jadwal(sRabSatu, sRabDua, sRabTiga, sRabEmpat, sRabu));
                                sumbitKamis_2(new Jadwal(sKamSatu, sKamDua, sKamTiga, sKamEmpat, sKamis));
                                sumbitJumat_2(new Jadwal(sJumSatu, sJumDua, sJumTiga, sJumEmpat, sJumat));
                                sumbitSabtu_2(new Jadwal(sSabSatu, sSabDua, sSabTiga, sSabEmpat, sSabtu));

                                progressDialog.dismiss();
                                Toast.makeText(AdminJadwalAddActivity.this, "Berhasil menabahkan jadwal kelas 2", Toast.LENGTH_SHORT).show();
                                SenSatu.getText().clear();
                                SenDua.getText().clear();
                                SenTiga.getText().clear();
                                SenEmpat.getText().clear();
                                SelSatu.getText().clear();
                                SelDua.getText().clear();
                                SelTiga.getText().clear();
                                SelEmpat.getText().clear();
                                RabSatu.getText().clear();
                                RabDua.getText().clear();
                                RabTiga.getText().clear();
                                RabEmpat.getText().clear();
                                KamSatu.getText().clear();
                                KamDua.getText().clear();
                                KamTiga.getText().clear();
                                KamEmpat.getText().clear();
                                JumSatu.getText().clear();
                                JumDua.getText().clear();
                                JumTiga.getText().clear();
                                JumEmpat.getText().clear();
                                SabSatu.getText().clear();
                                SabDua.getText().clear();
                                SabTiga.getText().clear();
                                SabEmpat.getText().clear();
                            }
                        })
                        .setPositiveButton("Periksa lagi",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                progressDialog.dismiss();
                                dialog.cancel();
                            }
                        });
                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();
                // menampilkan alert dialog
                alertDialog.show();
            }
        });

        SubmitJadwalKelas_3 = findViewById(R.id.submitjadwalkelas_3);
        SubmitJadwalKelas_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Please wait...");
                progressDialog.show();

                progressDialog.dismiss();
                // set title dialog
                alertDialogBuilder.setTitle("Perhatian!");
                // set pesan dari dialog
                alertDialogBuilder.setMessage("Periksa kembali jadwal sebelum diinput!").setCancelable(true)
                        .setNegativeButton("Input langsung",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String sSenSatu = SenSatu.getText().toString();
                                String sSenDua = SenDua.getText().toString();
                                String sSenTiga = SenTiga.getText().toString();
                                String sSenEmpat = SenEmpat.getText().toString();
                                String sSenin = Senin.getText().toString();
                                String sSelSatu = SelSatu.getText().toString();
                                String sSelSDua = SelDua.getText().toString();
                                String sSelTiga = SelTiga.getText().toString();
                                String sSelEmpat = SelEmpat.getText().toString();
                                String sSelasa = Selasa.getText().toString();
                                String sRabSatu = RabSatu.getText().toString();
                                String sRabDua = RabDua.getText().toString();
                                String sRabTiga = RabDua.getText().toString();
                                String sRabEmpat = RabDua.getText().toString();
                                String sRabu = Rabu.getText().toString();
                                String sKamSatu = KamSatu.getText().toString();
                                String sKamDua = KamDua.getText().toString();
                                String sKamTiga = KamTiga.getText().toString();
                                String sKamEmpat = KamEmpat.getText().toString();
                                String sKamis = Kamis.getText().toString();
                                String sJumSatu = JumSatu.getText().toString();
                                String sJumDua = JumDua.getText().toString();
                                String sJumTiga = JumTiga.getText().toString();
                                String sJumEmpat = JumEmpat.getText().toString();
                                String sJumat = Jumat.getText().toString();
                                String sSabSatu = SabSatu.getText().toString();
                                String sSabDua = SabDua.getText().toString();
                                String sSabTiga = SabTiga.getText().toString();
                                String sSabEmpat = SabEmpat.getText().toString();
                                String sSabtu = Sabtu.getText().toString();

                                sumbitSenin_3(new Jadwal(sSenSatu, sSenDua, sSenTiga, sSenEmpat, sSenin));
                                sumbitSelasa_3(new Jadwal(sSelSatu, sSelSDua, sSelTiga, sSelEmpat, sSelasa));
                                sumbitRabu_3(new Jadwal(sRabSatu, sRabDua, sRabTiga, sRabEmpat, sRabu));
                                sumbitKamis_3(new Jadwal(sKamSatu, sKamDua, sKamTiga, sKamEmpat, sKamis));
                                sumbitJumat_3(new Jadwal(sJumSatu, sJumDua, sJumTiga, sJumEmpat, sJumat));
                                sumbitSabtu_3(new Jadwal(sSabSatu, sSabDua, sSabTiga, sSabEmpat, sSabtu));

                                progressDialog.dismiss();
                                Toast.makeText(AdminJadwalAddActivity.this, "Berhasil menabahkan jadwal kelas 3", Toast.LENGTH_SHORT).show();
                                SenSatu.getText().clear();
                                SenDua.getText().clear();
                                SenTiga.getText().clear();
                                SenEmpat.getText().clear();
                                SelSatu.getText().clear();
                                SelDua.getText().clear();
                                SelTiga.getText().clear();
                                SelEmpat.getText().clear();
                                RabSatu.getText().clear();
                                RabDua.getText().clear();
                                RabTiga.getText().clear();
                                RabEmpat.getText().clear();
                                KamSatu.getText().clear();
                                KamDua.getText().clear();
                                KamTiga.getText().clear();
                                KamEmpat.getText().clear();
                                JumSatu.getText().clear();
                                JumDua.getText().clear();
                                JumTiga.getText().clear();
                                JumEmpat.getText().clear();
                                SabSatu.getText().clear();
                                SabDua.getText().clear();
                                SabTiga.getText().clear();
                                SabEmpat.getText().clear();
                            }
                        })
                        .setPositiveButton("Periksa lagi",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                progressDialog.dismiss();
                                dialog.cancel();
                            }
                        });
                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();
                // menampilkan alert dialog
                alertDialog.show();
            }
        });
    }

    private void sumbitSenin_1(Jadwal senin) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_1").child("A_senin").setValue(senin)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitSelasa_1(Jadwal selasa) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_1").child("B_selasa").setValue(selasa)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitRabu_1(Jadwal rabu) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_1").child("C_rabu").setValue(rabu)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitKamis_1(Jadwal kamis) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_1").child("D_kamis").setValue(kamis)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitJumat_1(Jadwal jumat) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_1").child("E_jumat").setValue(jumat)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitSabtu_1(Jadwal sabtu) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_1").child("F_sabtu").setValue(sabtu)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }

    private void sumbitSenin_2(Jadwal senin) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_2").child("A_senin").setValue(senin)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitSelasa_2(Jadwal selasa) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_2").child("B_selasa").setValue(selasa)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitRabu_2(Jadwal rabu) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_2").child("C_rabu").setValue(rabu)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitKamis_2(Jadwal kamis) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_2").child("D_kamis").setValue(kamis)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitJumat_2(Jadwal jumat) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_2").child("E_jumat").setValue(jumat)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitSabtu_2(Jadwal sabtu) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_2").child("F_sabtu").setValue(sabtu)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }

    private void sumbitSenin_3(Jadwal senin) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_3").child("A_senin").setValue(senin)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitSelasa_3(Jadwal selasa) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_3").child("B_selasa").setValue(selasa)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitRabu_3(Jadwal rabu) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_3").child("C_rabu").setValue(rabu)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitKamis_3(Jadwal kamis) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_3").child("D_kamis").setValue(kamis)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitJumat_3(Jadwal jumat) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_3").child("E_jumat").setValue(jumat)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
    private void sumbitSabtu_3(Jadwal sabtu) {
        mDatabase.child("smkh").child("pelajaran").child("kelas_3").child("F_sabtu").setValue(sabtu)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
    }
}