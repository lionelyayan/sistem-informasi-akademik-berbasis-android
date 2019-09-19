package com.example.smkhkandanghaur.Admin;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.example.smkhkandanghaur.Modal.Loker;
import com.example.smkhkandanghaur.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLokerAddFragment extends Fragment {

    EditText ETperusahaan, ETlokasi, ETisiloker;
    ImageView IVpoto;
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
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_admin_loker_add, container, false);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("smkh").child("loker");

        progressDialog = new ProgressDialog(getActivity());

        ETperusahaan = view.findViewById(R.id.perusahaan);
        ETlokasi = view.findViewById(R.id.lokasi);
        ETisiloker = view.findViewById(R.id.isiloker);

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

        IVpoto = view.findViewById(R.id.poto);
        IVpoto.setOnClickListener(new View.OnClickListener() {
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
                submitLoker();
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
            IVpoto.setVisibility(View.VISIBLE);
            IVpoto.setImageURI(imguri);
        }
    }

    private void submitLoker() {
        progressDialog.setTitle("Sedang memproses...");
        progressDialog.show();
        final String key = databaseReference.push().getKey();
        final StorageReference store = FirebaseStorage.getInstance().getReference().child("admin/").child("loker/").child(key);

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

                    String sPerusahaan = ETperusahaan.getText().toString();
                    String sLokasi = ETlokasi.getText().toString();
                    String sIsi = ETisiloker.getText().toString();
                    String sFoto = downloadUri.toString();

                    Loker loker = new Loker(sPerusahaan, sLokasi, sIsi, sFoto);
                    databaseReference.child(key).setValue(loker);

                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                    Toast.makeText(getActivity(), "Loker berhasil ditambahkan", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public interface OnFragmentInteractionListener {
    }
}
