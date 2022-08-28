package com.example.homework_10.repository;


import com.google.firebase.Timestamp;

import java.util.Map;
import java.util.HashMap;

public class NoteDataMapping {
    public static class Fields{
        public static final String DATE = "date";
        public static final String NAME_NOTE = "name_note";
        public static final String DESCRIPTION = "description";
    }

    public static NoteData toNoteData(String id, Map<String, Object> doc){
        Timestamp timeStamp = (Timestamp)doc.get(Fields.DATE);
        NoteData answer = new NoteData((String) doc.get(Fields.NAME_NOTE),
                (String) doc.get(Fields.DESCRIPTION),
                timeStamp.toDate());
        answer.setId(id);
        return answer;
    }

    public static Map<String, Object> toDocument (NoteData cardData){
        Map<String, Object> answer = new HashMap<>();
        answer.put(Fields.NAME_NOTE, cardData.getName_note());
        answer.put(Fields.DESCRIPTION, cardData.getDescriptions());
        answer.put(Fields.DATE, cardData.getDate());
        return answer;
    }

}
