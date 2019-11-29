package com.example.ticktacktoab;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    TicTacToeGame game;
    ImageView images[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Boolean isOnePlayer = getIntent().getExtras().getBoolean("yourBoolName");

        game = new TicTacToeGame(true, isOnePlayer);

        // reference all views
        images = new ImageView[9];
        images[0] = findViewById(R.id.image1);
        images[1] = findViewById(R.id.image2);
        images[2] = findViewById(R.id.image3);
        images[3] = findViewById(R.id.image4);
        images[4] = findViewById(R.id.image5);
        images[5] = findViewById(R.id.image6);
        images[6] = findViewById(R.id.image7);
        images[7] = findViewById(R.id.image8);
        images[8] = findViewById(R.id.image9);

    }

    public void viewPressed(View view) {
        if (game.isGameOver()) {
            // do nothing!

            //Toast.makeText(this,"game over!",Toast.LENGTH_SHORT).show();
        } else {
            // if the game is not over

            ImageView image = (ImageView) view;

            int position = Integer.parseInt(view.getTag().toString());

            // if the game is single player, play against AI
            if (game.isSinglePlayer()) {
                Toast.makeText(this, "is single player", Toast.LENGTH_SHORT).show();
                if (game.getTurn() && game.isPositionFree(position)) {
                    // make player move
                    image.setImageResource(R.drawable.red);
                    image.animate().alphaBy(1f).setDuration(800);
                    game.nextTurn(position);
                    if (!game.isGameOver()) {
                        // make AI move
                        int aiPos = game.playAI();
                        images[aiPos].setImageResource(R.drawable.yellow);
                        images[aiPos].animate().alphaBy(1f).setDuration(800);
                    }


                }
            // if the game is two players
            } else {

                if (game.getTurn() && game.isPositionFree(position)) {
                    image.setImageResource(R.drawable.red);
                    image.animate().alphaBy(1f).setDuration(800);
                    game.nextTurn(position);
                } else if (!game.getTurn() && game.isPositionFree(position)) {
                    image.setImageResource(R.drawable.yellow);
                    image.animate().alphaBy(1f).setDuration(800);
                    game.nextTurn(position);
                }
            }

            // check to see if game is over
            if (game.isGameOver()) {
                if (game.getWinningPlayer() == 1) {
                    Toast.makeText(this, "Player 1 wins!!", Toast.LENGTH_SHORT).show();
                } else if (game.getWinningPlayer() == -1) {
                    Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
