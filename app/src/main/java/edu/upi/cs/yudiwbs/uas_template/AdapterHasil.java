package edu.upi.cs.yudiwbs.uas_template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//untuk recylerview
public class AdapterHasil extends RecyclerView.Adapter<ViewHolderHasil> {

    ArrayList<Hasil> alHasil;

    public AdapterHasil(ArrayList<Hasil> alHasil) {
        this.alHasil = alHasil;
    }

    @NonNull
    @Override
    public ViewHolderHasil onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hasil_row, parent, false);
        // jangan sampai lupa return viewholder, akan menyebabkan bug yang susah ditrace
        return new ViewHolderHasil(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHasil holder, int position) {
        Hasil m = alHasil.get(position);
        holder.tvHasil.setText(m.getHasil());
    }

    @Override
    public int getItemCount() {
        return alHasil.size();
    }
}
