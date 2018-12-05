package com.example.user.dogsapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DogProfile extends AppCompatActivity implements View.OnClickListener{
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    //1.
    EditText etDogName ,etDate , etWeight, etTime ;
    Button btAdd, btCamera, btGallery;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile);

        final FirebaseDatabase firebaseDatabase;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Dogs");

        //2.
        etDogName = findViewById(R.id.etDogName);
        etDate = findViewById(R.id.etDate);
        etWeight = findViewById(R.id.etWieght);
        //option to add a list of time picker
        etTime = findViewById(R.id.etTime);

        btCamera = findViewById(R.id.btCamera);
        btCamera.setOnClickListener(this);

        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = etDogName.getText().toString();
                String date = etDate.getText().toString();
                String weight = etWeight.getText().toString();
                String time = etTime.getText().toString();
                String photo = BitMapToString(bitmap);
                Dog dog = new Dog(photo,name, date, weight, time);

                myRef.push().setValue(dog);

                Intent i = new Intent(DogProfile.this, MyDogList.class);
                startActivity(i);


            }
        });

    }

    @Override
    public void onClick(View v) {
      if (v == btCamera) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);

        }else{
          Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(i, SELECT_IMAGE);
      }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            //String imagePath= saveImage(bitmap);

           // SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
         //   SharedPreferences.Editor editor= pref.edit();
           // editor.putString("image",imagePath);
         //   editor.commit();

        } else if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri targetUri = data.getData();
            try {
               bitmap =
                        BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String saveImage(Bitmap bitmap) {
        File root = Environment.getExternalStorageDirectory();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = root.getAbsolutePath() + "/DCIM/Camera/IMG_" + timeStamp + ".jpg";
        //creating an object from type file

        File file = new File(filePath);
        //determinig the type of the file and its place

        try {
            file.createNewFile();
            FileOutputStream ostream= new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Faild to save image", Toast.LENGTH_LONG).show();

        }
        return filePath;
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}

