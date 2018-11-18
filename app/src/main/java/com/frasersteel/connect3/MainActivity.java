package com.frasersteel.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //zero = yellow, 1 = red

    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 means empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int  tag = Integer.parseInt(counter.getTag().toString());


        if (gameState[tag] ==2 && gameIsActive) {

            counter.setTranslationY(-1000f);

            gameState[tag] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).rotation(180).setDuration(500);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).rotation(180).setDuration(500);
                activePlayer = 0;
            }

            for (int[] winningPos:winningPos) {

                if(gameState[winningPos[0]] == gameState[winningPos[1]]
                        && gameState[winningPos[1]] == gameState[winningPos[2]]
                        && gameState[winningPos[0]] !=2){


                   gameIsActive = false;

                   String winner = "Red";

                   if(gameState[winningPos[0]] == 0){
                       winner = "Yellow";
                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);

                   winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                } else{

                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if(counterState == 2) gameIsOver = false;
                    }

                    if(gameIsOver){
                        TextView winnerMessage = findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                    }
                }


            }

        }
        else{
            Toast.makeText(this, "Location already selected, try again.", Toast.LENGTH_SHORT).show();
        }
    }


    public void playAgain(View view){
        gameIsActive=true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }


    private String getPlayer(){
        if(activePlayer==0){
            return "Red";
        }else{
            return "Yellow";
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
