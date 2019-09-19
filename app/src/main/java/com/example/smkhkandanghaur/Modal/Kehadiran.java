package com.example.smkhkandanghaur.Modal;

public class Kehadiran {
    private String HADIR, IZIN, SAKIT, ALPA, ABSENPERTAMA, ABSENKEDUA;

    public Kehadiran(){
    }

    public Kehadiran(String HADIR, String IZIN, String SAKIT, String ALPA, String ABSENPERTAMA, String ABSENKEDUA) {
        this.HADIR = HADIR;
        this.IZIN = IZIN;
        this.SAKIT = SAKIT;
        this.ALPA = ALPA;
        this.ABSENPERTAMA = ABSENPERTAMA;
        this.ABSENKEDUA = ABSENKEDUA;
    }

    public String getHADIR() {
        return HADIR;
    }

    public void setHADIR(String HADIR) {
        this.HADIR = HADIR;
    }

    public String getIZIN() {
        return IZIN;
    }

    public void setIZIN(String IZIN) {
        this.IZIN = IZIN;
    }

    public String getSAKIT() {
        return SAKIT;
    }

    public void setSAKIT(String SAKIT) {
        this.SAKIT = SAKIT;
    }

    public String getALPA() {
        return ALPA;
    }

    public void setALPA(String ALPA) {
        this.ALPA = ALPA;
    }

    public String getABSENPERTAMA() {
        return ABSENPERTAMA;
    }

    public void setABSENPERTAMA(String ABSENPERTAMA) {
        this.ABSENPERTAMA = ABSENPERTAMA;
    }

    public String getABSENKEDUA() {
        return ABSENKEDUA;
    }

    public void setABSENKEDUA(String ABSENKEDUA) {
        this.ABSENKEDUA = ABSENKEDUA;
    }
}
