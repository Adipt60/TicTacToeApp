package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = false;

    // Player Representations:
    // O -> 0
    // X -> 1

    int activePlayer = 1;

    // State Representations:
    // 2 -> Blank
    // 0 -> O
    // 1 -> X

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winPosition = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6}};

    public void tapToPlay(View view){
        gameActive = true;
        newGame(view);
    }

    public void tapPosition(View view){

        ImageView img  = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // Setting Grid for each turn
        if(gameState[tappedImage]==2 && gameActive){
            gameState[tappedImage] = activePlayer;

            if(activePlayer==1){
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.oTurn);
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.xTurn);
            }
        }

        if(checkDraw()){
            TextView status = findViewById(R.id.status);
            status.setText(R.string.gameDraw);
        }

    }

    // Checking Win
    public boolean checkWin(){

        for(int[] winPosition: winPosition){

            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=2){

                gameActive = false;
                String winner;
                if(gameState[winPosition[0]]==1){
                    winner = "X Won";
                }
                else
                {
                    winner = "O Won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winner);
                return true;
            }
        }

        return false;
    }


    // Checking Draw
    public boolean checkDraw(){

        boolean draw = true;

        for (int j : gameState) {
            if (j == 2) {
                draw = false;
                break;
            }
        }

        return !checkWin() && draw;

    }

    // Game Reset
    public void newGame(View view){

        activePlayer = 1;
        TextView status = findViewById(R.id.status);
        status.setText(R.string.xTurn);

        Arrays.fill(gameState, 2);

        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
    }
}

// To Do
// 1. First Tap tp play then X's Turn-done
// 2. Check for Draw-done
// 3. New Game Button