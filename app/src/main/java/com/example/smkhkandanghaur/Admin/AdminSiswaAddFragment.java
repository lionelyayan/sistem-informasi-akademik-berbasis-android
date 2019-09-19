package com.example.smkhkandanghaur.Admin;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smkhkandanghaur.Modal.Kehadiran;
import com.example.smkhkandanghaur.Modal.SiswaModal;
import com.example.smkhkandanghaur.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSiswaAddFragment extends Fragment {

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private SimpleDateFormat formatTanggal;

    EditText Nisn, Nama, Alamat, Email, Password, TmptLahir, NoTelp, Jurusan;
    TextView TglLahir, Tipe;
    RadioButton Cowok, Cewek, XA, XIB, XIIC;
    Button Submit;

    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String sKelamin = "";
    String sKelas = "";
    String sFOTO = "null";

    String sHadir = "0";
    String sAlpa = "0";
    String sIzin = "0";
    String sSakit = "0";
    String sAbsenpertama = "sudah absen";
    String sAbsenkedua = "sudah absen";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_admin_siswa_add, container, false);

        Nisn = view.findViewById(R.id.nisn);
        Nama = view.findViewById(R.id.nama);
        TmptLahir = view.findViewById(R.id.tmptlahir);
        NoTelp = view.findViewById(R.id.notelp);
        Email = view.findViewById(R.id.email);
        Alamat = view.findViewById(R.id.alamat);
        Password = view.findViewById(R.id.password);
        Cowok = view.findViewById(R.id.laki);
        Cewek = view.findViewById(R.id.perempuan);
        Submit = view.findViewById(R.id.submit);
        Tipe = view.findViewById(R.id.tipe);
        Jurusan = view.findViewById(R.id.jurusan);
        XA = view.findViewById(R.id.satu);
        XIB = view.findViewById(R.id.dua);
        XIIC = view.findViewById(R.id.tiga);

        progressDialog = new ProgressDialog(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        formatTanggal = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        TglLahir = view.findViewById(R.id.tgllahir);
        TglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int tahun = calendar.get(Calendar.YEAR);
                int bulan = calendar.get(Calendar.MONTH);
                int hari = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        dateSetListener, tahun, bulan, hari);
                datePickerDialog.getWindow();
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth +"/"+ month +"/"+ year;
                TglLahir.setText(date);
            }
        };

        progressDialog.dismiss();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sTipe = Tipe.getText().toString();
                final String sNisn = Nisn.getText().toString();
                final String sNama = Nama.getText().toString();
                final String sTmpLahir = TmptLahir.getText().toString();
                final String sTglLahir = TglLahir.getText().toString();
                final String sAlamat = Alamat.getText().toString();
                final String sNoTelp = NoTelp.getText().toString();
                final String sJurusan = Jurusan.getText().toString();
                final String sEmail = Email.getText().toString();
                final String sPassword = Password.getText().toString();

                if (Cowok.isChecked()){
                    sKelamin = "Laki-laki";
                }
                if (Cewek.isChecked()){
                    sKelamin = "Perempuan";
                }
                if (XA.isChecked()){
                    sKelas = "X A";
                }
                if (XIB.isChecked()){
                    sKelas = "XI B";
                }
                if (XIIC.isChecked()){
                    sKelas = "XII C";
                }
                if (TextUtils.isEmpty(sNisn)){
                    Nisn.setError("NISN harus diisi");
                    Nisn.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sNama)){
                    Nama.setError("Nama harus diisi");
                    Nama.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sTmpLahir)){
                    TmptLahir.setError("Tempat lahir harus diisi");
                    TmptLahir.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sAlamat)){
                    Alamat.setError("Alamat harus diisi");
                    Alamat.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sNoTelp)){
                    NoTelp.setError("No. telepon harus diisi");
                    NoTelp.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sJurusan)){
                    Jurusan.setError("Jurusan harus diisi");
                    Jurusan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sEmail)){
                    Email.setError("Email harus diisi");
                    Email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(sPassword)){
                    Password.setError("Password harus diisi");
                    Password.requestFocus();
                    return;
                }

                progressDialog.setTitle("Please wait...");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            SiswaModal informasi = new SiswaModal(sNisn, sNama, sTmpLahir, sTglLahir,
                                    sKelamin, sAlamat, sNoTelp, sEmail, sPassword, sTipe, sJurusan, sKelas, sFOTO);
                            FirebaseDatabase.getInstance().getReference().child("smkh").child("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(informasi);

                            Kehadiran hdr = new Kehadiran(sHadir, sAlpa, sIzin, sSakit, sAbsenpertama, sAbsenkedua);
                            FirebaseDatabase.getInstance().getReference().child("smkh")
                                    .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("kehadiran").setValue(hdr);

                            Toast.makeText(getActivity(), "Berhasil menambahkan siswa", Toast.LENGTH_LONG).show();
                            getActivity().finish();
                            startActivity(getActivity().getIntent());
                        }
                    }
                });
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}
