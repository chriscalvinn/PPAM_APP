package com.example.ppam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;

public class NextLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);
    }

    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        //myIntent.putExtra("nextLevel", "Barcelona");
        startActivity(myIntent);
    }
}
