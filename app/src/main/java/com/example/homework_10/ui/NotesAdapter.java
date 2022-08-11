package com.example.homework_10.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework_10.R;
import com.example.homework_10.repository.NoteData;
import com.example.homework_10.repository.NotesSource;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private NotesSource notesSource;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(NotesSource notesSource) {
        this.notesSource = notesSource;
        notifyDataSetChanged(); // команда адаптеру отрисовать все полученные данные
    }

    public NotesAdapter(NotesSource notesSource) {

        this.notesSource = notesSource;
    }

    public NotesAdapter() {
    }

    @NonNull
    @Override
    // метод создает ViewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_notes_cardview_item, parent, false));
    }

    @Override
    // метод связывает ViewHolder с контентом
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(notesSource.getNoteData(position));
    }

    @Override
    public int getItemCount() { //размер массива с которым будет работать Adapter

        return notesSource.size();
    }

    // кастомный ViewHolder (управляет макетом)
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNameNotes;
        private TextView textViewDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameNotes = (TextView) itemView.findViewById(R.id.name_notes);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
       /*     textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });*/
        }
            // Метод связывает контент с макетом
        public void bindContentWithLayout(NoteData content){

            textViewNameNotes.setText(content.getName_note());
            textViewDescription.setText(content.getDescriptions());
        }

    }


}
