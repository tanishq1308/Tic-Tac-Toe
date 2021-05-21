package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class name2 extends AppCompatActivity {

    public static final String player2 = "com.example.tictactoe.player2";

    public void multi(View view) {
        EditText editText1 = findViewById(R.id.editTextTextPersonName3);
        EditText editText2 = findViewById(R.id.editTextTextPersonName4);

        String p1=editText1.getText().toString();
        String p2=editText2.getText().toString();

        String[] p= {p1,p2};

        Intent intent = new Intent(this, multiPlayer.class);

        intent.putExtra(player2, p);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name2);
    }
}