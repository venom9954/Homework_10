package com.example.homework_10.repository;

import java.util.List;

public interface NotesSource {
    int size();
    List<NoteData> getAllNotesData();
    NoteData getNoteData(int position);

    void clearNotesData();
    void addNotesData(NoteData noteData);

    void deleteNoteData(int position);
    void updateNoteData(int position, NoteData newNoteData);
}
