package com.example.drewthoennes.response;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SurveyActivity extends AppCompatActivity {

    TextView questionText;
    Button firstButton;
    Button secondButton;

    String question;
    String firstAnswer;
    String secondAnswer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_survey);

        question = getIntent().getStringExtra("question").toString();
        firstAnswer = getIntent().getStringExtra("firstAnswer").toString();
        secondAnswer = getIntent().getStringExtra("secondAnswer").toString();

        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(question);

        firstButton = (Button) findViewById(R.id.firstButton);
        firstButton.setText(firstAnswer);

        secondButton = (Button) findViewById(R.id.secondButton);
        secondButton.setText(secondAnswer);
    }
}
