package com.example.user.dogsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DogDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etDogName ,etDate , etWeight, etTime ;
    Button btEdit, btCamera, btGallery, btDelete;
    ImageView imageView;
    Dog dog;
    // final FirebaseDatabase firebaseDatabase;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Dogs/Users");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);

        dog = (Dog)getIntent().getSerializableExtra("dog");
        if(dog != null) {
            //2.
            etDogName = findViewById(R.id.etDogName);
            etDogName.setText(dog.getName());

            etDate = findViewById(R.id.etDate);
            etDate.setText(dog.getDate());

            etWeight = findViewById(R.id.etWieght);
            etWeight.setText(dog.getWeight());

            //option to add a list of time picker
            etTime = findViewById(R.id.etTime);
            etTime.setText(dog.getTime());

            //     imageView = findViewById(R.id.imageView);
            //    imageView


        }
        btCamera = findViewById(R.id.btCamera);
        btCamera.setOnClickListener(this);

        btGallery = findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        btDelete = findViewById(R.id.btDelete);
        btDelete.setOnClickListener(this);

        btEdit = findViewById(R.id.btEdit);
        btEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(btEdit == v) {
            myRef.child(user.getUid()).child(dog.getKey()).child("date").setValue(etDate.getText().toString());
            myRef.child(user.getUid()).child(dog.getKey()).child("name").setValue(etDogName.getText().toString());
            myRef.child(user.getUid()).child(dog.getKey()).child("time").setValue(etTime.getText().toString());
            myRef.child(user.getUid()).child(dog.getKey()).child("weight").setValue(etWeight.getText().toString());
        }else{
            myRef.child(user.getUid()).child(dog.getKey()).setValue(null);
        }

    }

}
