package com.group11.etimcqquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button chapter1,chapter2,chapter3,chapter4,chapter5,chapter6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chapter1 = findViewById(R.id.startbtn1);
        chapter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch1 = new Intent(HomeActivity.this, Chapter1.class);
                startActivity(ch1);
                finish();
            }
        });

        chapter2 = findViewById(R.id.startbtn2);
        chapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch2 = new Intent(HomeActivity.this, Chapter2.class);
                startActivity(ch2);
                finish();
            }
        });

        chapter3 = findViewById(R.id.startbtn3);
        chapter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch3 = new Intent(HomeActivity.this, Chapter3.class);
                startActivity(ch3);
                finish();
            }
        });

        chapter4 = findViewById(R.id.startbtn4);
        chapter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch4 = new Intent(HomeActivity.this, Chapter4.class);
                startActivity(ch4);
                finish();
            }
        });

        chapter5 = findViewById(R.id.startbtn5);
        chapter5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch5 = new Intent(HomeActivity.this, Chapter5.class);
                startActivity(ch5);
                finish();
            }
        });

        chapter6 = findViewById(R.id.startbtn6);
        chapter6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch6 = new Intent(HomeActivity.this, Chapter6.class);
                startActivity(ch6);
                finish();
            }
        });
    }
}