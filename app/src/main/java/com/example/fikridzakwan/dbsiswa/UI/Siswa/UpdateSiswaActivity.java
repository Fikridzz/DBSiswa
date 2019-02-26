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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateSiswaActivity extends AppCompatActivity {

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
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    private Bundle bundle;

    private List<SiswaModel> siswaModelList;

    private SiswaDB siswaDB;

    private String nama_siswa, asal, umur, jenis_kelamin, email;

    private int id_siswa, id_kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_siswa);
        ButterKnife.bind(this);

        setTitle("Student Update");
        bundle = getIntent().getExtras();
        siswaDB = SiswaDB.createDatabase(this);

        showData();
    }

    private void showData() {
        id_kelas = bundle.getInt(Constant.KEY_ID_KELAS, 0);
        id_siswa = bundle.getInt(Constant.KEY_ID_SISWA, 0);
        nama_siswa = bundle.getString(Constant.KEY_NAMA_SISWA);
        umur = bundle.getString(Constant.KEY_UMUR);
        asal = bundle.getString(Constant.KEY_ASAL);
        email = bundle.getString(Constant.KEY_EMAIL);
        jenis_kelamin = bundle.getString(Constant.KEY_JENIS_KELAMIN);

        if (jenis_kelamin.equals(radioLaki.getText().toString())) {
            radioJenisKelamin.check(radioLaki.getId());
        }

        if (jenis_kelamin.equals(radioPerempuan.getText().toString())) {
            radioJenisKelamin.check(radioPerempuan.getId());
        }

        edtNamaSiswa.setText(nama_siswa);
        edtUmur.setText(umur);
        edtMultiline.setText(asal);
        edtEmail.setText(email);
    }

    private void clearData() {
        edtEmail.setText("");
        edtMultiline.setText("");
        edtUmur.setText("");
        edtNamaSiswa.setText("");
    }

    private void updateData() {
        SiswaModel siswaModel = new SiswaModel();
        siswaModel.setNama_siswa(nama_siswa);
        siswaModel.setEmail(email);
        siswaModel.setAsal(asal);
        siswaModel.setJenis_kelamin(jenis_kelamin);
        siswaModel.setUmur(umur);
        siswaModel.setId_siswa(id_siswa);
        siswaModel.setId_kelas(id_kelas);

        siswaDB.kelasdao().updateSiswa(siswaModel);
    }

    private void getData() {
        nama_siswa = edtNamaSiswa.getText().toString();
        asal = edtMultiline.getText().toString();
        email = edtEmail.getText().toString();
        umur = edtUmur.getText().toString();

        updateData();
        clearData();
        Toast.makeText(this, "Data Update", Toast.LENGTH_SHORT).show();

        finish();
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

    @OnClick(R.id.btnUpdate)
    public void onViewClicked() {
        getData();

    }
}
