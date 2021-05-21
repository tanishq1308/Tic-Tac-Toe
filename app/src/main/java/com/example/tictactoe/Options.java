package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Options extends AppCompatActivity{

    public void Tap(View view) {
        TextView textView = (TextView)view;
        int tappedImage=Integer.parseInt(textView.getTag().toString());

        if (tappedImage==0) {
            Intent intent = new Intent(this,name1.class);
            startActivity(intent);
        }
        else if (tappedImage==1) {
            Intent intent = new Intent(this,name2.class);
            startActivity(intent);
        }
        else if (tappedImage==2) {
            Intent intent = new Intent(this,rules.class);
            startActivity(intent);
        }
        else if (tappedImage==3) {
            Intent intent = new Intent(this,about.class);
            startActivity(intent);
        }
        else {
            finish();
            moveTaskToBack(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
}
