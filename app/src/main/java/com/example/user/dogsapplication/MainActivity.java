package com.example.user.dogsapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    Button btMyDogs, btAboutApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btMyDogs = (Button) findViewById(R.id.btMyDogs);
        btAboutApp = (Button) findViewById(R.id.btAboutApp);

        btMyDogs.setOnClickListener(this);
        btAboutApp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==btMyDogs){
            Intent i = new Intent(this, MyDogList.class);
            startActivity(i);
        }
        if(v==btAboutApp){
            Intent i = new Intent(this,AboutAplication.class);
            startActivity(i);
        }


    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==dialog.BUTTON_POSITIVE){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        
        if(which==dialog.BUTTON_NEGATIVE) {
            Toast.makeText(this,"No", Toast.LENGTH_LONG ).show();
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",this);
        builder.setNegativeButton("No",this);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}

