package com.example.homework_10.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.homework_10.R;
import com.example.homework_10.publisher.Observer;
import com.example.homework_10.repository.LocalRepositoryImpl;
import com.example.homework_10.repository.NoteData;
import com.example.homework_10.repository.NotesSource;
import com.example.homework_10.ui.MainActivity;
import com.example.homework_10.ui.editor.NoteFragment;

import java.util.Calendar;

public class NotesFragment extends Fragment implements OnItemClickListener {

    NotesAdapter notesAdapter;
    NotesSource data;
    RecyclerView recyclerView;

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

    private void initRadioGroup(View view){
        view.findViewById(R.id.sourceArrays).setOnClickListener(listener);
        view.findViewById(R.id.sourceSP).setOnClickListener(listener);
        view.findViewById(R.id.sourceGF).setOnClickListener(listener);

        switch (getCurrentSource()){
            case SOURCE_ARRAY:{
                ((RadioButton)view.findViewById(R.id.sourceArrays)).setChecked(true);
                break;
            }
            case SOURCE_SP:{
                ((RadioButton)view.findViewById(R.id.sourceSP)).setChecked(true);
                break;
            }
            case SOURCE_GF:{
                ((RadioButton)view.findViewById(R.id.sourceGF)).setChecked(true);
                break;
            }
        }
    }

    static final int SOURCE_ARRAY = 1;
    static final int SOURCE_SP = 2;
    static final int SOURCE_GF = 3;

    static String KEY_SP_S1 = "key_1";
    static String KEY_SP_S1_CELL_C1 = "s1_cell";

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                switch (view.getId()){
                    case R.id.sourceArrays:
                        setCurrentSource(SOURCE_ARRAY);
                        break;
                    case R.id.sourceSP:
                        setCurrentSource(SOURCE_SP);
                        break;
                    case R.id.sourceGF:
                        setCurrentSource(SOURCE_GF);
                        break;
                }
        }
    };

    void setCurrentSource(int currentSource){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(KEY_SP_S1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SP_S1_CELL_C1, currentSource);
        editor.apply();
    }

    int getCurrentSource(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(KEY_SP_S1, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_SP_S1_CELL_C1, SOURCE_ARRAY);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecycler(view);
        setHasOptionsMenu(true);
        initRadioGroup(view);

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
                data.addNotesData(new NoteData("Заголовок новой заметки " + data.size(), "Описание новой заметки " + data.size(), Calendar.getInstance().getTime()));
                notesAdapter.notifyItemInserted(data.size() -1);
                recyclerView.smoothScrollToPosition(data.size() -1);
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
            case R.id.action_update: {
                Observer observer = new Observer() {
                    @Override
                    public void receiveMessage(NoteData noteData) {
                        ((MainActivity) requireActivity()).getPublisher().unsubscribe(this);
                        data.updateNoteData(menuPosition, noteData);
                        notesAdapter.notifyItemChanged(menuPosition);
                    }
                };
                ((MainActivity) requireActivity()).getPublisher().subscribe(observer);
                ((MainActivity) requireActivity()).getSupportFragmentManager().beginTransaction().add(R.id.container, NoteFragment.newInstance(data.getNoteData(menuPosition))).addToBackStack("").commit();
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
        recyclerView = view.findViewById(R.id.recyclerView);
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