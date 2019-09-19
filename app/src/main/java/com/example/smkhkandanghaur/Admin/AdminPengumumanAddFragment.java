package com.example.smkhkandanghaur.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPengumumanAddFragment extends Fragment {

    EditText Judul, Isi;
    ImageView FotoP;
    Button BtnSubmit;
    ProgressDialog progressDialog;
    TextView textimg;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    int IMAGE_REQUEST_CODE = 5;
    public Uri imguri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_admin_pengumuman_add, container, false);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("pengumuman");

        progressDialog = new ProgressDialog(getActivity());

        Judul = view.findViewById(R.id.judul);
        Isi = view.findViewById(R.id.isi);
        FotoP = view.findViewById(R.id.fotopengumuman);

        textimg = view.findViewById(R.id.textImg);
        textimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pilih foto"), IMAGE_REQUEST_CODE);
            }
        });

        FotoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
  //              intent.setType("image/*");
    //            intent.setAction(Intent.ACTION_GET_CONTENT);
      //          startActivityForResult(Intent.createChooser(intent,"Pilih foto"), IMAGE_REQUEST_CODE);
            }
        });

        BtnSubmit = view.findViewById(R.id.btnsubmit);
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPengumuman();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imguri = data.getData();
            textimg.setVisibility(View.INVISIBLE);
            FotoP.setVisibility(View.VISIBLE);
            FotoP.setImageURI(imguri);
        }
    }

    private void uploadPengumuman() {
        progressDialog.setTitle("Sedang memproses...");
        progressDialog.show();
        final String key = databaseReference.push().getKey();
        final StorageReference store = FirebaseStorage.getInstance().getReference().child("admin/").child("pengumuman/").child(key);

        store.putFile(imguri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    progressDialog.dismiss();
                    throw  task.getException();
                }
                return store.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Uri downloadUri = task.getResult();

                    String sJudul = Judul.getText().toString();
                    String sIsi = Isi.getText().toString();
                    String sFoto = downloadUri.toString();

                    databaseReference.child(key).child("judul").setValue(sJudul);
                    databaseReference.child(key).child("isi").setValue(sIsi);
                    databaseReference.child(key).child("foto").setValue(sFoto);

                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                    Toast.makeText(getActivity(), "Pengumuman berhasil disubmit", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public interface OnFragmentInteractionListener {
    }
}
