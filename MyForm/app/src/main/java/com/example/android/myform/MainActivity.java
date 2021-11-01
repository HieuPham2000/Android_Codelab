package com.example.android.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitForm(View view) {
        EditText name = findViewById(R.id.input_name);
        EditText studentCode = findViewById(R.id.input_student_code);
        EditText birthday = findViewById(R.id.input_birthday);
        EditText phoneNumber = findViewById(R.id.input_phone_number);
        EditText email = findViewById(R.id.input_email);
        if(name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Họ tên không được để trống!", Toast.LENGTH_SHORT).show();
            name.requestFocus();
            return;
        }
        if(studentCode.getText().toString().isEmpty()) {
            Toast.makeText(this, "MSSV không được để trống!", Toast.LENGTH_SHORT).show();
            studentCode.requestFocus();
            return;
        }
        if(birthday.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ngày sinh không được để trống!", Toast.LENGTH_SHORT).show();
            birthday.requestFocus();
            return;
        }
        if(phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Số điện thoại không được để trống!", Toast.LENGTH_SHORT).show();
            phoneNumber.requestFocus();
            return;
        }
        if(email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email không được để trống!", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }

        Toast.makeText(this, "Submit thông tin thành công!", Toast.LENGTH_SHORT).show();
    }
}