package com.example.smkhkandanghaur.Modal;

public class Nilai {
    private String MATPEL;
    private String NILAI;

    public Nilai() {
    }

    public Nilai(String MATPEL, String NILAI) {
        this.MATPEL = MATPEL;
        this.NILAI = NILAI;
    }

    public String getMATPEL() {
        return MATPEL;
    }

    public void setMATPEL(String MATPEL) {
        this.MATPEL = MATPEL;
    }

    public String getNILAI() {
        return NILAI;
    }

    public void setNILAI(String NILAI) {
        this.NILAI = NILAI;
    }
}
