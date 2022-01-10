package com.example.android.fileexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TextActivity extends AppCompatActivity {

    TextView txtFilePath;
    TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        txtFilePath = findViewById(R.id.txt_file_path);
        txtContent = findViewById(R.id.txt_content);

        String filePath = getIntent().getStringExtra("filePath");
        txtFilePath.setText(filePath);

        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                Log.v("test", line);
                builder.append(line + "\n");
            }
            reader.close();

            txtContent.setText(builder.toString());
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}