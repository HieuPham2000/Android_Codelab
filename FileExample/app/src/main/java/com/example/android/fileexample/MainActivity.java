package com.example.android.fileexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtSdPath;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSdPath = findViewById(R.id.txt_sd_path);
        listView = findViewById(R.id.list_view);
        List<String> items = new ArrayList<>();

        // Kiểm tra và đề nghị người dùng cấp quyền nếu cần
        if(Build.VERSION.SDK_INT >= 23) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "READ_EXTERNAL_STORAGE: Permission granted.");
            } else {
                Log.v("TAG", "READ_EXTERNAL_STORAGE: Permission denied.");
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
            }
        }

        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        txtSdPath.setText(sdPath);

        File[] files = new File(sdPath).listFiles();
        if(files != null) {
            for(File f: files) {
                items.add(f.getName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, R.id.txt_file_item, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("TAG", "Item " + i + " clicked: " + items.get(i));
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + items.get(i);
                File folderFile = new File(path);
                if (folderFile.isDirectory()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("directoryPath", path);
                    startActivity(intent);
                } else if (folderFile.isFile()) {
                    Intent intent = new Intent(MainActivity.this, TextActivity.class);
                    intent.putExtra("filePath", path);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1000) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "READ_EXTERNAL_STORAGE: Permission granted.");
            } else {
                Log.v("TAG", "READ_EXTERNAL_STORAGE: Permission denied.");
            }
        }
    }
}