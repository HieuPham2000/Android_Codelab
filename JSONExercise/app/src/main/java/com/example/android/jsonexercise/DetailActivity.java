package com.example.android.jsonexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        ImageView imgPhoto = findViewById(R.id.img_photo);
        TextView txtUsername = findViewById(R.id.txt_username_detail);
        TextView txtName = findViewById(R.id.txt_name_detail);
        TextView txtEmail = findViewById(R.id.txt_email_detail);
        TextView txtAddress = findViewById(R.id.txt_address_detail);
        TextView txtPhone = findViewById(R.id.txt_phone_detail);
        TextView txtCompany = findViewById(R.id.txt_company_detail);

        ItemModel itemModel = (ItemModel) getIntent().getSerializableExtra("itemModel");
        Picasso.with(this).load(itemModel.getAvatar().get("photo")).into(imgPhoto);
        txtUsername.setText(itemModel.getUsername());
        txtName.setText(itemModel.getName());
        txtEmail.setText(itemModel.getEmail());
        txtAddress.setText(itemModel.getAddressString());
        txtPhone.setText(itemModel.getPhone());
        txtCompany.setText(itemModel.getCompanyString());
    }
}