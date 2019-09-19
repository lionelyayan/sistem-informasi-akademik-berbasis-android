package com.example.smkhkandanghaur.FragmentDrawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class KehadiranFragment extends Fragment {

    private DatabaseReference databaseReference, durasiAbsen;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private PieChart pieChart;
    private TextView tvHadir, tvIzin, tvAlpa, tvSakit;

    Integer intSekarang, tambahAbsenHadir;
    String sStartPertama, sStopPertama, sStatusPertama,
            sStartKedua, sStopKedua, sStatusKedua, localTime;

    public KehadiranFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_kehadiran, container, false);
        getActivity().setTitle("Rekap Kehadiran");

        pieChart = view.findViewById(R.id.tampilchart);

        tvHadir = view.findViewById(R.id.hadir);
        tvIzin = view.findViewById(R.id.izin);
        tvAlpa = view.findViewById(R.id.alpa);
        tvSakit = view.findViewById(R.id.sakit);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        final FloatingActionButton FabPertama = view.findViewById(R.id.fabpertama);
        FabPertama.hide();
        final FloatingActionButton FabKeuda = view.findViewById(R.id.fabkedua);
        FabKeuda.hide();

        //tampil data
        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("users").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sHadir = dataSnapshot.child("kehadiran").child("hadir").getValue().toString();
                String sIzin = dataSnapshot.child("kehadiran").child("izin").getValue().toString();
                String sAlpa = dataSnapshot.child("kehadiran").child("alpa").getValue().toString();
                String sSakit = dataSnapshot.child("kehadiran").child("sakit").getValue().toString();
                sStatusPertama = dataSnapshot.child("kehadiran").child("absenpertama").getValue().toString();
                sStatusKedua = dataSnapshot.child("kehadiran").child("absenkedua").getValue().toString();

                //tampil di textview
                tvHadir.setText(" Hadir : "+sHadir);
                tvIzin.setText(" Izin : "+sIzin);
                tvAlpa.setText(" Alpa : "+sAlpa);
                tvSakit.setText(" Sakit : "+sSakit);

                //convert ke float
                float fHadir = Float.parseFloat(sHadir);
                float fIzin = Float.parseFloat(sIzin);
                float fAlpa = Float.parseFloat(sAlpa);
                float fSakit = Float.parseFloat(sSakit);

                //total
                float fTotal = fHadir + fIzin + fSakit + fAlpa;

                //rumus cari persen
                float perHadir = fHadir * 100 / fTotal;
                float perSakit = fSakit * 100 / fTotal;
                float perAlpa = fAlpa * 100 / fTotal;
                float perIzin = fIzin * 100 / fTotal;

                //tampil di chart bentuk persen
                List<PieEntry> value = new ArrayList<>();
                value.add(new PieEntry(perHadir, "Hadir"));
                value.add(new PieEntry(perSakit, "Sakit"));
                value.add(new PieEntry(perAlpa, "Alpa"));
                value.add(new PieEntry(perIzin, "Izin"));

                PieDataSet pieSet = new PieDataSet(value,"");
                PieData pieData = new PieData(pieSet);

                pieSet.setColors(ColorTemplate.MATERIAL_COLORS);
                pieSet.setSliceSpace(2f);

                //convert float ke integer
                int intTotalhari = (int) fTotal;
                pieChart.setCenterText("Hasil \n persentase dari \n"+ intTotalhari +" Hari");
                pieChart.setData(pieData);
                pieChart.animateY(1000);
                pieChart.setHoleRadius(35);
                pieChart.getDescription().setEnabled(false);
                pieChart.getLegend().setEnabled(false);

                //menghitung untuk tambah absen
                tambahAbsenHadir = 1 + Integer.parseInt(sHadir);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //cek jam untuk show/hide button
        durasiAbsen = FirebaseDatabase.getInstance().getReference().child("smkh").child("jam absen");
        durasiAbsen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sStartPertama = dataSnapshot.child("startabsenpertama").getValue().toString();
                sStopPertama = dataSnapshot.child("stopabsenpertama").getValue().toString();
                sStartKedua = dataSnapshot.child("absenkeduastart").getValue().toString();
                sStopKedua = dataSnapshot.child("absenkeduastop").getValue().toString();

                //zona waktu +7:00
                final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00"));
                Date currentLocalTime = cal.getTime();
                //format jam HHmm = JAMmenit
                DateFormat date = new SimpleDateFormat("HHmm");
                date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));

                //jam saat ini
                localTime = date.format(currentLocalTime);

                //convert jadi integer
                intSekarang = Integer.parseInt(localTime);
                Integer intStartPertama = Integer.parseInt(sStartPertama);
                Integer intStopPertama = Integer.parseInt(sStopPertama);
                Integer intStartKedua = Integer.parseInt(sStartKedua);
                Integer intStopKedua = Integer.parseInt(sStopKedua);

                //kondisi untuk button absen pertama hide/show
                if (intSekarang <= intStopPertama && intSekarang >= intStartPertama){
                    FabPertama.show();
                }else {
                    FabPertama.hide();
                }

                //kondisi untuk button absen kedua hide/show
                if (intSekarang <= intStopKedua && intSekarang >= intStartKedua){
                    FabKeuda.show();
                }else {
                    FabKeuda.hide();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //onclick Absen fabpertama
        FabPertama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sStatusPertama.equals("sudah absen") && sStatusKedua.equals("sudah absen")){
                    databaseReference.child("kehadiran").child("absenpertama").setValue("belum absen");
                    Toast.makeText(getActivity(), "Tekan sekali lagi untuk absen", Toast.LENGTH_SHORT).show();
                } else if (sStatusPertama.equals("belum absen") && sStatusKedua.equals("sudah absen")){
                    databaseReference.child("kehadiran").child("absenpertama").setValue("sudah absen");
                    databaseReference.child("kehadiran").child("absenkedua").setValue("belum absen");
                    Toast.makeText(getActivity(), "Absen pertama berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Absen pertama sudah dilakukan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //onclick Absen fabkedua
        FabKeuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sStatusPertama.equals("sudah absen") && sStatusKedua.equals("belum absen")){
                    Toast.makeText(getActivity(), "Absen kedua berhasil", Toast.LENGTH_SHORT).show();
                    databaseReference.child("kehadiran").child("absenkedua").setValue("sudah absen");
                    databaseReference.child("kehadiran").child("hadir").setValue(String.valueOf(tambahAbsenHadir));
                } else if (sStartPertama.equals("belum absen") && sStatusKedua.equals("sudah absen")){
                    databaseReference.child("kehadiran").child("absenpertama").setValue("sudah absen");
                    Toast.makeText(getActivity(), "Absen pertama kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Absen kedua sudah dilakukan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
