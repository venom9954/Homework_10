package com.example.homework_10.repository;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class NoteData implements Parcelable {
    public void setName_note(String name_note) {
        this.name_note = name_note;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    private String name_note;
    private String descriptions;
    private Date date;

    protected NoteData(Parcel in) {
        name_note = in.readString();
        descriptions = in.readString();
        date = new Date(in.readLong());
    }

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NoteData(String name_note, String descriptions, Date date) {
        this.name_note = name_note;
        this.descriptions = descriptions;
        this.date = date;
    }

    public String getName_note() {
        return name_note;
    }

    public String getDescriptions() {
        return descriptions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name_note);
        parcel.writeString(descriptions);
        parcel.writeLong(date.getTime());
    }
}
