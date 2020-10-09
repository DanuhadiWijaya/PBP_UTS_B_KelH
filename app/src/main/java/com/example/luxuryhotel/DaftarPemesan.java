package com.example.luxuryhotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarPemesan extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.daftar_pemesan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.Home){
            startActivity(new Intent(this, MainActivity.class));
        }else if(item.getItemId()==R.id.Profil_Pemesan){
            startActivity(new Intent(this, DaftarPemesan.class));
        }else if(item.getItemId()==R.id.Location){
            startActivity(new Intent(this, Location.class));
        }else if(item.getItemId()==R.id.About){
            startActivity(new Intent(this, About.class));
        }else if(item.getItemId()==R.id.Profile){
            startActivity(new Intent(this, Profile.class));
        }

        return true;
    }
}
