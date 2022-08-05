package com.example.homework_10;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private String[] data;

    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged(); // команда адаптеру отрисовать все полученные данные
    }

    public NotesAdapter(String[] data) {
        this.data = data;
    }

    public NotesAdapter() {
    }

    @NonNull
    @Override
    // метод создает ViewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_notes_recycler_item, parent, false));
    }

    @Override
    // метод связывает ViewHolder с контентом
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(data[position]);
    }

    @Override
    public int getItemCount() { //размер массива с которым будет работать Adapter
        return data.length;
    }

    // кастомный ViewHolder (управляет макетом)
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
            // Метод связывает контент с макетом
        public void bindContentWithLayout(String content){
            textView.setText(content);
        }

    }


}
