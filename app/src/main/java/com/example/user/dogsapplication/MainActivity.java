package com.example.user.dogsapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    Button btMyDogs, btAboutApp;
    private static final int NOTIFICATION_REMINDER_NIGHT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btMyDogs = (Button) findViewById(R.id.btMyDogs);
        btAboutApp = (Button) findViewById(R.id.btAboutApp);

        btMyDogs.setOnClickListener(this);
        btAboutApp.setOnClickListener(this);

        //and then in your activity set the alarm manger to start the broadcast receiver at a specific time and use AlarmManager setRepeating method to repeat it this example bellow will repeat it every day.
        Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);


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

