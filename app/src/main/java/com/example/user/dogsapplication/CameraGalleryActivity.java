package com.example.user.dogsapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    ImageView imageView;
    Button btGallery, btTakePhoto , btSave;
    EditText etName;
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);
        btTakePhoto = findViewById(R.id.btTakePhoto);
        btTakePhoto.setOnClickListener(this);

        etName = findViewById(R.id.etName);
        etName.setOnClickListener(this);

        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);

        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(this);

        SharedPreferences pref = getSharedPreferences("mypref", MODE_PRIVATE);

        String name = pref.getString(s:"name", s1:null);

        tvName = findViewById(R.id.tvName);
        if (name != null) {
            tvName.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref =getSharedPreferences("mypref", MODE_PRIVATE);
         // open the file for editing
        SharedPreferences.Editor editor= pref.edit();

        editor.putString(s:"name", etName.getText().toString());
        editor.commit();


        if (v == btTakePhoto) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);

        } else {
            Intent i =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
            startActivityForResult(i,SELECT_IMAGE);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }else if(requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK){
            Uri targetUri = data.getData();
            try {
                Bitmap bitmap =
                        BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}



