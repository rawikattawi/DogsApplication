package com.example.user.dogsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class MyDogList extends AppCompatActivity implements  View.OnClickListener{
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
      //  lvDogs.setOnItemClickListener(this);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, String> dog = (Map<String, String>) dataSnapshot.getValue();
                String key = dataSnapshot.getKey();
                Dog d1 = new Dog(dog.get("image"), dog.get("name"), dog.get("date"),dog.get("weight"),dog.get("time"));
                d1.setKey(key);
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

/*    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, DogDetailsActivity.class);
        i.putExtra("dog",arrayList.get(position));
        startActivity(i);
    }*/

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, DogProfile.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.about:
                Intent i = new Intent(this, AboutAplication.class);
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
