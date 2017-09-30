package com.example.drewthoennes.response;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SetupActivity extends AppCompatActivity {

    Button backButton;
    Button newSurveyButton;
    EditText endpointEdit;
    EditText questionEdit;
    EditText firstAnswerEdit;
    EditText secondAnswerEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setup);

        getIntent();

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://a.elnardu.me:8080/vote";
        final StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response == "") {
                    Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
                    intent.putExtra("question", questionEdit.getText().toString());
                    intent.putExtra("firstAnswer", firstAnswerEdit.getText().toString());
                    intent.putExtra("secondAnswer", secondAnswerEdit.getText().toString());
                    startActivity(intent);
                }
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
                params.put("name", "Elnard");

                return params;
            }
        };

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }
        });

        newSurveyButton = (Button) findViewById(R.id.newSurveyButton);
        newSurveyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(endpointEdit.getText().toString().equals("")) {
                    endpointEdit.setError("Field is required");
                }
                else if(questionEdit.getText().toString().equals("")) {
                    questionEdit.setError("Field is required");
                }
                else if(firstAnswerEdit.getText().toString().equals("")) {
                    firstAnswerEdit.setError("Field is required");
                }
                else if(secondAnswerEdit.getText().toString().equals("")) {
                    secondAnswerEdit.setError("Field is required");
                }
                else {
                    queue.add(postRequest);
                    Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
//                    intent.putExtra("question", questionEdit.getText().toString());
//                    intent.putExtra("firstAnswer", firstAnswerEdit.getText().toString());
//                    intent.putExtra("secondAnswer", secondAnswerEdit.getText().toString());
                    startActivity(intent);
                }
            }
        });

        endpointEdit = (EditText) findViewById(R.id.endpointEdit);
        questionEdit = (EditText) findViewById(R.id.questionEdit);
        firstAnswerEdit = (EditText) findViewById(R.id.firstAnswerEdit);
        secondAnswerEdit = (EditText) findViewById(R.id.secondAnswerEdit);
    }
}
