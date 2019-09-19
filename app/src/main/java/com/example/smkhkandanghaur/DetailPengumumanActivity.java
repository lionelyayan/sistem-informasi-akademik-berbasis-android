package com.example.smkhkandanghaur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailPengumumanActivity extends AppCompatActivity {

    ImageView PotoPengumuman;
    TextView IsiPengumuman, JudulPengumuman;

    String sJudulPgman, sIsiPgman, sFotoPgman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengumuman);

        PotoPengumuman = findViewById(R.id.detailpotopengumuman);
        JudulPengumuman = findViewById(R.id.detailjudulpengumuman);
        IsiPengumuman = findViewById(R.id.detailisipengumuman);

        sFotoPgman = getIntent().getStringExtra("foto");
        sJudulPgman = getIntent().getStringExtra("judul");
        sIsiPgman = getIntent().getStringExtra("isi");

        Picasso.get().load(sFotoPgman).into(PotoPengumuman);
        JudulPengumuman.setText(sJudulPgman);
        IsiPengumuman.setText(sIsiPgman);
    }
}
