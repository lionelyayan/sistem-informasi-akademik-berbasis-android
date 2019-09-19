package com.example.smkhkandanghaur.Modal;

public class Latihan {
    private String JAWABANA, JAWABANB, JAWABANC, JAWABAND, PERTANYAAN;

    public Latihan() {
    }

    public Latihan(String JAWABANA, String JAWABANB, String JAWABANC, String JAWABAND, String PERTANYAAN) {
        this.JAWABANA = JAWABANA;
        this.JAWABANB = JAWABANB;
        this.JAWABANC = JAWABANC;
        this.JAWABAND = JAWABAND;
        this.PERTANYAAN = PERTANYAAN;
    }

    public String getJAWABANA() {
        return JAWABANA;
    }

    public void setJAWABANA(String JAWABANA) {
        this.JAWABANA = JAWABANA;
    }

    public String getJAWABANB() {
        return JAWABANB;
    }

    public void setJAWABANB(String JAWABANB) {
        this.JAWABANB = JAWABANB;
    }

    public String getJAWABANC() {
        return JAWABANC;
    }

    public void setJAWABANC(String JAWABANC) {
        this.JAWABANC = JAWABANC;
    }

    public String getJAWABAND() {
        return JAWABAND;
    }

    public void setJAWABAND(String JAWABAND) {
        this.JAWABAND = JAWABAND;
    }

    public String getPERTANYAAN() {
        return PERTANYAAN;
    }

    public void setPERTANYAAN(String PERTANYAAN) {
        this.PERTANYAAN = PERTANYAAN;
    }
}
