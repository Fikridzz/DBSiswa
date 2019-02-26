package com.example.fikridzakwan.dbsiswa.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.KelasModel;
import com.example.fikridzakwan.dbsiswa.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahKelasActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKelas)
    EditText edtNamaKelas;
    @BindView(R.id.edtNamaWali)
    EditText edtNamaWali;
    @BindView(R.id.btnSave)
    Button btnSave;

    // TODO 1 Membuat variable yang dibutuhkan
    private SiswaDB siswaDB;

    private String namaKelas, namaWali;
    private int idKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);
        ButterKnife.bind(this);

        // TODO 2 Mensetting judul activity
        setTitle("Add Kelas");

        // TODO 3 Membuat object database SiswaDatabase
        siswaDB = SiswaDB.createDatabase(this);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        // TODO 4 Mengambil data dari input dari user
        getData();

        // TODO 5 Proses menyimpan data ke SQLite
        saveData();

        clearData();

        Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void clearData() {
    }

    private void saveData() {

        // Membuat variable List kelasModels
//        List<KelasModel> kelasModels = new ArrayList<>();

        // Membuat object kelasModel untuk menampung data
        KelasModel kelasModel = new KelasModel();

        // Memasukan data ke dalam kelasModels
        kelasModel.setNama_kelas(namaKelas);
        kelasModel.setNama_wali(namaWali);

        // Memasukan daat yang sudah terkumpul di dalama kelasModels ke dalam List kelasModels
//        kelasModels.add(kelasModel);

        // Perintah untuk melakukan operasi Insert menggunakan siswaDatabase
        siswaDB.kelasdao().insert(kelasModel);
        finish();
    }

    private void getData() {
        namaKelas = edtNamaKelas.getText().toString();
        namaWali = edtNamaWali.getText().toString();
    }
}
