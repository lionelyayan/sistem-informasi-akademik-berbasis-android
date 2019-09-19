package com.example.smkhkandanghaur.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smkhkandanghaur.Modal.SiswaModal;
import com.example.smkhkandanghaur.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSiswaReadFragment extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_admin_siswa_read, container, false);

        recycler = view.findViewById(R.id.recyclersiswa);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                FirebaseRecyclerAdapter<SiswaModal, AdminSiswaReadFragment.SiswaModalHolder>
                        fireRecycAdapter = new FirebaseRecyclerAdapter<SiswaModal, AdminSiswaReadFragment.SiswaModalHolder>
                        (SiswaModal.class, R.layout.fragment_admin_siswa_read_recycler, AdminSiswaReadFragment.SiswaModalHolder.class, databaseReference) {
                    @Override
                    protected void populateViewHolder(AdminSiswaReadFragment.SiswaModalHolder viewHolder, final SiswaModal model, int position) {
                        viewHolder.setNama(model.getNAMA());
                        viewHolder.setNisn(model.getNISN());
                        viewHolder.setKelas(model.getKELAS());

                        //lihat detail item
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), AdmSiswaDetailActivity.class);
                                intent.putExtra("nama", model.getNAMA());
                                intent.putExtra("kelas", model.getKELAS());
                                intent.putExtra("nisn", model.getNISN());
                                intent.putExtra("jurusan", model.getJURUSAN());
                                intent.putExtra("alamat", model.getALAMAT());
                                intent.putExtra("lahir", model.getTEMPATLAHIR()+", "+model.getTANGGALLAHIR());
                                intent.putExtra("kelamin", model.getKELAMIN());
                                intent.putExtra("notelp", model.getNOTELP());
                                intent.putExtra("email", model.getEMAIL());
                                intent.putExtra("foto", model.getFOTO());
                                startActivity(intent);
                            }
                        });
                    }
                };
                recycler.setAdapter(fireRecycAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
    }

    public static class SiswaModalHolder extends RecyclerView.ViewHolder {
        View mView;

        public SiswaModalHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNama(String nama) {
            TextView tvNama = mView.findViewById(R.id.nama);
            tvNama.setText(nama);
        }

        public void setNisn(String nisn) {
            TextView tvNisn = mView.findViewById(R.id.nisn);
            tvNisn.setText(nisn);
        }

        public void setKelas(String kelas) {
            TextView tvKelas = mView.findViewById(R.id.kelas);
            tvKelas.setText(kelas);
        }
    }
}
