package com.example.user.dogsapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

@SuppressLint("Registered")
public class FireActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etEmail;
    EditText etPass;
    TextView tvEmail;
    TextView tvProfession;
    Button btSave;
    ListView lvUsers;
    ArrayList<String>users;
    protected void onCreate(Bundle savedInstanceState) {
        final FirebaseDatabase firebaseDatabase;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                Log.v ("E_VALUE", "DATA" + dataSnapshot.getValue());
                String name = map.get("name");
                String profession = map.get("profession");
                tvEmail.setText(name);
                tvProfession.setText(profession);

//                String value = dataSnapshot.getValue(String.class);
//                tvEmail.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvEmail = findViewById(R.id.tvEmail);
        tvProfession = findViewById(R.id.tvProfession);
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String email = etEmail.getText().toString();
              myRef.child("Name").setValue(email);
              myRef.child("profession").setValue("student");
              myRef.child("Name").push().setValue(email);
            }
        });

         lvUsers = findViewById(R.id.lvUsers);
         users = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        lvUsers.setAdapter(adapter);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.getValue(String.class);
                users.add(name);
                adapter.notifyDataSetChanged();
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
    public void onClick(View v) {

    }
}
