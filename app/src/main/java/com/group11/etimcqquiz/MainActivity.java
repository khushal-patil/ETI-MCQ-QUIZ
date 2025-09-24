package com.group11.etimcqquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread timer = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3500);
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                    super.run();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        View appName = findViewById(R.id.group_name);
        new Thread() {
            @Override
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }
}