package com.example.homework_10.publisher;

import com.example.homework_10.repository.NoteData;

public interface Observer {
   void receiveMessage(NoteData noteData);
}
