package com.example.rgr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rgr.data.structure.CircularList;

public class CustomAdapterCircularList<T> extends RecyclerView.Adapter<CustomAdapterCircularList.ViewHolder> {
    private CircularList<T> data;
    static Integer position;
    public CustomAdapterCircularList(CircularList<T> data){
        this.data = data;
    }

    @Override
    public CustomAdapterCircularList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(CustomAdapterCircularList.ViewHolder holder, int position) {
        holder.textView.setText(this.data.getDataAtPosition(position).toString());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = view.findViewById(R.id.textview);
        }

        @Override
        public void onClick(View view) {
            position = getLayoutPosition();
            Toast.makeText(view.getContext(), "position : " + position + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}