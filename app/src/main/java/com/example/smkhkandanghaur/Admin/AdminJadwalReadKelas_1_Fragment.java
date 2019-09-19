package com.example.smkhkandanghaur.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smkhkandanghaur.FragmentDrawer.PelajaranFragment;
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
public class AdminJadwalReadKelas_1_Fragment extends Fragment {

    private RecyclerView recyclerView;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_admin_jadwal_read_kelas_1_, container, false);

        recyclerView = view.findViewById(R.id.recyclerpelajaran);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton BtnAdd = view.findViewById(R.id.add);
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdminJadwalAddActivity.class));
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("smkh").child("pelajaran").child("kelas_1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                JadwalPelajaranKelas();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;
    }

    private void JadwalPelajaranKelas() {
        FirebaseRecyclerAdapter<Jadwal, AdminJadwalReadKelas_1_Fragment.JadwalViewHolder> jadwal
                = new FirebaseRecyclerAdapter<Jadwal, AdminJadwalReadKelas_1_Fragment.JadwalViewHolder>
                (Jadwal.class, R.layout.fragment_admin_jadwal_read_kelas_1_recyclerview,
                        AdminJadwalReadKelas_1_Fragment.JadwalViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(AdminJadwalReadKelas_1_Fragment.JadwalViewHolder viewHolder, Jadwal model, int position) {
                viewHolder.setHARI(model.getHARI());
                viewHolder.setPERTAMA(model.getPERTAMA());
                viewHolder.setKEDUA(model.getKEDUA());
                viewHolder.setKETIGA(model.getKETIGA());
                viewHolder.setKEEMPAT(model.getKEEMPAT());
            }
        };
        recyclerView.setAdapter(jadwal);
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

    public interface OnFragmentInteractionListener {
    }
}
