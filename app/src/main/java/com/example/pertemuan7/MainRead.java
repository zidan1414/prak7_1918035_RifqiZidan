package com.example.pertemuan7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements
        AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Mahasiswa> ListMahasiswa = new
            ArrayList<Mahasiswa>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListMahasiswa
        );
        mListView = (ListView) findViewById(R.id.list_mahasiswa);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListMahasiswa.clear();
        List<Mahasiswa> mahasiswa = db.ReadMahasiswa();
        for (Mahasiswa mhs : mahasiswa) {
            Mahasiswa daftar = new Mahasiswa();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_kelas(mhs.get_kelas());
            ListMahasiswa.add(daftar);
            if ((ListMahasiswa.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Mahasiswa detailMhs = (Mahasiswa)o;
        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama();
        String Skelas = detailMhs.get_kelas();
        Intent goUpdel = new Intent(MainRead.this,
                MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ikelas", Skelas);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListMahasiswa.clear();
        mListView.setAdapter(adapter_off);
        List<Mahasiswa> mahasiswa = db.ReadMahasiswa();
        for (Mahasiswa mhs : mahasiswa) {
            Mahasiswa daftar = new Mahasiswa();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_kelas(mhs.get_kelas());
            ListMahasiswa.add(daftar);
            if ((ListMahasiswa.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}

