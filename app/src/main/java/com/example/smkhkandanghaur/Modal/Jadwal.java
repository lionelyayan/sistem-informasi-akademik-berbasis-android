package com.example.smkhkandanghaur.Modal;

public class Jadwal {
    private String PERTAMA, KEDUA, KETIGA, KEEMPAT, HARI;

    public Jadwal(){
    }

    public Jadwal(String PERTAMA, String KEDUA, String KETIGA, String KEEMPAT, String HARI) {
        this.PERTAMA = PERTAMA;
        this.KEDUA = KEDUA;
        this.KETIGA = KETIGA;
        this.KEEMPAT = KEEMPAT;
        this.HARI = HARI;
    }

    public String getPERTAMA() {
        return PERTAMA;
    }

    public void setPERTAMA(String PERTAMA) {
        this.PERTAMA = PERTAMA;
    }

    public String getKEDUA() {
        return KEDUA;
    }

    public void setKEDUA(String KEDUA) {
        this.KEDUA = KEDUA;
    }

    public String getKETIGA() {
        return KETIGA;
    }

    public void setKETIGA(String KETIGA) {
        this.KETIGA = KETIGA;
    }

    public String getKEEMPAT() {
        return KEEMPAT;
    }

    public void setKEEMPAT(String KEEMPAT) {
        this.KEEMPAT = KEEMPAT;
    }

    public String getHARI() {
        return HARI;
    }

    public void setHARI(String HARI) {
        this.HARI = HARI;
    }

    @Override
    public String toString(){
        return " "+PERTAMA+"\n" +
                " "+KEDUA+"\n" +
                " "+KETIGA+"\n" +
                " "+KEEMPAT+"\n" +
                " "+HARI;
    }
}
