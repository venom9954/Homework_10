package com.example.homework_10.ui.editor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework_10.R;
import com.example.homework_10.repository.NoteData;
import com.example.homework_10.ui.MainActivity;


public class NoteFragment extends Fragment {

    NoteData noteData;

    public static NoteFragment newInstance(NoteData noteData) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable("noteData", noteData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            noteData = getArguments().getParcelable("noteData");
            noteData.setName_note("Test1");
            noteData.setDescriptions("Test1");
            ((MainActivity) requireActivity()).getPublisher().sendMessage(noteData);
            ((MainActivity) requireActivity()).getSupportFragmentManager().popBackStack();
        }

    }
}