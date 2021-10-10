package com.example.android.helloconstraint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import helloconstraint.R;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button mButtonZero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        mButtonZero = (Button) findViewById(R.id.button_zero);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if(mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
        if(mButtonZero != null) {
            mButtonZero.setBackgroundColor(0xFFF54296);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mButtonZero.setBackgroundTintList(ColorStateList.valueOf(0xFFF54296));
            }
        }
        if(mCount % 2 == 0) {
            view.setBackgroundColor(0xFFF58442);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setBackgroundTintList(ColorStateList.valueOf(0xFFF58442));
            }
        } else {
            view.setBackgroundColor(0xFF4287F5);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setBackgroundTintList(ColorStateList.valueOf(0xFF4287F5));
            }
        }
    }

    public void setCountToZero(View view) {
        mCount = 0;
        view.setBackgroundColor(0xFF666666);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackgroundTintList(ColorStateList.valueOf(0xFF666666));
        }
        if(mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
    }
}