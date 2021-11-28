package com.example.android.inbox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    List<ItemModel> items;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        ListView listItem = findViewById(R.id.list_item);
        listItem.setAdapter(adapter);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#dd4237")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void init() {
        items = new ArrayList<>();
        Faker faker = new Faker();
        for(int i = 1; i < 20; ++i) {
            items.add(new ItemModel(
                    R.drawable.ic_launcher_background,
                    faker.name.name(),
                    faker.lorem.sentence(),
                    faker.lorem.sentence(),
                    "11:30 AM"));
        }

        adapter = new ItemAdapter(items);
    }
}