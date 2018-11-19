package com.example.user.dogsapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AlertDidogActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_didog);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==dialog.BUTTON_POSITIVE){
            Toast.makeText(this,"Yes", Toast.LENGTH_LONG ).show();
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
