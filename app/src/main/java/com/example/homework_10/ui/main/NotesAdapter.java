package com.example.homework_10.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework_10.R;
import com.example.homework_10.repository.NoteData;
import com.example.homework_10.repository.NotesSource;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private NotesSource notesSource;

    OnItemClickListener onItemClickListener;

    Fragment fragment;

    private int menuPosition;

    public int getMenuPosition() {
        return menuPosition;
    }

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

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
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


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    return false;
                }
            });
            fragment.registerForContextMenu(itemView);
        }
            // Метод связывает контент с макетом
        public void bindContentWithLayout(NoteData content){

            textViewNameNotes.setText(content.getName_note());
            textViewDescription.setText(content.getDescriptions());
        }

    }


}
