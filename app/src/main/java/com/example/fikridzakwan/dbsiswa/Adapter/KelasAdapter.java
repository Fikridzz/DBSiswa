package com.example.fikridzakwan.dbsiswa.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.fikridzakwan.dbsiswa.DB.Constant;
import com.example.fikridzakwan.dbsiswa.DB.SiswaDB;
import com.example.fikridzakwan.dbsiswa.Model.KelasModel;
import com.example.fikridzakwan.dbsiswa.R;
import com.example.fikridzakwan.dbsiswa.UI.Siswa.MainSiswaActivity;
import com.example.fikridzakwan.dbsiswa.UI.UpdateKelasActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {

    private final Context context;
    private final List<KelasModel> kelasModelList;

    private SiswaDB siswaDB;

    private Bundle bundle;

    // Membuat variable AlertDialog
    private AlertDialog alertDialog;

    public KelasAdapter(Context context, List<KelasModel> dataKelasList) {
        this.context = context;
        this.kelasModelList = dataKelasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_kelas, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        // Memindahkan data di dalam list dengan index ke dalam kelasModel
        final KelasModel kelasModel = kelasModelList.get(i);
        // Menampilkan data ke layar
        viewHolder.tvNamaKelas.setText(kelasModel.getNama_kelas());
        viewHolder.tvNamaWali.setText(kelasModel.getNama_wali());

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getRandomColor();

        // Mesetting color backround cardview
        viewHolder.cv.setCardBackgroundColor(color);

        // Membauat onClikc icon  Overflow
        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siswaDB = siswaDB.createDatabase(context);

                // Membuat object PopupMenu
                PopupMenu popupMenu = new PopupMenu(context, v);

                // Inflate menu ke dalam PopupMenu
                popupMenu.inflate(R.menu.pop_up_menu);

                // Menampilkan menu
                popupMenu.show();

                // Onclik pada salah satu menu pada PopupMenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:

                                // Setting  Positive AlertDialog
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setMessage("Are you delete " + kelasModel.getNama_kelas() + " ?");
                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Melakukan operasi delete data
                                        siswaDB.kelasdao().delete(kelasModel);

                                        // Menghapus data yang telah di hapus pada list
                                        kelasModelList.remove(i);

                                        // Memberi tahu bawadata sudah hilang
                                        notifyItemRemoved(i);
                                        notifyItemRangeChanged(0, kelasModelList.size());

                                        Toast.makeText(context, "Item Remove", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                // Setting Alert Dialog Negative
                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                // Menampilkan AlertDialog
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                                break;

                            case R.id.edit:
                                // Membuat object bundel
                                bundle = new Bundle();

                                // Mengisi bundle degan data
                                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                                bundle.putString(Constant.KEY_NAMA_KELAS, kelasModel.getNama_kelas());
                                bundle.putString(Constant.KEY_NAMA_WALI, kelasModel.getNama_wali());

                                // Berpindah halaman dgn membawa data
                                context.startActivity(new Intent(context, UpdateKelasActivity.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                bundle.putString(Constant.KEY_NAMA_KELAS, kelasModel.getNama_kelas());
                context.startActivity(new Intent(context, MainSiswaActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelasModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNamaKelas)
        TextView tvNamaKelas;
        @BindView(R.id.tvNamaWali)
        TextView tvNamaWali;
        @BindView(R.id.cv)
        CardView cv;
        @BindView(R.id.overflow)
        ImageButton overflow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
