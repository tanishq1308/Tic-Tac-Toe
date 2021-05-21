package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class multiPlayer extends AppCompatActivity {

    boolean gameActive = true;
    String player1="";
    String player2="";

    //      Player representations
    //      0-X-Player1
    //      1-O-Player2

    int activePlayer=0;
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};

    //      State meanings:
    //      0-X
    //      1-O
    //      2-Null

    int[][] winPositions={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    public void playerTap(View view) {

        if (gameActive) {
            ImageView img = (ImageView)view;
            int tappedImage=Integer.parseInt(img.getTag().toString());
            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);

                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText(player2+"'s Turn - Tap to play");
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText(player1+"'s Turn - Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
        }

        // Check if any player has won

        for (int[] winPosition: winPositions) {
            if ((gameState[winPosition[0]] == gameState[winPosition[1]]) && (gameState[winPosition[1]] == gameState[winPosition[2]]) && gameState[winPosition[0]]!=2) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive=false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = player1+" has WON";
                }
                else {
                    winnerStr = player2+" has WON";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        int i=0;
        for (i=0;i<gameState.length;i++) {
            if (gameState[i]==2)
                break;
        }
        if ((i==gameState.length) && (gameActive)) {
            TextView status = findViewById(R.id.status);
            status.setText("Game Over");
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i=0; i<gameState.length;i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView02)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText(player1+"'s Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        Intent intent = getIntent();
        String[] p=intent.getStringArrayExtra(name2.player2);

        player1=p[0];
        player2=p[1];

        TextView status = findViewById(R.id.textView);
        status.setText(player1+" vs "+player2);
    }
}