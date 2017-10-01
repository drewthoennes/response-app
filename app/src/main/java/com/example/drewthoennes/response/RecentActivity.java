package com.example.drewthoennes.response;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecentActivity extends AppCompatActivity {

    com.beardedhen.androidbootstrap.BootstrapButton backButton;

    ArrayList<String> recentSearchesList;
    ArrayAdapter<String> adapter;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recent);

        backButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        final String[][] searchData = {{"How was the food?", "food", "Amazing", "Could've been better"}, {"Were you able to get help when you needed it?", "support", "Yes", "No"}, {"How were the songs at the event?", "music", "Really good", "Could've been better"}};
        String[] recentSearches = new String[searchData.length];
        for(int i = 0; i < searchData.length; i++) {
            recentSearches[i] = searchData[i][0];
        }
        recentSearchesList = new ArrayList<String>(Arrays.asList(recentSearches));
        adapter = new ArrayAdapter<String>(this, R.layout.recent_search, R.id.search, recentSearchesList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
               intent.putExtra("question", searchData[position][0]);
               intent.putExtra("tag", searchData[position][1]);
               intent.putExtra("firstAnswer", searchData[position][2]);
               intent.putExtra("secondAnswer", searchData[position][3]);
               startActivity(intent);
           }
        });
    }
}
