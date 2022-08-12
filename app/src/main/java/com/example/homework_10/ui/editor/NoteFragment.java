package com.example.homework_10.ui.editor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homework_10.R;
import com.example.homework_10.repository.NoteData;
import com.example.homework_10.ui.MainActivity;

import java.util.Calendar;
import java.util.Date;


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
    Calendar calendar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            noteData = getArguments().getParcelable("noteData");
            ((EditText) view.findViewById(R.id.inputTitle)).setText(noteData.getName_note());
            ((EditText) view.findViewById(R.id.inputDescription)).setText(noteData.getDescriptions());

            calendar = Calendar.getInstance();
            calendar.setTime(noteData.getDate());
            ((DatePicker) view.findViewById(R.id.inputDate)).init(calendar.get(Calendar.YEAR) - 1,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), null);

            ((DatePicker) view.findViewById(R.id.inputDate)).setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    calendar.set(Calendar.YEAR, i);
                    calendar.set(Calendar.MONTH, i1);
                    calendar.set(Calendar.DAY_OF_MONTH, i2);
                }
            });

            view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View otherView) {
                    noteData.setName_note(((EditText) view.findViewById(R.id.inputTitle)).getText().toString());
                    noteData.setDescriptions(((EditText) view.findViewById(R.id.inputDescription)).getText().toString());

                    DatePicker datePicker = ((DatePicker) view.findViewById(R.id.inputDate));
                    calendar.set(Calendar.YEAR, datePicker.getYear());
                    calendar.set(Calendar.MONTH, datePicker.getMonth());
                    calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                    noteData.setDate(calendar.getTime());

                    ((MainActivity) requireActivity()).getPublisher().sendMessage(noteData);
                    ((MainActivity) requireActivity()).getSupportFragmentManager().popBackStack();
                }
            });

        }

    }

}