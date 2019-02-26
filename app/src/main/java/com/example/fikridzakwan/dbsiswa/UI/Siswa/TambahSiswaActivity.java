package com.example.fikridzakwan.dbsiswa.UI.Siswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fikridzakwan.dbsiswa.DB.Constant;
import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.SiswaModel;
import com.example.fikridzakwan.dbsiswa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahSiswaActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaSiswa)
    EditText edtNamaSiswa;
    @BindView(R.id.edtUmur)
    EditText edtUmur;
    @BindView(R.id.radio_laki)
    RadioButton radioLaki;
    @BindView(R.id.radio_perempuan)
    RadioButton radioPerempuan;
    @BindView(R.id.radio_jenis_kelamin)
    RadioGroup radioJenisKelamin;
    @BindView(R.id.edtMultiline)
    EditText edtMultiline;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.edtEmail)
    EditText edtEmail;

    // TODO 1 Membuat variable yang dibutuhkan
    private SiswaDB siswaDB;
    private int id_kelas;
    private String namaSiswa, asal, umur, jenis_kelamin, email;
    private boolean empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_siswa);
        ButterKnife.bind(this);

        // TODO 2 Mensetting dan menangkap data dari activity sebelumnya
        // Mensseting title
        setTitle("Add siswa");

        // Tangkap id_kelas dari activity sebelumnya
        id_kelas = getIntent().getIntExtra(Constant.KEY_ID_KELAS, 0);

        // Kita buat object database
        siswaDB = SiswaDB.createDatabase(this);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {
        // Menampilkan semuanya terisi
        cekData();

        if (!empty) {
            saveData();
            clearData();
            Toast.makeText(this, "Update data", Toast.LENGTH_SHORT).show();

            finish();
        }
        else {
            Toast.makeText(this, "There is still an empty column\n", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData() {
        edtNamaSiswa.setText("");
        edtUmur.setText("");
        edtMultiline.setText("");
        edtEmail.setText("");
        radioJenisKelamin.clearCheck();
    }

    private void saveData() {
        // Membuat penampung SiswaModel
        SiswaModel siswaModel = new SiswaModel();

        // Masukan data ke dalam SiswaModel
        siswaModel.setId_kelas(id_kelas);
        siswaModel.setNama_siswa(namaSiswa);
        siswaModel.setAsal(asal);
        siswaModel.setUmur(umur);
        siswaModel.setEmail(email);
        siswaModel.setJenis_kelamin(jenis_kelamin);

        siswaDB.kelasdao().insertSiswa(siswaModel);
    }

    private void cekData() {
        namaSiswa = edtNamaSiswa.getText().toString();
        asal = edtMultiline.getText().toString();
        umur = edtUmur.getText().toString();
        email = edtEmail.getText().toString();

        empty = namaSiswa.isEmpty() || asal.isEmpty() || umur.isEmpty() || email.isEmpty() || jenis_kelamin.isEmpty();
    }


    @OnClick({R.id.radio_laki, R.id.radio_perempuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_laki:
                jenis_kelamin = radioLaki.getText().toString();
                break;
            case R.id.radio_perempuan:
                jenis_kelamin = radioPerempuan.getText().toString();
                break;
        }
    }
}
