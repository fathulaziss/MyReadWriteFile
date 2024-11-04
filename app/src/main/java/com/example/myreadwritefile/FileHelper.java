package com.example.myreadwritefile;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/** @noinspection ALL*/
public class FileHelper {

    // Private constructor to prevent instantiation
    private FileHelper() {}

    // Singleton instance
    private static final FileHelper INSTANCE = new FileHelper();

    // Getter for singleton instance
    public static FileHelper getInstance() {
        return INSTANCE;
    }

    // Method to write to file
    public void writeToFile(FileModel fileModel, Context context) {
        try (FileOutputStream fos = context.openFileOutput(fileModel.getFilename(), Context.MODE_PRIVATE)) {
            if (fileModel.getData() != null) {
                fos.write(fileModel.getData().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read from file
    public FileModel readFromFile(Context context, String filename) {
        FileModel fileModel = new FileModel();
        fileModel.setFilename(filename);

        StringBuilder data = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(filename);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {

            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            fileModel.setData(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileModel;
    }
}
