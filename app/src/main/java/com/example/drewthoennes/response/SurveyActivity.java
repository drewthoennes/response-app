package com.example.drewthoennes.response;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.duration;

public class SurveyActivity extends AppCompatActivity {

    com.beardedhen.androidbootstrap.BootstrapButton backButton;
    TextView questionText;
    com.beardedhen.androidbootstrap.BootstrapButton firstButton;
    com.beardedhen.androidbootstrap.BootstrapButton secondButton;

    String question;
    String tag;
    String firstAnswer;
    String secondAnswer;
    String choice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_survey);

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://a.elnardu.me:8080/vote"; // CHANGE THIS
        final StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("tag", tag);
                params.put("sentiment", choice);

                return params;
            }
        };

        backButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        question = getIntent().getStringExtra("question").toString();
        tag = getIntent().getStringExtra("tag").toString();
        firstAnswer = getIntent().getStringExtra("firstAnswer").toString();
        secondAnswer = getIntent().getStringExtra("secondAnswer").toString();

        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(question);

        firstButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.firstButton);
        firstButton.setText(firstAnswer);
        firstButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choice = "1";
                queue.add(postRequest);
                Snackbar.make(findViewById(R.id.activity_survey), "Answer recorded. Thank you for your response!", Snackbar.LENGTH_SHORT).show();
            }
        });

        secondButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.secondButton);
        secondButton.setText(secondAnswer);
        secondButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choice = "0";
                queue.add(postRequest);
                Snackbar.make(findViewById(R.id.activity_survey), "Answer recorded. Thank you for your response!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
