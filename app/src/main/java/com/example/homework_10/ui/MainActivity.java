package com.example.homework_10.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homework_10.R;
import com.example.homework_10.publisher.Publisher;
import com.example.homework_10.ui.main.NotesFragment;

public class MainActivity extends AppCompatActivity {

    private Publisher publisher;

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        publisher = new Publisher();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, NotesFragment.newInstance()).commit();
        }
    }
}