package com.example.smkhkandanghaur.Modal;

public class Loker {
    private String PERUSAHAAN;
    private String LOKASI;
    private String ISILOKER;
    private String POTO;

    public Loker() {
    }

    public Loker(String PERUSAHAAN, String LOKASI, String ISILOKER, String POTO) {
        this.PERUSAHAAN = PERUSAHAAN;
        this.LOKASI = LOKASI;
        this.ISILOKER = ISILOKER;
        this.POTO = POTO;
    }

    public String getPERUSAHAAN() {
        return PERUSAHAAN;
    }

    public void setPERUSAHAAN(String PERUSAHAAN) {
        this.PERUSAHAAN = PERUSAHAAN;
    }

    public String getLOKASI() {
        return LOKASI;
    }

    public void setLOKASI(String LOKASI) {
        this.LOKASI = LOKASI;
    }

    public String getISILOKER() {
        return ISILOKER;
    }

    public void setISILOKER(String ISILOKER) {
        this.ISILOKER = ISILOKER;
    }

    public String getPOTO() {
        return POTO;
    }

    public void setPOTO(String POTO) {
        this.POTO = POTO;
    }
}
