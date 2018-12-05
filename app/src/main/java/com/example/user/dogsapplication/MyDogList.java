package com.example.user.dogsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class MyDogList extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{
    ListView lvDogs;
    CustomAdapter arrayAdapter;
    ArrayList<Dog> arrayList = new ArrayList<>();

    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dog_list);

        final FirebaseDatabase firebaseDatabase;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Dogs");

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);

        lvDogs = (ListView)  findViewById(R.id.myDogList);

        arrayAdapter = new CustomAdapter(this,R.layout.costumrow, arrayList);
        lvDogs.setAdapter(arrayAdapter);
        lvDogs.setOnItemClickListener(this);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, String> dog = (Map<String, String>) dataSnapshot.getValue();

                Dog d1 = new Dog(dog.get("image"), dog.get("name"), dog.get("date"),dog.get("weight"),dog.get("time"));
                arrayList.add(d1);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
