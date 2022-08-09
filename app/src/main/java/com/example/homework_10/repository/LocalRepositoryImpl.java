package com.example.homework_10.repository;

import android.content.res.Resources;

import com.example.homework_10.R;

import java.util.ArrayList;
import java.util.List;

public class LocalRepositoryImpl implements NoteSource{

    private List<NoteDate> noteSource;
    private Resources resources;

    public LocalRepositoryImpl(Resources resources){
        noteSource = new ArrayList<NoteDate>();
        this.resources = resources;
    }

    public LocalRepositoryImpl init(){
        String[] name_notes = resources.getStringArray(R.array.name_notes);
        String[] descriptions = resources.getStringArray(R.array.descriptions);

        for(int i = 0; i<name_notes.length; i++){
            noteSource.add(new NoteDate(name_notes[i], descriptions[i]));
        }
        return this;
    }

    @Override
    public int size() {
        return noteSource.size();
    }

    @Override
    public List<NoteDate> getAllNotesDate() {
        return noteSource;
    }

    @Override
    public NoteDate getNoteDate(int position) {
        return noteSource.get(position);
    }
}
