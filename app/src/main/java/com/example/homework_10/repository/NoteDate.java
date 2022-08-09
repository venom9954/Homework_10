package com.example.homework_10.repository;

public class NoteDate {
    private String name_note;
    private String descriptions;

    public NoteDate(String name_note, String descriptions) {
        this.name_note = name_note;
        this.descriptions = descriptions;
    }

    public String getName_note() {
        return name_note;
    }

    public String getDescriptions() {
        return descriptions;
    }
}
