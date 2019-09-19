package com.example.smkhkandanghaur.FragmentDrawer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatihanFragment extends Fragment {

    DatabaseReference databaseReference;
    TextView Tanya1, Tanya2;
    RadioButton Jwb1a, Jwb1b, Jwb1c, Jwb1d, Jwb2a, Jwb2b, Jwb2c, Jwb2d;
    Button Submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_latihan, container, false);
        getActivity().setTitle("Latihan Soal (BETA)");

        Tanya1 = view.findViewById(R.id.tnya1);
        Jwb1a = view.findViewById(R.id.jwb1a);
        Jwb1b = view.findViewById(R.id.jwb1b);
        Jwb1c = view.findViewById(R.id.jwb1c);
        Jwb1d = view.findViewById(R.id.jwb1d);
        Tanya2 = view.findViewById(R.id.tnya2);
        Jwb2a = view.findViewById(R.id.jwb2a);
        Jwb2b = view.findViewById(R.id.jwb2b);
        Jwb2c = view.findViewById(R.id.jwb2c);
        Jwb2d = view.findViewById(R.id.jwb2d);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("elearning").child("matematika");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String No1 = dataSnapshot.child("no1").child("pertanyaan").getValue().toString();
                Tanya1.setText(No1);
                String No2 = dataSnapshot.child("no2").child("pertanyaan").getValue().toString();
                Tanya2.setText(No2);
                String sJwb1a = dataSnapshot.child("no1").child("jawabana").getValue().toString();
                String sJwb1b = dataSnapshot.child("no1").child("jawabanb").getValue().toString();
                String sJwb1c = dataSnapshot.child("no1").child("jawabanc").getValue().toString();
                String sJwb1d = dataSnapshot.child("no1").child("jawaband").getValue().toString();
                String sJwb2a = dataSnapshot.child("no2").child("jawabana").getValue().toString();
                String sJwb2b = dataSnapshot.child("no2").child("jawabanb").getValue().toString();
                String sJwb2c = dataSnapshot.child("no2").child("jawabanc").getValue().toString();
                String sJwb2d = dataSnapshot.child("no2").child("jawaband").getValue().toString();
                Jwb1a.setText(sJwb1a);
                Jwb1b.setText(sJwb1b);
                Jwb1c.setText(sJwb1c);
                Jwb1d.setText(sJwb1d);
                Jwb2a.setText(sJwb2a);
                Jwb2b.setText(sJwb2b);
                Jwb2c.setText(sJwb2c);
                Jwb2d.setText(sJwb2d);

                Submit = view.findViewById(R.id.submit);
                Submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Jwb1d.isChecked() && Jwb2d.isChecked()){
                            Toast.makeText(getActivity(), "Jawaban benar 2", Toast.LENGTH_SHORT)
                                    .show();
                        }else if (Jwb1d.isChecked() || Jwb2d.isChecked()){
                            Toast.makeText(getActivity(), "Jawaban benar 1", Toast.LENGTH_SHORT)
                                    .show();
                        }else{
                            Toast.makeText(getActivity(), "Jawaban salah semua", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;
    }

}
