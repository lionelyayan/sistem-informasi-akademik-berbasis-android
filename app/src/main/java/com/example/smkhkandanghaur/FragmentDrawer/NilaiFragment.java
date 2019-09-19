package com.example.smkhkandanghaur.FragmentDrawer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smkhkandanghaur.Modal.Nilai;
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
public class NilaiFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String userID;

    private TextView tvRata, tvJumlah, tvIndex;
    private RecyclerView recycler;

    public NilaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_nilai, container, false);
        getActivity().setTitle("Transkrip Nilai");

        tvRata = view.findViewById(R.id.rata);
        tvJumlah = view.findViewById(R.id.jumlah);
        tvIndex = view.findViewById(R.id.index);
        recycler = view.findViewById(R.id.recyclernilai);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("users").child(userID).child("nilai");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseRecyclerAdapter<Nilai, NilaiHolder> fireRecycAdapter = new FirebaseRecyclerAdapter<Nilai, NilaiHolder>
                        (Nilai.class, R.layout.fragment_nilai_recyclerview, NilaiHolder.class, databaseReference) {
                    @Override
                    protected void populateViewHolder(NilaiHolder viewHolder, Nilai model, int position) {
                        viewHolder.setmatpeL(model.getMATPEL());
                        viewHolder.setniLai(model.getNILAI());
                    }
                };
                recycler.setAdapter(fireRecycAdapter);

                //menghitung
                int rata, pel=12, jumlah=0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Nilai nilai = ds.getValue(Nilai.class);
                    Integer niLangka = Integer.valueOf(nilai.getNILAI());

                    //jumlah semua nilai
                    jumlah = (niLangka + jumlah);
                    tvJumlah.setText(String.valueOf(jumlah));
                    tvJumlah.setTextColor(Color.BLUE);

                    //rata-rata
                    rata = jumlah/pel;
                    tvRata.setText(String.valueOf(rata));

                    //index
                    if (rata < 60){
                        tvIndex.setText("E");
                        tvIndex.setTextColor(Color.RED);
                        tvRata.setTextColor(Color.RED);
                    }if (rata >= 60 && rata <= 69){
                        tvIndex.setText("D");
                        tvIndex.setTextColor(Color.RED);
                        tvRata.setTextColor(Color.RED);
                    }if (rata >= 70 && rata <= 79){
                        tvIndex.setText("C");
                        tvIndex.setTextColor(Color.YELLOW);
                        tvRata.setTextColor(Color.YELLOW);
                    }if (rata >= 80 && rata <= 89){
                        tvIndex.setText("B");
                        tvIndex.setTextColor(Color.GREEN);
                        tvRata.setTextColor(Color.GREEN);
                    }if (rata >= 90 && rata <= 95){
                        tvIndex.setText("A");
                        tvIndex.setTextColor(Color.GREEN);
                        tvRata.setTextColor(Color.GREEN);
                    }if (rata >= 96 && rata <= 100){
                        tvIndex.setText("A+");
                        tvIndex.setTextColor(Color.BLUE);
                        tvRata.setTextColor(Color.BLUE);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;
    }

    public static class NilaiHolder extends RecyclerView.ViewHolder {
        View mView;

        public NilaiHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setmatpeL(String matpel) {
            TextView tvmatpeL = mView.findViewById(R.id.mapel);
            tvmatpeL.setText(matpel);
        }

        public void setniLai(String nilai) {
            TextView tvniLai = mView.findViewById(R.id.nilaimapel);
            tvniLai.setText(nilai);
        }
    }
}
