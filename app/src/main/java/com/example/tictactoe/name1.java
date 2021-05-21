package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class name1 extends AppCompatActivity {

    public static final String player1 = "com.example.tictactoe.player1";

    public void single(View view) {
        EditText editText = findViewById(R.id.editTextTextPersonName2);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, singlePlayer.class);

        intent.putExtra(player1, message);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name1);
    }
}