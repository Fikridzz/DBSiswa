package com.example.fikridzakwan.dbsiswa.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.fikridzakwan.dbsiswa.Model.KelasModel;
import com.example.fikridzakwan.dbsiswa.Model.SiswaModel;

@Database(entities = {KelasModel.class, SiswaModel.class}, version = 1)
public abstract class SiswaDB extends RoomDatabase {

    public abstract Kelasdao kelasdao();

    private static SiswaDB INSTANCE;

    public static SiswaDB createDatabase(Context context) {
        synchronized (SiswaDB.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, SiswaDB.class, "db_siswa").allowMainThreadQueries().build();
            }
        }return INSTANCE;
    }

}
