package com.example.pertemuan7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_kampus";
    private static final String tb_mahasiswa = "tb_mahasiswa";
    private static final String tb_mahasiswa_id = "id";
    private static final String tb_mahasiswa_nama = "nama";
    private static final String tb_mahasiswa_kelas = "kelas";
    private static final String CREATE_TABLE_MAHASISWA = "CREATETABLE " + tb_mahasiswa +"("
            + tb_mahasiswa_id + " INTEGER PRIMARY KEY ,"
            + tb_mahasiswa_nama + " TEXT ,"
            + tb_mahasiswa_kelas + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreateMahasiswa(Mahasiswa data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_mahasiswa_id, data.get_id());
        values.put(tb_mahasiswa_nama, data.get_nama());
        values.put(tb_mahasiswa_kelas, data.get_kelas());
        db.insert(tb_mahasiswa, null, values);
        db.close();
    }
    public List<Mahasiswa> ReadMahasiswa() {
        List<Mahasiswa> listMhs = new ArrayList<Mahasiswa>();
        String selectQuery = "SELECT * FROM " + tb_mahasiswa;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Mahasiswa data = new Mahasiswa();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_kelas(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int UpdateMahasiswa (Mahasiswa data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_mahasiswa_nama, data.get_nama());
        values.put(tb_mahasiswa_kelas, data.get_kelas());
        return db.update(tb_mahasiswa, values, tb_mahasiswa_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteMahasiswa(Mahasiswa data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_mahasiswa,tb_mahasiswa_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}