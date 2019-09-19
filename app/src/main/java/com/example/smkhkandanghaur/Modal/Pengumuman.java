package com.example.smkhkandanghaur.Modal;

public class Pengumuman {
    private String JUDUL;
    private String ISI;
    private String FOTO;

    public Pengumuman() {
    }

    public Pengumuman(String JUDUL, String ISI, String FOTO) {
        this.JUDUL = JUDUL;
        this.ISI = ISI;
        this.FOTO = FOTO;
    }

    public String getJUDUL() {
        return JUDUL;
    }

    public void setJUDUL(String JUDUL) {
        this.JUDUL = JUDUL;
    }

    public String getISI() {
        return ISI;
    }

    public void setISI(String ISI) {
        this.ISI = ISI;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }
}
