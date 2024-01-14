package com.example.rateprof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

    private List<String> buttonList;

    public ButtonAdapter(List<String> buttonList) {
        this.buttonList = buttonList;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        String buttonText = buttonList.get(position);
        holder.bindButton(buttonText);
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {

        private Button button;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }

        public void bindButton(String buttonText) {
            button.setText(buttonText);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ButtonDetailActivity.class);

                    intent.putExtra("BUTTON_TEXT", buttonText);

                    v.getContext().startActivity(intent);
                }
            });

        }
    }
}
