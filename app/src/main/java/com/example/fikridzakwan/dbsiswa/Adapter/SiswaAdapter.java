package com.example.fikridzakwan.dbsiswa.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.fikridzakwan.dbsiswa.DB.Constant;
import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.SiswaModel;
import com.example.fikridzakwan.dbsiswa.R;
import com.example.fikridzakwan.dbsiswa.UI.Siswa.UpdateSiswaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private final Context context;
    private final List<SiswaModel> siswaModelList;

    private Bundle bundle;

    public SiswaAdapter(Context context, List<SiswaModel> siswaModelList) {
        this.context = context;
        this.siswaModelList = siswaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        // Memindah kan data yang dipilih ke dalam list
        final SiswaModel siswaModel = siswaModelList.get(i);

        // Kita tampilkan data ke layar
        viewHolder.txtNamaSiswa.setText(siswaModel.getNama_siswa());

        // Menampilkan huruf pertama
        String nama = siswaModel.getNama_siswa().substring(0,1);

        // Membuat color generator untuk mendapatkan color material
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();

        // Mensetting TextDrawable untuk membuat lingkaran
        TextDrawable drawable = TextDrawable.builder().buildRound(nama,color);

        // Tamplikan gambar lingkaran ke layar
        viewHolder.imgSiswa.setImageDrawable(drawable);

        final SiswaDB siswaDB = SiswaDB.createDatabase(context);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you delete " + siswaModel.getNama_siswa() + " ?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        siswaDB.kelasdao().deleteSiswa(siswaModel);
                        siswaModelList.remove(i);
                        notifyItemRemoved(i);
                        notifyItemRangeChanged(0, siswaModelList.size());

                        Toast.makeText(context, "Item Remove", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constant.KEY_ID_SISWA, siswaModel.getId_siswa());
                bundle.putString(Constant.KEY_NAMA_SISWA, siswaModel.getNama_siswa());
                bundle.putString(Constant.KEY_EMAIL, siswaModel.getEmail());
                bundle.putString(Constant.KEY_UMUR, siswaModel.getUmur());
                bundle.putString(Constant.KEY_ASAL, siswaModel.getAsal());
                bundle.putString(Constant.KEY_JENIS_KELAMIN, siswaModel.getJenis_kelamin());
                bundle.putInt(Constant.KEY_ID_KELAS, siswaModel.getId_kelas());

                context.startActivity(new Intent(context, UpdateSiswaActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {

        return siswaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgSiswa)
        ImageView imgSiswa;
        @BindView(R.id.txtNamaSiswa)
        TextView txtNamaSiswa;
        @BindView(R.id.btnDelete)
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
