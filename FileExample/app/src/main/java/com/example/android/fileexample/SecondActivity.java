package com.example.android.fileexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView txtDirPath;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtDirPath = findViewById(R.id.txt_dir_path);
        listView2 = findViewById(R.id.list_view2);
        listView2.setEmptyView(findViewById(R.id.txt_empty_notice));
        List<String> items = new ArrayList<>();

        Intent intent = getIntent();
        String directoryPath = intent.getStringExtra("directoryPath");
        txtDirPath.setText(directoryPath);

        File folder = new File(directoryPath);
        Log.v("TAG", folder.getAbsolutePath());

        File[] files = folder.listFiles();
        if(files != null) {
            for (File f : files) {
                items.add(f.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, R.id.txt_file_item, items);
        listView2.setAdapter(adapter);
    }
}