package com.example.luxuryhotel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    public RelativeLayout btnKamar, btnSewa, btnAbout, btnProfil, btnLokasi, btnSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        TextView nama = findViewById(R.id.tvNama);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserHotel", Context.MODE_PRIVATE);

        String namastring = sp.getString("Nama", "");
        nama.setText(namastring);

        btnKamar = (RelativeLayout) findViewById(R.id.lihat);
        btnSewa = (RelativeLayout) findViewById(R.id.sewa);
        btnAbout = (RelativeLayout) findViewById(R.id.about);
        btnProfil = (RelativeLayout) findViewById(R.id.profil);
        btnLokasi = (RelativeLayout) findViewById(R.id.lokasi_hotel);
        btnSignOut = (RelativeLayout) findViewById(R.id.sign_out);

        btnKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PemesananActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignOut.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
