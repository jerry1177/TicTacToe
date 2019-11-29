package com.example.ticktacktoab;
import java.lang.ArrayIndexOutOfBoundsException;

public class TicTacToeGame {
    private int[] gameState;
    private boolean isFirstPlayerTurn;
    private boolean isOnePlayer;
    private boolean gameOver;
    private int winningPlayer;
    private int turn;

    // --- Constuctors ---

    /**
     * Single player constructor with player starting first
     */
    public TicTacToeGame(){
        gameState = new int[9];
        for (int i = 0; i < gameState.length;i++){
            gameState[i] = 0;
        }
        isFirstPlayerTurn = true;
        isOnePlayer = true;
        gameOver = false;
        turn = 0;
    }

    /**
     * Single player constructor settings
     * @param isFirstPlayerTurn set boolean to false if you want player two to start first
     */
    public TicTacToeGame(boolean isFirstPlayerTurn){
        gameState = new int[9];
        for (int i = 0; i < gameState.length;i++){
            gameState[i] = 0;
        }
        this.isFirstPlayerTurn = isFirstPlayerTurn;
        isOnePlayer = true;
        gameOver = false;
        turn = 0;
        winningPlayer = 0;
    }

    /**
     * Constructor
     * @param isFirstPlayerTurn set boolean to false if you want player to start first
     * @param isOnePlayer set boolean to false if you want game to be two players
     */
    public TicTacToeGame(boolean isFirstPlayerTurn, boolean isOnePlayer){
        gameState = new int[9];
        for (int i = 0; i < gameState.length;i++){
            gameState[i] = 0;
        }
        this.isFirstPlayerTurn = isFirstPlayerTurn;
        this.isOnePlayer = isOnePlayer;
        gameOver = false;
        turn = 0;
        winningPlayer = 0;
    }

    /**
     * This function checks to see if it is the first player's turn
     * @return returns true if it is first player's turn
     */

    public boolean getTurn(){
        return isFirstPlayerTurn;
    }

    /**
     * This function check to see if the game is in single player mode
     * @return returns true if game is in single player mode
     */

    public boolean isSinglePlayer(){
        return isOnePlayer;
    }

    /**
     * This function takes an integer position from 1-9,
     * which represents every position on the board in row-major order
     * and adjusts the game's state for the next player
     * @param position position from 1-9 representing the position of every square
     */

    public void nextTurn(int position) {
        if (position < 1 || position > 9)
            throw new ArrayIndexOutOfBoundsException("position must be from 1-9");
        // make move
        if (isFirstPlayerTurn)
            gameState[position - 1] = 1;
        else
            gameState[position-1] = -1;

        turn++;

        // check to see if game is over
        int state = checkGameState(gameState);

        if (state != 0) {
            gameOver = true;
            winningPlayer = state;
        }
        else if (turn >= 9)
            gameOver = true;

        isFirstPlayerTurn = !isFirstPlayerTurn;
    }

    /**
     * This function takes an integer position from 1-9,
     * which represents every position on the board in row-major order
     * and returns the player state of that position
     *
     * @param position position from 1-9 representing the position of every square
     * @return the state of which player owns that position player1 = 1 player2 = -1 and no player = 0
     */
    public int getPositionState(int position) {
        if (position < 1 || position > 9)
            throw new ArrayIndexOutOfBoundsException("position must be from 1-9");
        return gameState[position-1];
    }

    /**
     * This function takes an integer position from 1-9,
     * which represents every position on the board in row-major order
     * and returns whether or not the position is free
     *
     * @param position position from 1-9 representing the position of every square
     * @return returns true if the postion is free
     */

    public boolean isPositionFree(int position) {
        if (position < 1 || position > 9)
                throw new ArrayIndexOutOfBoundsException("position must be from 1-9");
        return gameState[position-1] == 0;
    }

    /**
     * This function checks to see if the game is over or not
     * @return returns true if the game is over
     */

    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * This function returns 1 if player1 wins -1 if player2 wins and 0 if it is a draw
     * @return returns the winning player.
     */
    public int getWinningPlayer(){
        return winningPlayer;
    }

    /**
     * This function resets the game
     * @param isFirstPlayerTurn False if you want Ai to go first
     * @param isOnePlayer False if you want game to be two players
     */

    public void resetGame(boolean isFirstPlayerTurn, boolean isOnePlayer) {
        for (int i = 0; i < gameState.length;i++){
            gameState[i] = 0;
        }
        this.isFirstPlayerTurn = isFirstPlayerTurn;
        this.isOnePlayer = isOnePlayer;
        gameOver = false;
        turn = 0;
        winningPlayer = 0;
    }

    /**
     * This function returns checks to see if a player has won
     * @param stateOfGame // takes the current state of the game
     * @return returns 1 if player1 won, -1 if player2 won, and 0 if no one won
     */
    private int checkGameState(int [] stateOfGame){
        if ((stateOfGame[0] == 1 && stateOfGame[1] == 1 && stateOfGame[2] == 1)
                || (stateOfGame[3] == 1 && stateOfGame[4] == 1 && stateOfGame[5] == 1)
                || (stateOfGame[6] == 1 && stateOfGame[7] == 1 && stateOfGame[8] == 1)
                || (stateOfGame[0] == 1 && stateOfGame[3] == 1 && stateOfGame[6] == 1)
                || (stateOfGame[1] == 1 && stateOfGame[4] == 1 && stateOfGame[7] == 1)
                || (stateOfGame[2] == 1 && stateOfGame[5] == 1 && stateOfGame[8] == 1)
                || (stateOfGame[0] == 1 && stateOfGame[4] == 1 && stateOfGame[8] == 1)
                || (stateOfGame[2] == 1 && stateOfGame[4] == 1 && stateOfGame[6] == 1)) {
            return 1;
        } else if ((stateOfGame[0] == -1 && stateOfGame[1] == -1 && stateOfGame[2] == -1)
                || (stateOfGame[3] == -1 && stateOfGame[4] == -1 && stateOfGame[5] == -1)
                || (stateOfGame[6] == -1 && stateOfGame[7] == -1 && stateOfGame[8] == -1)
                || (stateOfGame[0] == -1 && stateOfGame[3] == -1 && stateOfGame[6] == -1)
                || (stateOfGame[1] == -1 && stateOfGame[4] == -1 && stateOfGame[7] == -1)
                || (stateOfGame[2] == -1 && stateOfGame[5] == -1 && stateOfGame[8] == -1)
                || (stateOfGame[0] == -1 && stateOfGame[4] == -1 && stateOfGame[8] == -1)
                || (stateOfGame[2] == -1 && stateOfGame[4] == -1 && stateOfGame[6] == -1)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * This function allows the AI to make a move
     * @return returns the position that the AI wants to take
     */

    public int playAI() {
        int action = minimaxAB(gameState, isFirstPlayerTurn);
        if (isFirstPlayerTurn) {
            gameState[action] = 1;
        } else {
            gameState[action] = -1;
        }
        nextTurn(action+1);
        return action;
    }

    // --------- AI minimax alpha beta algorithm ----------

    private int minimaxAB(int[]statOfGame, boolean firstPlayerTurn){
        int action;
        double alpha = -2000, beta = 2000;

        if (firstPlayerTurn) { // max players turn
            double maxv = -2000;
            action = 0;
            for (int i = 0; i < 9; i++){
                if (statOfGame[i] == 0) { // a valid move
                    int[] newState = statOfGame.clone();
                    newState[i] = 1;
                    double val = minValueAB(newState, alpha, beta);
                    if (val > maxv) {
                        maxv = val;
                        action = i;
                    }
                }
            }
        } else { // min players turn
            double minVal = 2000;
            action = 0;
            for (int i = 0; i < 9; i++) {
                if (statOfGame[i] == 0) { // valid move
                    int[] newState = statOfGame.clone();
                    newState[i] = -1;
                    double val = maxValueAB(newState, alpha, beta);
                    if (val < minVal) {
                        minVal = val;
                        action = i;
                    }

                }
            }
        }
        return action;
    }

    private double minValueAB(int[] stateOfGame, double alpha, double beta) {

        // check if terminal state

        if (checkGameState(stateOfGame) != 0) // a player won
            return checkGameState(stateOfGame);

        // check to see if it is a tie
        
        boolean isTie = true;
        for (int i = 0; i < stateOfGame.length; i++) {
            if (stateOfGame[i] == 0)
                isTie = false;
        }
        if (isTie)
            return 0;

        double minVal = 2000; // infinity

        for (int i = 0; i < stateOfGame.length; i++) {
            if(stateOfGame[i] == 0) { // position is valid
                int[] newState = stateOfGame.clone();
                newState[i] = -1;
                double val = maxValueAB(newState,alpha,beta);

                if (val < minVal)
                    minVal = val;

                if (minVal <= alpha)
                    return minVal;

                if (beta < minVal)
                    beta = minVal;
            }
        }

        return minVal;
    }

    private double maxValueAB(int[] stateOfGame, double alpha, double beta) {
        // check if terminal state

        if (checkGameState(stateOfGame) != 0) // a player won
            return checkGameState(stateOfGame);

        // check to see if it is a tie

        boolean isTie = true;
        for (int i = 0; i < stateOfGame.length; i++) {
            if (stateOfGame[i] == 0)
                isTie = false;
        }
        if (isTie)
            return 0;

        double maxVal = -2000; // negative infinity

        for (int i = 0; i < stateOfGame.length; i++) {
            if(stateOfGame[i] == 0) { // position is valid
                int[] newState = stateOfGame.clone();
                newState[i] = 1;
                double val = minValueAB(newState,alpha,beta);

                if (val > maxVal)
                    maxVal = val;

                if (maxVal >= beta)
                    return maxVal;

                if (alpha > maxVal)
                    alpha = maxVal;
            }
        }

        return maxVal;
    }
}