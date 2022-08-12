package com.example.homework_10.publisher;

import com.example.homework_10.repository.NoteData;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers;

    public Publisher(){
        observers = new ArrayList<>();
    }

    public void  subscribe(Observer observer){
        observers.add(observer);
    }

    public void  unsubscribe(Observer observer){
        observers.remove(observer);
    }

    public void sendMessage(NoteData noteData){
        for(Observer observer:observers){
            observer.receiveMessage(noteData);
        }
    }
}
