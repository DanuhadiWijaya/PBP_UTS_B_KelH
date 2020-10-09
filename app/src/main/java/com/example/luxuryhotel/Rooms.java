package com.example.luxuryhotel;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Rooms {
    public String NamaRooms;
    public int HargaRooms;
    public String KapasitasRooms;
    public String imgURL;

    public Rooms(String namaRooms, int hargaRooms, String kapasitasRooms, String imgURL) {
        this.NamaRooms = namaRooms;
        this.HargaRooms = hargaRooms;
        this.KapasitasRooms = kapasitasRooms;
        this.imgURL = imgURL;
    }

    public String getNamaRooms() {
        return NamaRooms;
    }

    public void setNamaRooms(String namaRooms) {
        this.NamaRooms = namaRooms;
    }

    public int getHargaRooms() {
        return HargaRooms;
    }

    public void setHargaRooms(int hargaRooms) {
        this.HargaRooms = hargaRooms;
    }

    public String getStringHargaRooms() { return String.valueOf(HargaRooms); }

    public void setStringHargaRooms(String hargaRooms) {
        if(!hargaRooms.isEmpty()) {
            this.HargaRooms = Integer.parseInt(hargaRooms);
        }else{
            this.HargaRooms = 0;
        }
    }

    public String getKapasitasRooms() {
        return KapasitasRooms;
    }

    public void setKapasitasRooms(String kapasitasRooms) {
        this.KapasitasRooms = kapasitasRooms;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imgURL) {
        Glide.with(imageView)
                .load(imgURL)
                .into(imageView);
    }
}
