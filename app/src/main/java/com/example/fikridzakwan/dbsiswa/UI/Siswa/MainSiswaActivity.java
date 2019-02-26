package com.example.fikridzakwan.dbsiswa.UI.Siswa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fikridzakwan.dbsiswa.Adapter.SiswaAdapter;
import com.example.fikridzakwan.dbsiswa.DB.Constant;
import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.SiswaModel;
import com.example.fikridzakwan.dbsiswa.R;
import com.example.fikridzakwan.dbsiswa.UI.UpdateKelasActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSiswaActivity extends AppCompatActivity {

    @BindView(R.id.rvSiswa)
    RecyclerView rvSiswa;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    // TODO 1 Membuat variable uang kita butuhkan
    private SiswaDB siswaDB;
    private List<SiswaModel> siswaModelList;
    private int id_kelas;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_siswa);
        ButterKnife.bind(this);

        // Menangkap data dari activity sebelumnya
        bundle = getIntent().getExtras();

        // Mengecek apakah bundel null atau tidak
        if (bundle != null) {
            // Menangkap data id_kelas ke dalam variable
            id_kelas = bundle.getInt(Constant.KEY_ID_KELAS);
            // Set title degan menangkap data kelas yang di tangkap dari activity sebelumnya
            setTitle(bundle.getString(Constant.KEY_NAMA_KELAS));
        }
        // Membuat database object
        siswaDB = SiswaDB.createDatabase(this);

        // Membuat object list
        siswaModelList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Menghapus ini data di dalam list
        siswaModelList.clear();

        // Mengambil data dan mengisinya
        getData();

        // Mensetting adapter untuk menampilkan data nya
        // Mensetting garis bawah
        rvSiswa.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // Setting LinearLayout
        rvSiswa.setLayoutManager(new LinearLayoutManager(this));
        // Set adapter
        rvSiswa.setAdapter(new SiswaAdapter(this, siswaModelList));
    }

    private void getData() {
        // Operasi menampilkan data yang ada di dalam SQLite menggunakan select degan parameter id_kelas
        // Untuk mengambil data siswa yang sesuai denga kelas yang diinginkan
        siswaModelList = siswaDB.kelasdao().selectSiswa(id_kelas);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, TambahSiswaActivity.class).putExtra(Constant.KEY_ID_KELAS, id_kelas));
    }
}
