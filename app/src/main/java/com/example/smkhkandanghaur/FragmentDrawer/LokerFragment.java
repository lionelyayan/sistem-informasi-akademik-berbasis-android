package com.example.smkhkandanghaur.FragmentDrawer;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.DetailLokerActivity;
import com.example.smkhkandanghaur.DetailPengumumanActivity;
import com.example.smkhkandanghaur.Modal.Loker;
import com.example.smkhkandanghaur.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class LokerFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_loker, container, false);
        getActivity().setTitle("Lowongan Pekerjaan");

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("loker");

        recyclerView = view.findViewById(R.id.recyclerloker);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Loker, LokerViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter <Loker, LokerViewHolder>
                        (Loker.class, R.layout.fragment_loker_recyclerview, LokerViewHolder.class, databaseReference) {
                    @Override
                    protected void populateViewHolder(final LokerViewHolder viewHolder, final Loker model, final int position) {
                        viewHolder.setPerusahaan(model.getPERUSAHAAN());
                        viewHolder.setLokasi(model.getLOKASI());
                        viewHolder.setIsiloker(model.getISILOKER());

                        //lihat detail item
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), DetailLokerActivity.class);
                                intent.putExtra("poto", model.getPOTO());
                                intent.putExtra("perusahaan", model.getPERUSAHAAN());
                                intent.putExtra("lokasi", model.getLOKASI());
                                intent.putExtra("isiloker", model.getISILOKER());
                                startActivity(intent);
                            }
                        });

                        //delete item
                        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                if (userId.equals("vQmTfG5d5KQcRtQIiRU2yOOUOpK2")){
                                    final Dialog dialog = new Dialog(getActivity());
                                    dialog.setContentView(R.layout.dialog);
                                    dialog.show();

                                    //delete
                                    Button btnDelete = dialog.findViewById(R.id.delete);
                                    btnDelete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            DatabaseReference delete = viewHolder.dbDelete = getRef(position);
                                            delete.removeValue();
                                            dialog.dismiss();
                                            Toast.makeText(getActivity(), "Berhasil menghapus pengumuman",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                return true;
                            }
                        });
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class LokerViewHolder extends RecyclerView.ViewHolder{
        public DatabaseReference dbDelete;
        View mView;

        public LokerViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setPerusahaan(String perusahaan) {
            TextView Namaperusahaan = mView.findViewById(R.id.namapt);
            Namaperusahaan.setText(perusahaan);
        }
        public void setLokasi(String lokasi) {
            TextView Lokasi = mView.findViewById(R.id.lokasipt);
            Lokasi.setText(lokasi);
        }
        public void setIsiloker(String isiloker) {
            TextView txIsi = mView.findViewById(R.id.lokerisi);
            txIsi.setText(isiloker);
        }
    }

    public interface OnFragmentInteractionListener {
    }
}
