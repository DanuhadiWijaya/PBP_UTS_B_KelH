package com.example.luxuryhotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxuryhotel.databinding.AboutBinding;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    private ArrayList<Abouts> ListAbouts;
    private RecyclerView recyclerView2;
    private RecyclerViewAdapterAbout adapter2;
    private RecyclerView.LayoutManager mLayoutManager2;
    AboutBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.about);
        binding.recyclerViewAbout.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewAbout.setHasFixedSize(true);

        //get data rooms
        ListAbouts = new DaftarAbout().ABOUTS;

        //recycler view
        recyclerView2 = findViewById(R.id.recycler_view_about);
        adapter2 = new RecyclerViewAdapterAbout(About.this, ListAbouts);
        mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter2);
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
