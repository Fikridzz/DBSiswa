package com.example.fikridzakwan.dbsiswa.DB;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.fikridzakwan.dbsiswa.Model.KelasModel;
import com.example.fikridzakwan.dbsiswa.Model.SiswaModel;

import java.util.List;

@Dao
public interface Kelasdao {

    // Mengambil data
    @Query("SELECT * FROM KELAS ORDER BY nama_kelas DESC")
    List<KelasModel>
    select();

    // Memasukan data
    @Insert
    void insert(KelasModel kelasModels);

    // Menghapus data
    @Delete
    void delete(KelasModel kelasModel);

    // Mengupdate data
    @Update
    void updata(KelasModel kelasModel);

    // Mengambil data siswa
    @Query("SELECT * FROM TB_SISWA WHERE id_kelas = :id_kelas ORDER BY nama_siswa ASC")
    List<SiswaModel> selectSiswa(int id_kelas);

    // Memasukan data siswa
    @Insert
    void insertSiswa(SiswaModel siswaModel);

    // Menghapus data
    @Delete
    void deleteSiswa(SiswaModel siswaModel);

    // Mengupdate Data
    @Update
    void updateSiswa(SiswaModel siswaModel);

}
