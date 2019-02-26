package com.example.fikridzakwan.dbsiswa.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fikridzakwan.dbsiswa.Adapter.KelasAdapter;
import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.KelasModel;
import com.example.fikridzakwan.dbsiswa.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvKelas)
    RecyclerView rvKelas;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    // TODO 1 Membuat variable
    private SiswaDB siswaDB;
    private List<KelasModel> kelasModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO 2 Mensseting variable yang di butuhkan
        // Membuat object database
        siswaDB = SiswaDB.createDatabase(this);

        // Membuat object List
        kelasModelList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahKelasActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mengosongkan list agar dipastikan list dapat diisi dgn data yg paling baru
        kelasModelList.clear();
        // Mengambil data dari SQLite
        getData();
        // Mensseting dan proses menampilkan data ke RecyclerView
        rvKelas.setLayoutManager(new GridLayoutManager(this, 2));
        rvKelas.setAdapter(new KelasAdapter(this, kelasModelList));
    }

    private void getData() {
        // Operasi mengambil data dari database SQLite
        kelasModelList = siswaDB.kelasdao().select();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
    }
}
