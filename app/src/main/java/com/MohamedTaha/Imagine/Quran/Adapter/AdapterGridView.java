package com.MohamedTaha.Imagine.Quran.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.mvp.model.ModelSora;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterGridView extends RecyclerView.Adapter<AdapterGridView.ViewHolder> {
    private boolean isParts;
    private List<ModelSora> modelSoraList;
    private ClickListener clickListener;


    public AdapterGridView( List<ModelSora> modelSoraList,boolean isParts,ClickListener clickListener ) {
        this.isParts = isParts;
        this.modelSoraList = modelSoraList;
        this.clickListener = clickListener;
    }
    public interface ClickListener {
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_grid_view, null);
        ViewHolder viewHolder = new ViewHolder(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                if (clickListener != null) clickListener.onClick(row, position);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelSora modelSora = modelSoraList.get(position);
        //For change font type

        if (isParts) {
            holder.TVNameSora.setText(modelSora.getName_part());
            holder.TVNzolelsora.setVisibility(View.INVISIBLE);
        } else {
            holder.TVNameSora.setText(modelSora.getName_sora());
            holder.TVNzolelsora.setVisibility(View.VISIBLE);
            holder.TVNzolelsora.setText(modelSora.getNzol_elsora());
        }
    }

    @Override
    public int getItemCount() {
        return modelSoraList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TV_Name_Sora)
        TextView TVNameSora;
        @BindView(R.id.TV_Nzol_elsora)
        TextView TVNzolelsora;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}