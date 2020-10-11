package com.example.luxuryhotel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PemesananDao {

    @Query("SELECT * FROM Pemesanan")
    List<Pemesanan> getAll();

    @Insert
    void insert(Pemesanan pemesanan);

    @Update
    void update(Pemesanan pemesanan);

    @Delete
    void delete(Pemesanan pemesanan);
}
