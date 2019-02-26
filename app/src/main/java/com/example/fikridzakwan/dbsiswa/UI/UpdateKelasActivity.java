package com.example.fikridzakwan.dbsiswa.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fikridzakwan.dbsiswa.DB.Constant;
import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.KelasModel;
import com.example.fikridzakwan.dbsiswa.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateKelasActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKelas)
    EditText edtNamaKelas;
    @BindView(R.id.edtNamaWali)
    EditText edtNamaWali;
    @BindView(R.id.btnSave)
    Button btnSave;

    // Membuat variable bundel
    private Bundle bundle;

    // Membyat variable list
    private List<KelasModel> kelasModelList;

    // Membuat variable database
    private SiswaDB siswaDB;

    String nama_kelas, nama_wali;
    int id_kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kelas);
        ButterKnife.bind(this);

        // Set title
        setTitle("Update class");

        // Menangkap data dari activity sebelumnya
        bundle = getIntent().getExtras();

        // Buat object list
        kelasModelList = new ArrayList<>();

        // Buat object database
        siswaDB = SiswaDB.createDatabase(this);

        // Menampilkan data sebelum nya ke layar
        showData();


    }

    private void showData() {
        // Mengambil data di dalam bundle
        nama_wali = bundle.getString(Constant.KEY_NAMA_WALI);
        nama_kelas = bundle.getString(Constant.KEY_NAMA_KELAS);
        id_kelas = bundle.getInt(Constant.KEY_ID_KELAS);

        // Menampilkan ke layar
        edtNamaKelas.setText(nama_kelas);
        edtNamaWali.setText(nama_wali);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        getData();

        savaData();

        Toast.makeText(this, "Data Update", Toast.LENGTH_SHORT).show();
        finish();

    }
    private void savaData() {
        KelasModel kelasModel = new KelasModel();
        kelasModel.setId_kelas(id_kelas);
        kelasModel.setNama_kelas(nama_kelas);
        kelasModel.setNama_wali(nama_wali);
        siswaDB.kelasdao().updata(kelasModel);
    }

    private void getData() {
        nama_kelas = edtNamaKelas.getText().toString();
        nama_wali = edtNamaWali.getText().toString();
    }
}
