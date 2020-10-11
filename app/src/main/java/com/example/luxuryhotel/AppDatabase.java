package com.example.luxuryhotel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pemesanan.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract PemesananDao employeeDao();
}
