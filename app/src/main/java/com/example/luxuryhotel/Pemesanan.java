package com.example.luxuryhotel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Pemesanan implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "number")
    public String number;

    @ColumnInfo(name = "full_name")
    public String fullName;

    @ColumnInfo(name = "telp")
    public String telp;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "pilihankamar")
    public String pilihankamar;

    @ColumnInfo(name = "checkin")
    public String checkin;

    @ColumnInfo(name="checkout")
    public String checkout;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() { return  alamat;}

    public void setAlamat(String alamat) { this.alamat = alamat;}

    public String getPilihankamar(){return pilihankamar;}

    public void setPilihankamar(String pilihankamar) {
        this.pilihankamar = pilihankamar;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() { return checkout;}

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
