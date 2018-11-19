package com.example.user.dogsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MyDogList extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{
    ListView lvDogs;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();

    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dog_list);

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);

        lvDogs = (ListView)  findViewById(R.id.myDogList);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lvDogs.setAdapter(arrayAdapter);
        lvDogs.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        arrayList.get(position);
        if(position == 0){
            Intent i = new Intent(this, DogDetailsActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, DogProfile.class);
        startActivity(i);
    }
}
