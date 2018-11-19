package com.example.user.dogsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DogDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvDogName ,tvDate ,tvWieght, tvTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);
        tvDogName =findViewById(R.id.tvDogName);
        tvDate = findViewById(R.id.tvDate);
        tvWieght = findViewById(R.id.tvWieght);
        tvTime = findViewById(R.id.tvTime);

    }

    @Override
    public void onClick(View v) {

    }
}
