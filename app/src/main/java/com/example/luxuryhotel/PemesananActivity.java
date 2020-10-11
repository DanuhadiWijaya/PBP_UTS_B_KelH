package com.example.luxuryhotel;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class PemesananActivity extends AppCompatActivity {

    private SearchView searchView3;
    private FloatingActionButton addBtn;
    private RecyclerView recyclerView3;
    private SwipeRefreshLayout refreshLayout3;
    private PemesananRecyclerViewAdapter adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_pemesan);
        addBtn = findViewById(R.id.btn_add);
        refreshLayout3 = findViewById(R.id.swipe_refresh);
        recyclerView3 = findViewById(R.id.user_rv);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        getSearch();
        getEmployee();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                FragmentAdd fragmentAdd = new FragmentAdd();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, fragmentAdd)
                        .commit();
            }
        });

        refreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEmployee();
                refreshLayout3.setRefreshing(false);
            }
        });
    }

    private void getEmployee() {
        class GetEmps extends AsyncTask<Void, Void, List<Pemesanan>> {

            @Override
            protected List<Pemesanan> doInBackground(Void... voids) {
                List<Pemesanan> pemesananList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .employeeDao()
                        .getAll();
                return pemesananList;
            }

            @Override
            protected void onPostExecute(List<Pemesanan> employees) {
                super.onPostExecute(employees);
                adapter3 = new PemesananRecyclerViewAdapter(PemesananActivity.this, employees);
                recyclerView3.setAdapter(adapter3);
                if (employees.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetEmps get = new GetEmps();
        get.execute();
    }

    private void getSearch() {
        searchView3 = findViewById(R.id.search_view);
        searchView3.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                    adapter3.getFilter().filter(s);
                }catch (NullPointerException e){}

                return false;
            }
        });
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
        if (item.getItemId()==R.id.menu_Home_Page){
            startActivity(new Intent(this, HomePage.class));
        }

        return true;
    }
}
