package com.example.smkhkandanghaur.FragmentDrawer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.Modal.Jadwal;
import com.example.smkhkandanghaur.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PelajaranFragment extends Fragment {

    private RecyclerView recyclerView;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference, datajadwalkelas_1, datajadwalkelas_2, datajadwalkelas_3;
    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pelajaran, container, false);
        getActivity().setTitle("Jadwal Pelajaran");

        recyclerView = view.findViewById(R.id.recyclerpelajaran);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser usr = firebaseAuth.getCurrentUser();
        userID = usr.getUid();
        databaseReference = firebaseDatabase.getReference().child("smkh");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String siswaModal = dataSnapshot.child("users").child(userID).child("kelas").getValue().toString();
                if (siswaModal.equals("X A")){
                    datajadwalkelas_1 = firebaseDatabase.getReference().child("smkh").child("pelajaran").child("kelas_1");
                    datajadwalkelas_1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            JadwalPelajaranKelas_1();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

                else if (siswaModal.equals("XI B")){
                    datajadwalkelas_2 = firebaseDatabase.getReference().child("smkh").child("pelajaran").child("kelas_2");
                    datajadwalkelas_2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            JadwalPelajaranKelas_2();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

                else if (siswaModal.equals("XII C")){
                    datajadwalkelas_3 = firebaseDatabase.getReference().child("smkh").child("pelajaran").child("kelas_3");
                    datajadwalkelas_3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            JadwalPelajaranKelas_3();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

                else {
                    Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;
    }

    private void JadwalPelajaranKelas_1() {
        FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder> jadwalkelas_1 = new FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder>
                (Jadwal.class, R.layout.fragment_pelajaran_recyclerview, JadwalViewHolder.class, datajadwalkelas_1) {
            @Override
            protected void populateViewHolder(JadwalViewHolder viewHolder, Jadwal model, int position) {
                viewHolder.setHARI(model.getHARI());
                viewHolder.setPERTAMA(model.getPERTAMA());
                viewHolder.setKEDUA(model.getKEDUA());
                viewHolder.setKETIGA(model.getKETIGA());
                viewHolder.setKEEMPAT(model.getKEEMPAT());
            }
        };
        recyclerView.setAdapter(jadwalkelas_1);
    }

    private void JadwalPelajaranKelas_2() {
        FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder> jadwalkelas_2 = new FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder>
                (Jadwal.class, R.layout.fragment_pelajaran_recyclerview, JadwalViewHolder.class, datajadwalkelas_2) {
            @Override
            protected void populateViewHolder(JadwalViewHolder viewHolder, Jadwal model, int position) {
                viewHolder.setHARI(model.getHARI());
                viewHolder.setPERTAMA(model.getPERTAMA());
                viewHolder.setKEDUA(model.getKEDUA());
                viewHolder.setKETIGA(model.getKETIGA());
                viewHolder.setKEEMPAT(model.getKEEMPAT());
            }
        };
        recyclerView.setAdapter(jadwalkelas_2);
    }

    private void JadwalPelajaranKelas_3() {
        FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder> jadwalkelas_3 = new FirebaseRecyclerAdapter<Jadwal, JadwalViewHolder>
                (Jadwal.class, R.layout.fragment_pelajaran_recyclerview, JadwalViewHolder.class, datajadwalkelas_3) {
            @Override
            protected void populateViewHolder(JadwalViewHolder viewHolder, Jadwal model, int position) {
                viewHolder.setHARI(model.getHARI());
                viewHolder.setPERTAMA(model.getPERTAMA());
                viewHolder.setKEDUA(model.getKEDUA());
                viewHolder.setKETIGA(model.getKETIGA());
                viewHolder.setKEEMPAT(model.getKEEMPAT());
            }
        };
        recyclerView.setAdapter(jadwalkelas_3);
    }


    public static class JadwalViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public JadwalViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setPERTAMA(String PERTAMA){
            TextView pertamaXX = mView.findViewById(R.id.pertama);
            pertamaXX.setText(PERTAMA);
        }
        public void setKEDUA(String KEDUA){
            TextView keduaXX = mView.findViewById(R.id.kedua);
            keduaXX.setText(KEDUA);
        }
        public void setKETIGA(String KETIGA){
            TextView ketigaXX = mView.findViewById(R.id.ketiga);
            ketigaXX.setText(KETIGA);
        }
        public void setKEEMPAT(String KEEMPAT){
            TextView keempatXX = mView.findViewById(R.id.keempat);
            keempatXX.setText(KEEMPAT);
        }
        public void setHARI(String HARI){
            TextView hariXX = mView.findViewById(R.id.hari);
            hariXX.setText(HARI);
        }
    }
}
