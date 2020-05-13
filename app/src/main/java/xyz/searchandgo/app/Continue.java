package xyz.searchandgo.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Continue extends AppCompatActivity {
    final String[] arr = {"","Mainan", "Anak Muda", "Bangunan"};
    TextView continueText;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);
        final boolean isSuccess;
        // final String tes;
        isSuccess = MyApplication.getInstance().isSuccess; // false is the default

        setContentView(R.layout.activity_continue);
        continueText = (TextView) findViewById(R.id.continueText);
        continueButton = (Button) findViewById((R.id.contButton));

        if (isSuccess){
            continueText.setText("Success");
            continueButton.setText("Continue");
        } else {
            continueText.setText("Fail");
            continueButton.setText("Retry");
        }
        // continueText.setText(tes);
    }

    public void cont(View v){
        final boolean isSuccess = getIntent().getBooleanExtra("isSuccess",false);
        int level = MyApplication.getInstance().level;
        Intent myIntent = new Intent(getBaseContext(), LevelOne.class);
        if (isSuccess){
            if (level!=3) {
                level++;
                MyApplication.getInstance().level = level;
                myIntent = new Intent(getBaseContext(), LevelOne.class);
            }else {
                myIntent = new Intent(getBaseContext(), MainActivity.class);
            }
        }
        myIntent.putExtra("level", level);
        myIntent.putExtra("levelname", arr[level]);

        startActivity(myIntent);
    }

}
