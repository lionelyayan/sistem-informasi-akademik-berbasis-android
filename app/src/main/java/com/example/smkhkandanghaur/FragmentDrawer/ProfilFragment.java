package com.example.smkhkandanghaur.FragmentDrawer;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.Modal.Pengumuman;
import com.example.smkhkandanghaur.Modal.SiswaModal;
import com.example.smkhkandanghaur.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser usr;
    private DatabaseReference databaseReference;
    private String sFoto;
    StorageReference storageReference;
    int IMAGE_REQUEST_CODE = 5;
    public Uri imguri;

    private String userID,
            sNama, sKelas, sNisn, sJurusan, sAlamat, sEmail, sKelamin, sNotelp, sTglLahir, sTmptLahir;

    TextView Nama, Kelas, Nisn, Jurusan, Alamat, Email, Kelamin, Notelp, TglLahir;
    ImageView FotoProfil;
    Button GantiFoto, Foto;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profil, container, false);
        getActivity().setTitle("Profil");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.dismiss();

        GantiFoto = view.findViewById(R.id.gantifoto);
        Nama = view.findViewById(R.id.nama);
        Kelas = view.findViewById(R.id.kelas);
        Nisn = view.findViewById(R.id.nisn);
        Jurusan = view.findViewById(R.id.jurusan);
        Alamat = view.findViewById(R.id.alamat);
        Email = view.findViewById(R.id.email);
        Kelamin = view.findViewById(R.id.kelamin);
        Notelp = view.findViewById(R.id.notelp);
        TglLahir = view.findViewById(R.id.lahir);

        FotoProfil = view.findViewById(R.id.fotoprofil);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        usr = firebaseAuth.getCurrentUser();
        userID = usr.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("users").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sNama = dataSnapshot.getValue(SiswaModal.class).getNAMA();
                sKelas = dataSnapshot.getValue(SiswaModal.class).getKELAS();
                sNisn = dataSnapshot.getValue(SiswaModal.class).getNISN();
                sJurusan = dataSnapshot.getValue(SiswaModal.class).getJURUSAN();
                sAlamat = dataSnapshot.getValue(SiswaModal.class).getALAMAT();
                sEmail = dataSnapshot.getValue(SiswaModal.class).getEMAIL();
                sKelamin = dataSnapshot.getValue(SiswaModal.class).getKELAMIN();
                sNotelp = dataSnapshot.getValue(SiswaModal.class).getNOTELP();
                sTglLahir = dataSnapshot.getValue(SiswaModal.class).getTANGGALLAHIR();
                sTmptLahir = dataSnapshot.getValue(SiswaModal.class).getTEMPATLAHIR();

                sFoto = dataSnapshot.child("foto").getValue().toString();
                if (sFoto.equals("null")){
                    FotoProfil.setImageResource(R.drawable.lk);
                }else {
                    Picasso.get().load(sFoto).into(FotoProfil);
                }

                Nama.setText(sNama);
                Kelas.setText(sKelas);
                Nisn.setText(sNisn);
                Jurusan.setText(sJurusan);
                Alamat.setText(sAlamat);
                Email.setText(sEmail);
                Kelamin.setText(sKelamin);
                Notelp.setText(sNotelp);
                TglLahir.setText(sTmptLahir+", "+sTglLahir);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Foto = view.findViewById(R.id.foto);
        Foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pilih foto"), IMAGE_REQUEST_CODE);
            }
        });

        GantiFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gantiFoto();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imguri = data.getData();
            FotoProfil.setImageURI(imguri);
            GantiFoto.setVisibility(View.VISIBLE);
            Foto.setVisibility(View.GONE);
        }
    }

    private void gantiFoto() {
        progressDialog.setTitle("Sedang memproses...");
        progressDialog.show();
        final String key = databaseReference.push().getKey();
        final StorageReference store = FirebaseStorage.getInstance().getReference().child("siswa/").child(userID).child(key);

        if (imguri!=null) {
            store.putFile(imguri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        progressDialog.dismiss();
                        GantiFoto.setVisibility(View.GONE);
                        Foto.setVisibility(View.VISIBLE);
                        throw task.getException();
                    }
                    return store.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Uri downloadUri = task.getResult();
                        String sFoto = downloadUri.toString();

                        databaseReference.child("foto").setValue(sFoto);
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                        GantiFoto.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Foto berhasil diganti", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            Toast.makeText(getActivity(), "Pilih foto", Toast.LENGTH_SHORT).show();
        }
    }
}
