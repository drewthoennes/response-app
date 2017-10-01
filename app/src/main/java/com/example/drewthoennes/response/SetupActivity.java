package com.example.drewthoennes.response;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.io.FileDescriptor.in;

public class SetupActivity extends AppCompatActivity {

    com.beardedhen.androidbootstrap.BootstrapButton backButton;
    com.beardedhen.androidbootstrap.BootstrapButton newSurveyButton;
    EditText endpointEdit;
    EditText tagEdit;
    EditText questionEdit;
    EditText firstAnswerEdit;
    EditText secondAnswerEdit;

    protected void onCreate(Bundle savedInstanceState) {
        final RequestQueue queue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setup);

        getIntent();

        backButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }
        });

        newSurveyButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.newSurveyButton);
        newSurveyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(tagEdit.getText().toString().equals("")) {
                    tagEdit.setError("Field is required");
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



                    String url = "http://a.elnardu.me:8080/polls/create";
                    final StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println(response.replaceAll("^\"|\"$", ""));
                            nextActivity(response);

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
                            params.put("question", questionEdit.getText().toString());
                            params.put("tag", tagEdit.getText().toString());
                            params.put("goodAnswer", firstAnswerEdit.getText().toString());
                            params.put("badAnswer", secondAnswerEdit.getText().toString());

                            return params;
                        }
                    };

//                    String filename = "recentSurveys.txt";
//
//                    File recentSurveys = new File("recentSurveys.txt");
//                    try {
//                        if(recentSurveys.exists()) {
//                            JSONArray surveysObject = new JSONArray(recentSurveys.toString());
//                            JSONArray questionsObject = surveysObject.getJSONArray(0); // get question
//
//                        }
//                        else {
//
//                        }
//                    } catch(Exception exception) {
//
//                    }
                    queue.add(postRequest);
                }
            }
        });

        tagEdit = (EditText) findViewById(R.id.tagEdit);
        questionEdit = (EditText) findViewById(R.id.questionEdit);
        firstAnswerEdit = (EditText) findViewById(R.id.firstAnswerEdit);
        secondAnswerEdit = (EditText) findViewById(R.id.secondAnswerEdit);
    }

    void nextActivity(String pollId) {
        Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
        intent.putExtra("question", questionEdit.getText().toString());
        intent.putExtra("tag", tagEdit.getText().toString());
        intent.putExtra("firstAnswer", firstAnswerEdit.getText().toString());
        intent.putExtra("secondAnswer", secondAnswerEdit.getText().toString());
        intent.putExtra("pollId", pollId);
        startActivity(intent);
    }
}
