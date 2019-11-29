package com.example.ticktacktoab;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    ImageView images[];
    TicTacToeGame game;
    private boolean isOnePlayer;
    private boolean playerOneStarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        isOnePlayer = getIntent().getExtras().getBoolean("isOnePlayer");
        playerOneStarts = getIntent().getExtras().getBoolean("playerOneStarts");


        // create game instance
        game = new TicTacToeGame(playerOneStarts, isOnePlayer);

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


        // if AI goes first
        if (isOnePlayer && !playerOneStarts) {
            AIMove();
        }

    }

    /**
     * This function gets called every time player makes a move
     * @param view view that calls the function
     */

    public void viewPressed(View view) {
        if (game.isGameOver()) {
            // reset game!
            resetGame();
            Toast.makeText(this,"reset game!",Toast.LENGTH_SHORT).show();
        } else {
            // if the game is not over

            ImageView image = (ImageView) view;

            int position = Integer.parseInt(view.getTag().toString());

            // if the game is single player, play against AI
            if (game.isSinglePlayer()) {
                if (game.isPositionFree(position) && game.getTurn()) {
                    // make player move
                    image.setImageResource(R.drawable.red);
                    image.animate().alphaBy(1f).setDuration(800);
                    game.nextTurn(position);
                    if (!game.isGameOver()) {
                        AIMove();
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
                // determine the game outcome

                // cases for first player to win
                if ((game.getWinningPlayer() == 1 && isOnePlayer)
                || (game.getWinningPlayer() == 1 && !isOnePlayer && playerOneStarts)
                || (game.getWinningPlayer() == -1 && !isOnePlayer && !playerOneStarts)) {
                    Toast.makeText(this, "Player 1 wins!!", Toast.LENGTH_SHORT).show();

                // cases for AI to win
                } else if (game.getWinningPlayer() == -1 && isOnePlayer) {
                    Toast.makeText(this, "AI Wins!", Toast.LENGTH_SHORT).show();

                // cases for player 2 to win
                } else if ((game.getWinningPlayer() == -1 && !isOnePlayer && playerOneStarts)
                || (game.getWinningPlayer() == 1 && !isOnePlayer && !playerOneStarts)){
                    Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void AIMove() {
        // make AI move
        int aiPos = game.playAI();
        images[aiPos].setImageResource(R.drawable.yellow);
        images[aiPos].animate().alphaBy(1f).setDuration(800);
    }

    /**
     * This function resets the game and updates the UI
     */
    private void resetGame() {
        game.resetGame(playerOneStarts,isOnePlayer);
        for (int i = 0; i < images.length;i++) {
            if (images[i].getAlpha() == 1f)
                images[i].animate().alphaBy(-1f);
        }

        if (!playerOneStarts && isOnePlayer) {
            AIMove();
        }
    }
}
