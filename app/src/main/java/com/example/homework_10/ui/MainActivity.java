package com.example.homework_10.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homework_10.R;
import com.example.homework_10.ui.NotesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, NotesFragment.newInstance()).commit();
        }
    }
}