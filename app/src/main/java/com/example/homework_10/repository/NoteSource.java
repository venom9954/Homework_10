package com.example.homework_10.repository;

import java.util.List;

public interface NoteSource {
    int size();
    List<NoteDate> getAllNotesDate();
    NoteDate getNoteDate(int position);
}
