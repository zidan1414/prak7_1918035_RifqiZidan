package com.example.pertemuan7;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Mahasiswa> Mahasiswa;
    public CustomListAdapter(Activity activity, List<Mahasiswa>
            Mahasiswa) {
        this.activity = activity;
        this.Mahasiswa = Mahasiswa;
    }
    @Override
    public int getCount() {
        return Mahasiswa.size();
    }
    @Override
    public Object getItem(int location) {
        return Mahasiswa.get(location);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity

                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_list,
                    null);
        TextView nama = (TextView)
                convertView.findViewById(R.id.text_nama);
        TextView kelas = (TextView)
                convertView.findViewById(R.id.text_kelas);
        ImageView imageView = (ImageView)
                convertView.findViewById(R.id.iconid);
        Mahasiswa m = Mahasiswa.get(position);
        nama.setText("Nama : "+ m.get_nama());
        kelas.setText("Kelas : "+ m.get_kelas());
        return convertView;
    }
}
