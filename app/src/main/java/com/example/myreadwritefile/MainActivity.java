package com.example.myreadwritefile;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myreadwritefile.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set click listeners
        binding.buttonNew.setOnClickListener(this);
        binding.buttonOpen.setOnClickListener(this);
        binding.buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_new) {
            newFile();
        } else if (view.getId() == R.id.button_open) {
            showList();
        } else if (view.getId() == R.id.button_save) {
            saveFile();
        }
    }

    private void newFile() {
        binding.editTitle.setText("");
        binding.editFile.setText("");
        Toast.makeText(this, "Clearing file", Toast.LENGTH_SHORT).show();
    }

    private void showList() {
        String[] allFiles = fileList();
        List<String> filteredItems = new ArrayList<>();

        for (String fileName : allFiles) {
            if (!fileName.equals("profileInstalled")) {
                filteredItems.add(fileName);
            }
        }

        String[] items = filteredItems.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih file yang diinginkan");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                loadData(items[item]);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadData(String title) {
        FileModel fileModel = FileHelper.getInstance().readFromFile(this, title);
        binding.editTitle.setText(fileModel.getFilename());
        binding.editFile.setText(fileModel.getData());
        Toast.makeText(this, "Loading " + fileModel.getFilename() + " data", Toast.LENGTH_SHORT).show();
    }

    private void saveFile() {
        if (binding.editTitle.getText().toString().isEmpty()) {
            Toast.makeText(this, "Title harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else if (binding.editFile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Kontent harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else {
            String title = binding.editTitle.getText().toString();
            String text = binding.editFile.getText().toString();

            FileModel fileModel = new FileModel();
            fileModel.setFilename(title);
            fileModel.setData(text);

            FileHelper.getInstance().writeToFile(fileModel, this);
            Toast.makeText(this, "Saving " + fileModel.getFilename() + " file", Toast.LENGTH_SHORT).show();
        }
    }
}