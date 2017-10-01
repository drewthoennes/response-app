package com.example.drewthoennes.response;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    com.beardedhen.androidbootstrap.BootstrapButton newSurveyButton;
    com.beardedhen.androidbootstrap.BootstrapButton recentButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        newSurveyButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.newSurveyButton);
        newSurveyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetupActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        recentButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.recentButton);
        recentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecentActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }
}
