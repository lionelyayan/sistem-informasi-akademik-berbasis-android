package com.example.smkhkandanghaur.Modal;

public class SiswaModal {
    private String NISN, NAMA, TEMPATLAHIR, TANGGALLAHIR, KELAMIN, ALAMAT, NOTELP,
            EMAIL, PASSWORD, TIPE, JURUSAN, KELAS, FOTO;

    public SiswaModal() {
    }

    public SiswaModal(String NISN, String NAMA, String TEMPATLAHIR, String TANGGALLAHIR, String KELAMIN,
                      String ALAMAT, String NOTELP, String EMAIL, String PASSWORD, String TIPE, String JURUSAN,
                      String KELAS, String FOTO) {
        this.NISN = NISN;
        this.NAMA = NAMA;
        this.TEMPATLAHIR = TEMPATLAHIR;
        this.TANGGALLAHIR = TANGGALLAHIR;
        this.KELAMIN = KELAMIN;
        this.ALAMAT = ALAMAT;
        this.NOTELP = NOTELP;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.TIPE = TIPE;
        this.JURUSAN = JURUSAN;
        this.KELAS = KELAS;
        this.FOTO = FOTO;
    }

    public String getNISN() {
        return NISN;
    }

    public void setNISN(String NISN) {
        this.NISN = NISN;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getTEMPATLAHIR() {
        return TEMPATLAHIR;
    }

    public void setTEMPATLAHIR(String TEMPATLAHIR) {
        this.TEMPATLAHIR = TEMPATLAHIR;
    }

    public String getTANGGALLAHIR() {
        return TANGGALLAHIR;
    }

    public void setTANGGALLAHIR(String TANGGALLAHIR) {
        this.TANGGALLAHIR = TANGGALLAHIR;
    }

    public String getKELAMIN() {
        return KELAMIN;
    }

    public void setKELAMIN(String KELAMIN) {
        this.KELAMIN = KELAMIN;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getNOTELP() {
        return NOTELP;
    }

    public void setNOTELP(String NOTELP) {
        this.NOTELP = NOTELP;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getTIPE() {
        return TIPE;
    }

    public void setTIPE(String TIPE) {
        this.TIPE = TIPE;
    }

    public String getJURUSAN() {
        return JURUSAN;
    }

    public void setJURUSAN(String JURUSAN) {
        this.JURUSAN = JURUSAN;
    }

    public String getKELAS() {
        return KELAS;
    }

    public void setKELAS(String KELAS) {
        this.KELAS = KELAS;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }

}
