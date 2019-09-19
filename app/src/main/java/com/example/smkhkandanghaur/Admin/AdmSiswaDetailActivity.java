package com.example.smkhkandanghaur.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smkhkandanghaur.R;
import com.squareup.picasso.Picasso;

public class AdmSiswaDetailActivity extends AppCompatActivity {

    TextView Nama, Kelas, Nisn, Jurusan, Alamat, Lahir, Kelamin, Notelp, Email;
    String sNama, sKelas, sNisn, sJurusan, sAlamat, sLahir, sKelamin, sNotelp, sEmail, sFoto;
    ImageView Foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_siswa_detail);

        Nama = findViewById(R.id.nama);
        Kelas = findViewById(R.id.kelas);
        Nisn = findViewById(R.id.nisn);
        Jurusan = findViewById(R.id.jurusan);
        Alamat = findViewById(R.id.alamat);
        Lahir = findViewById(R.id.lahir);
        Kelamin = findViewById(R.id.kelamin);
        Notelp = findViewById(R.id.notelp);
        Email = findViewById(R.id.email);
        Foto = findViewById(R.id.foto);

        sNama = getIntent().getStringExtra("nama");
        sKelas = getIntent().getStringExtra("kelas");
        sNisn = getIntent().getStringExtra("nisn");
        sJurusan = getIntent().getStringExtra("jurusan");
        sAlamat = getIntent().getStringExtra("alamat");
        sLahir = getIntent().getStringExtra("lahir");
        sKelamin = getIntent().getStringExtra("kelamin");
        sNotelp = getIntent().getStringExtra("notelp");
        sEmail = getIntent().getStringExtra("email");
        sFoto = getIntent().getStringExtra("foto");

        if (sFoto.equals("2131165353")){
            Foto.setImageResource(R.drawable.lk);
        }else {
            Picasso.get().load(sFoto).into(Foto);
        }

        Nama.setText(sNama);
        Kelas.setText(sKelas);
        Nisn.setText(sNisn);
        Jurusan.setText(sJurusan);
        Alamat.setText(sAlamat);
        Lahir.setText(sLahir);
        Kelamin.setText(sKelamin);
        Notelp.setText(sNotelp);
        Email.setText(sEmail);
    }
}
