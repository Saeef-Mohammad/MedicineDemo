package com.saeefmd.official.medicinedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saeefmd.official.medicinedemo.R;
import com.saeefmd.official.medicinedemo.model.Medicine;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Medicine> mListMedicines;
    private Context mContext;

    public MedicineAdapter(List<Medicine> mListMedicines, Context mContext) {
        this.mListMedicines = mListMedicines;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_medicines, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder)holder).drugText.setText(mListMedicines.get(position).getDrugs());
        ((MyViewHolder)holder).pharmacologyText.setText(mListMedicines.get(position).getPharmacology());
        /*((MyViewHolder)holder).sideEffectText.setText(mListMedicines.get(position).getSideEffects());
        ((MyViewHolder)holder).storageText.setText(mListMedicines.get(position).getStorage());*/
    }

    @Override
    public int getItemCount() {
        return mListMedicines.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView drugText;
        TextView pharmacologyText;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            drugText = itemView.findViewById(R.id.text_view_drug);
            pharmacologyText = itemView.findViewById(R.id.text_view_pharmacology);
        }
    }
}
