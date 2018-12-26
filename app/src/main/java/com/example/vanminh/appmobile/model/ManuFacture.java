package com.example.vanminh.appmobile.model;

import java.io.Serializable;

public class ManuFacture implements Serializable {
    private int idSX;
    private String nameSX;

    public ManuFacture(int idSX, String nameSX) {
        this.idSX = idSX;
        this.nameSX = nameSX;
    }

    public ManuFacture() {

    }

    public int getIdSX() {
        return idSX;
    }

    public void setIdSX(int idSX) {
        this.idSX = idSX;
    }

    public String getNameSX() {
        return nameSX;
    }

    public void setNameSX(String nameSX) {
        this.nameSX = nameSX;
    }
    @Override
    public String toString() {
        return nameSX;
    }
}
