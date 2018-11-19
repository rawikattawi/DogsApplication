package com.example.user.dogsapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DogProfile extends AppCompatActivity implements View.OnClickListener{
    private static final int CAMERA_REQUEST = 0;
    //1.
    EditText etDogName ,etDate , etWeight, etTime ;
    Button btAdd, btCamera, btGallery;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile);

        //2.
        etDogName = findViewById(R.id.etDogName);
        etDate = findViewById(R.id.etDate);
        etWeight = findViewById(R.id.etWieght);
        etTime = findViewById(R.id.etTime);

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);

        btCamera = findViewById(R.id.btCamera);
        btCamera.setOnClickListener(this);

        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);


    }



    @Override
    public void onClick(View v) {
        if(v == btAdd) {

            Intent i = new Intent(this, MyDogList.class);
            startActivity(i);
        }
        else if (v == btCamera) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);

        } else {

        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}

