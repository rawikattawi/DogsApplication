package com.example.user.dogsapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Dog>{
    private int resourceLayout;
    private Context mContext;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Dog> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View V = convertView;
        if (V == null)
            V = LayoutInflater.from(mContext).inflate(resourceLayout,parent,false);

        final Dog p = getItem(position);

        if (p != null){
            TextView tvName = (TextView) V.findViewById(R.id.tvName);
            tvName.setText(p.getName());

            ImageView imageView = (ImageView) V.findViewById(R.id.imageView);
            imageView.setImageBitmap(StringToBitMap(p.getImage()));
        //    imageView.setImageResource(StringToBitMap(p.getImage()));

            Button btEdit = V.findViewById(R.id.btEdit);
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, DogDetailsActivity.class);
                    i.putExtra("dog", p);
                    mContext.startActivity(i);
                }
            });

        }
        return V;

    }
    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}