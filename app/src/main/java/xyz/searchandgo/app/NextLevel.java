package xyz.searchandgo.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class NextLevel extends AppCompatActivity {
    final String arr[] = {"mainan", "anak muda", "bangunan"};
    int level = 0;
    //JSONObject response = new JSONObject();
    //response.put("success", "true");
    //response.put("message", "benar");

    String testJson = "{'success':'true','message':'benar''}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);
    }


    public void nextLevel(View v) throws JSONException {
        JSONObject reader = new JSONObject(testJson);

        boolean bool = reader.getBoolean("success");
        String message = reader.getString("message");

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
