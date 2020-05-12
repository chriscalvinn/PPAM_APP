package xyz.searchandgo.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class LevelOne extends AppCompatActivity {
    final String arr[] = {"","Mainan", "Anak Muda", "Bangunan"};
    int level = 1;
    TextView levelText, levelNum, uploadButton;


    String testJson = "{'success':'true','message':'benar''}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        levelNum = (TextView) findViewById(R.id.level);
        levelNum.setText("Level "+ level);
        levelText = (TextView) findViewById(R.id.judulLevel);
        levelText.setText(arr[level]);
        uploadButton = (TextView) findViewById(R.id.uploadButton);
        uploadButton.setText("Upload Picture");

        try {
            JSONObject reader = new JSONObject(testJson);

            boolean bool = reader.getBoolean("success");
            String message = reader.getString("message");

            if (bool) {
                level++;
            }
        } catch (JSONException e){
            throw new RuntimeException(e);
        }
    }


    public void checkPict(View v){

        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);

        myIntent.putExtra("nextLevel", arr[level]);
        startActivity(myIntent);
    }

}
