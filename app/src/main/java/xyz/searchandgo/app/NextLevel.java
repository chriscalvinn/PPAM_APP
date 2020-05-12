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

public class NextLevel extends AppCompatActivity {
    final String arr[] = {"","Mainan", "Anak Muda", "Bangunan"};
    int level = 1;
    TextView levelText, levelNum;
    Button butt;

    String testJson = "{'success':'true','message':'benar''}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);
        levelNum = (TextView) findViewById(R.id.level);
        levelNum.setText("Level "+ level);
        levelText = (TextView) findViewById(R.id.judulLevel);
        levelText.setText(arr[level]);
        butt = (Button) findViewById(R.id.nextLevelButton);
        butt.setText("continue");
    }


    public void nextLevel(View v) throws JSONException {
        JSONObject reader = new JSONObject(testJson);

        boolean bool = reader.getBoolean("success");
        String message = reader.getString("message");

        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        if (bool){
            level++;
        }

        myIntent.putExtra("nextLevel", arr[level]);
        startActivity(myIntent);
    }

}
