package com.example.homework_10.repository;

import android.content.res.Resources;

import com.example.homework_10.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocalRepositoryImpl implements NotesSource {

    private List<NoteData> noteSource;
    private Resources resources;

    public LocalRepositoryImpl(Resources resources){
        noteSource = new ArrayList<NoteData>();
        this.resources = resources;
    }

    public LocalRepositoryImpl init(){
        String[] name_notes = resources.getStringArray(R.array.name_notes);
        String[] descriptions = resources.getStringArray(R.array.descriptions);

        for(int i = 0; i<name_notes.length; i++){
            noteSource.add(new NoteData(name_notes[i], descriptions[i], Calendar.getInstance().getTime()));
        }
        return this;
    }

    @Override
    public int size() {
        return noteSource.size();
    }

    @Override
    public List<NoteData> getAllNotesData() {
        return noteSource;
    }

    @Override
    public NoteData getNoteData(int position) {
        return noteSource.get(position);
    }

    @Override
    public void clearNotesData() {
        noteSource.clear();
    }

    @Override
    public void addNotesData(NoteData noteData) {
        noteSource.add(noteData);
    }

    @Override
    public void deleteNoteData(int position) {
        noteSource.remove(position);
    }

    @Override
    public void updateNoteData(int position, NoteData newNoteData) {
        noteSource.set(position, newNoteData);
    }
}
