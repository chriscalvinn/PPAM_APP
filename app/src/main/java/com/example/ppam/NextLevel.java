package com.example.ppam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

public class NextLevel extends AppCompatActivity {
    final String arr[] = {"mainan", "anak muda", "bangunan"};
    int level = 0;
    JSONObject response = new JSONObject();
    response.put("success", "true");
    response.put("message", "benar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);
    }

    public void nextLevel(View v){
        JSONObject reader = new JSONObject(response);
        boolean bool = reader.getBoolean("success");
        message = reader.getString("message");

        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        if (bool){
            level++;
        }
        TextView levelText = (TextView) findViewById(R.id.Level);
        levelText.setText("Level "+level);

        myIntent.putExtra("nextLevel", arr[level]);
        startActivity(myIntent);
    }
}
