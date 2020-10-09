package com.example.luxuryhotel;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxuryhotel.databinding.ActivityMainBinding;
import com.karumi.dexter.Dexter;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PermissionsListener {

    private ArrayList<Rooms> ListRooms;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ActivityMainBinding binding;
    private PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionsManager = new PermissionsManager(this);
        permissionsManager.requestLocationPermissions(this);
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewRooms.setHasFixedSize(true);

        //get data rooms
        ListRooms = new DaftarRooms().ROOMS;

        //recycler view
        recyclerView = findViewById(R.id.recycler_view_rooms);
        adapter = new RecyclerViewAdapter(MainActivity.this, ListRooms);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        permissionsManager.onRequestPermissionsResult(requestCode, permission, grantResults);
    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "Grant All Permission", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {

    }
}