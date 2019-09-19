package com.example.smkhkandanghaur.FragmentDrawer;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.DetailPengumumanActivity;
import com.example.smkhkandanghaur.Modal.Pengumuman;
import com.example.smkhkandanghaur.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengumumanFragment extends Fragment {

    private RecyclerView recyclerView;
    CardView cardView;
    private DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pengumuman, container, false);
        getActivity().setTitle("Pengumuman");

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("pengumuman");

        cardView = view.findViewById(R.id.cardview);
        recyclerView = view.findViewById(R.id.recyclerpengumuman);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Pengumuman, PengumumanViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Pengumuman, PengumumanViewHolder>
                        (Pengumuman.class, R.layout.fragment_pengumuman_recyclerview, PengumumanViewHolder.class, databaseReference) {
                    @Override
                    protected void populateViewHolder(final PengumumanViewHolder viewHolder, final Pengumuman model, final int position) {
                        viewHolder.setJudul(model.getJUDUL());
                        viewHolder.setIsi(model.getISI());

                        //lihat detail item
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), DetailPengumumanActivity.class);
                                intent.putExtra("foto", model.getFOTO());
                                intent.putExtra("judul", model.getJUDUL());
                                intent.putExtra("isi", model.getISI());
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

    public static class PengumumanViewHolder extends RecyclerView.ViewHolder{
        public DatabaseReference dbDelete;
        View mView;

        public PengumumanViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setJudul(String Judul){
            TextView txJudul = mView.findViewById(R.id.judul);
            txJudul.setText(Judul);
        }
        public void setIsi(String Isi){
            TextView txIsi = mView.findViewById(R.id.isi);
            txIsi.setText(Isi);
        }
    }

    public interface OnFragmentInteractionListener {
    }
}

