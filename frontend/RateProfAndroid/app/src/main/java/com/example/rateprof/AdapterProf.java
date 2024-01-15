package com.example.rateprof;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProf extends RecyclerView.Adapter<AdapterProf.ViewHolder> {
    private List<String> names;
    private Context context;

    public AdapterProf(List<String> names, Context context) {
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = names.get(position);
        holder.buttonItem.setText(name);

        holder.buttonItem.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfActivity.class);
            intent.putExtra("SELECTED_NAME", name);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(names !=null) {
            return names.size();
        }
        return -1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button buttonItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonItem = itemView.findViewById(R.id.buttonItem);
        }
    }
}

