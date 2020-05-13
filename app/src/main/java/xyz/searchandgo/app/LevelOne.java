package xyz.searchandgo.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.util.Base64;

public class LevelOne extends AppCompatActivity {

    final String[] arr = {"","Banner", "Anak Muda", "Bangunan"};
    int level = 1;
    TextView levelText, levelNum, uploadButton, checkButton;
    ImageView imageView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    String testJson = "{'success':'true','message':'benar''}";
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        level = MyApplication.getInstance().level;
        imageView = (ImageView) findViewById(R.id.image);

        levelNum = (TextView) findViewById(R.id.level);
        levelNum.setText("Level "+ level);
        levelText = (TextView) findViewById(R.id.judulLevel);
        levelText.setText(arr[level]);
        uploadButton = (TextView) findViewById(R.id.uploadButton);
        uploadButton.setText("Select Image");
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        checkButton = (TextView) findViewById(R.id.checkButton);
        checkButton.setText("Check Image");
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                checkButton.setEnabled(true);
            }
            catch (FileNotFoundException ex) {
                // insert code to run when exception occurs
            }
        }
    }


    public void checkPict(View v) throws JSONException, FileNotFoundException {
        final Intent myIntent = new Intent(this, Continue.class);

        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.searchandgo.xyz/?level=";
        url += level;
        JSONObject params = new JSONObject();
        params.put("image", encodedImage);

        checkButton.setEnabled(false);
        checkButton.setText("Uploading!");
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            MyApplication.getInstance().isSuccess = response.getBoolean("success");
                            MyApplication.getInstance().pesan = response.getString("message");
                        } catch (JSONException ignored){
                            MyApplication.getInstance().isSuccess = false;
                        }
                        finally {
                            myIntent.putExtra("nextLevel", arr[level]);
                            if(MyApplication.getInstance().isSuccess){
                                MyApplication.getInstance().level = MyApplication.getInstance().level+1;
                            }
                            checkButton.setText("Check Image");
                            startActivity(myIntent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkButton.setEnabled(true);
                checkButton.setText("Check Image");
            }
        });
        queue.add(postRequest);



        // Add the request to the RequestQueue.

    }


}
