package com.example.fikridzakwan.dbsiswa.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.fikridzakwan.dbsiswa.DB.Constant;

@Entity (tableName = "tb_siswa")
public class SiswaModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.id_siswa)
    private int id_siswa;

    @ColumnInfo(name = Constant.id_kelas)
    private int id_kelas;

    @ColumnInfo(name = Constant.nama_siswa)
    private String nama_siswa;

    @ColumnInfo(name = Constant.umur)
    private String umur;

    @ColumnInfo(name = Constant.jenis_kelamin)
    private String jenis_kelamin;

    @ColumnInfo(name = Constant.asal)
    private String asal;

    @ColumnInfo(name = Constant.email)
    private String email;


    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public int getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(int id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
