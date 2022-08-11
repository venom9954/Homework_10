package com.example.homework_10.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homework_10.R;
import com.example.homework_10.repository.LocalRepositoryImpl;
import com.example.homework_10.repository.NoteData;
import com.example.homework_10.repository.NotesSource;

public class NotesFragment extends Fragment implements OnItemClickListener {

    NotesAdapter notesAdapter;
    NotesSource data;

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecycler(view);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:{
                data.addNotesData(new NoteData("Заголовок новой заметки " + data.size(), "Описание новой заметки " + data.size()));
                notesAdapter.notifyItemInserted(data.size() -1);
                return true;
            }
            case R.id.action_clear:{
                data.clearNotesData();
                notesAdapter.notifyDataSetChanged();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.note_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int menuPosition = notesAdapter.getMenuPosition();
        switch (item.getItemId()){
            case R.id.action_update:{
                data.updateNoteData(menuPosition, new NoteData("Заголовок обновленной заметки " + data.size(), "Описание обновленной заметки " + data.size()));
                notesAdapter.notifyItemChanged(menuPosition);
                return true;
            }
            case R.id.action_delete:{
                data.deleteNoteData(menuPosition);
                notesAdapter.notifyItemRemoved(menuPosition);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }



    void initAdapter(){
        notesAdapter = new NotesAdapter(this);
        data = new LocalRepositoryImpl(requireContext().getResources()).init();
        notesAdapter.setData(data);
        notesAdapter.setOnItemClickListener(NotesFragment.this);
    }

    void initRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notesAdapter);

    }

    String[] getData(){
        String[] data = getResources().getStringArray(R.array.name_notes);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), "Нажали на " + data[position], Toast.LENGTH_SHORT).show();
    }
}