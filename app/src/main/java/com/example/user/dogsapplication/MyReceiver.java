package com.example.user.dogsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//this is the broadcast reciever class.
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
      Intent intent1 = new Intent(context,MyIntentService.class);
      context.startService(intent);
    }
}
