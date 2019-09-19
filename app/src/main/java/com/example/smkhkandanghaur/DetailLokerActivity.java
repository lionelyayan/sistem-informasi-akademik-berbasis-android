package com.example.smkhkandanghaur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailLokerActivity extends AppCompatActivity {

    ImageView PotoLoker;
    TextView IsiLoker, PerusahaanLoker, Lokasiloker;

    String sPerusahaanLoker, sIsiLoker, sLokasiloker;
    String sPotoLoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loker);

        PotoLoker = findViewById(R.id.poto);
        PerusahaanLoker = findViewById(R.id.detailperusahaanloker);
        Lokasiloker = findViewById(R.id.detaillokasiloker);
        IsiLoker = findViewById(R.id.detailisiloker);

        sPotoLoker = getIntent().getStringExtra("poto");
        sPerusahaanLoker = getIntent().getStringExtra("perusahaan");
        sIsiLoker = getIntent().getStringExtra("isiloker");
        sLokasiloker = getIntent().getStringExtra("lokasi");

        Picasso.get().load(sPotoLoker).into(PotoLoker);
        PerusahaanLoker.setText(sPerusahaanLoker);
        Lokasiloker.setText(sLokasiloker);
        IsiLoker.setText(sIsiLoker);
    }
}
