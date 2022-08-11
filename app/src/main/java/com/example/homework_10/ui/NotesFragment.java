package com.example.homework_10.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
                data.addNotesData(new NoteData("Заголовок новой карточки " + data.size(), "Описание новой карточки " + data.size()));
                notesAdapter.notifyDataSetChanged();
            }
            case R.id.action_clear:{

            }
        }

        return super.onOptionsItemSelected(item);
    }

    void initAdapter(){
        notesAdapter = new NotesAdapter();
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