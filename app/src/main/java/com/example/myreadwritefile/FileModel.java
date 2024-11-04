package com.example.myreadwritefile;

import androidx.annotation.NonNull;

public class FileModel {
    private String filename;
    private String data;

    // Default constructor
    public FileModel() {
    }

    // Constructor with parameters
    public FileModel(String filename, String data) {
        this.filename = filename;
        this.data = data;
    }

    // Getter and Setter for filename
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Getter and Setter for data
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Override toString for easy printing
    @NonNull
    @Override
    public String toString() {
        return "FileModel{" +
                "filename='" + filename + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

