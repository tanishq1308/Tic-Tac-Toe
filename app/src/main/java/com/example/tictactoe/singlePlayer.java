package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class singlePlayer extends AppCompatActivity {

    boolean gameActive = true;
    Timer timer;
    String player1="";
    int c=0;

    //      Player representations
    //      0-X-Player1
    //      1-O-Computer

    int activePlayer=0;
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};

    //      State meanings:
    //      0-X
    //      1-O
    //      2-Null

    int[][] winPositions={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    public void call() {
        if (activePlayer==1 && gameActive && c<5) {
            int tappedImage = 0, m=0;
            ImageView[] id = new ImageView[9];
            for (int[] winPosition: winPositions) {
                if ((gameState[winPosition[0]] == gameState[winPosition[1]]) && (gameState[winPosition[0]] == 1) && (gameState[winPosition[2]] == 2)) {
                    tappedImage = winPosition[2];
                    break;
                } else if ((gameState[winPosition[1]] == gameState[winPosition[2]]) && (gameState[winPosition[1]] == 1) && gameState[winPosition[0]] == 2) {
                    tappedImage = winPosition[0];
                    break;
                } else if ((gameState[winPosition[0]] == gameState[winPosition[2]]) && (gameState[winPosition[0]] == 1) && gameState[winPosition[1]] == 2) {
                    tappedImage = winPosition[1];
                    break;
                }
            }
            if (tappedImage==0) {
                for (int[] winPosition : winPositions) {
                    if ((gameState[winPosition[0]] == gameState[winPosition[1]]) && (gameState[winPosition[0]] == 0) && gameState[winPosition[2]] == 2) {
                        tappedImage = winPosition[2];
                        break;
                    } else if ((gameState[winPosition[0]] == gameState[winPosition[2]]) && (gameState[winPosition[0]] == 0) && gameState[winPosition[1]] == 2) {
                        tappedImage = winPosition[1];
                        break;
                    } else if ((gameState[winPosition[1]] == gameState[winPosition[2]]) && (gameState[winPosition[1]] == 0) && gameState[winPosition[0]] == 2) {
                        tappedImage = winPosition[0];
                        break;
                    } else {
                        m++;
                    }
                }
            }
            if (m==8) {
                do {
                    Random rand = new Random();
                    tappedImage = rand.nextInt(9);
                }
                while (gameState[tappedImage] != 2);
            }
            gameState[tappedImage] = activePlayer;

            id[0] = (ImageView) findViewById(R.id.imageView0);
            id[1] = (ImageView) findViewById(R.id.imageView1);
            id[2] = (ImageView) findViewById(R.id.imageView02);
            id[3] = (ImageView) findViewById(R.id.imageView3);
            id[4] = (ImageView) findViewById(R.id.imageView4);
            id[5] = (ImageView) findViewById(R.id.imageView5);
            id[6] = (ImageView) findViewById(R.id.imageView6);
            id[7] = (ImageView) findViewById(R.id.imageView7);
            id[8] = (ImageView) findViewById(R.id.imageView8);

            for (int i = 0; i < 9; i++) {
                if (tappedImage == Integer.parseInt(id[i].getTag().toString())) {
                    id[i].setTranslationY(-1000f);
                    id[i].setImageResource(R.drawable.o);

                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText(player1 + "'s Turn - Tap to play");
                    id[i].animate().translationYBy(1000f).setDuration(300);
                }
            }
        }
        for (int[] winPosition : winPositions) {
            if ((gameState[winPosition[0]] == gameState[winPosition[1]]) && (gameState[winPosition[1]] == gameState[winPosition[2]]) && gameState[winPosition[0]] != 2) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = player1 + " has WON";
                } else {
                    winnerStr = "Computer has WON";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                // Update the status bar for winner announcement
            }
        }
    }

    public void playerTap(View view) {

        ImageView img;
        if (gameActive) {
            img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());

            if (gameState[tappedImage] == 2) {
                c+=1;
                if (activePlayer == 0) {
                    gameState[tappedImage] = activePlayer;
                    img.setTranslationY(-1000f);

                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("Computer's Turn - Please Wait");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
        }

            // Check if any player has won

            for (int[] winPosition : winPositions) {
                if ((gameState[winPosition[0]] == gameState[winPosition[1]]) && (gameState[winPosition[1]] == gameState[winPosition[2]]) && gameState[winPosition[0]] != 2) {
                    // Somebody has won! - Find out who!
                    String winnerStr;
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = player1 + " has WON";
                    } else {
                        winnerStr = "Computer has WON";
                    }
                    // Update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        call();
                    }
                    catch(Exception m) {
                        call();
                    }
                }
            },2000);

            int i = 0;
            for (i = 0; i < gameState.length; i++) {
                if (gameState[i] == 2)
                    break;
            }
            if ((i == gameState.length) && (gameActive)) {
                TextView status = findViewById(R.id.status);
                status.setText("Game Over");
            }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        c=0;
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
        setContentView(R.layout.activity_single);

        Intent intent = getIntent();
        String p=intent.getStringExtra(name1.player1);

        player1=p;

        TextView status = findViewById(R.id.textView);
        status.setText(player1+" vs Computer");
    }
}