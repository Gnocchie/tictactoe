package de.lmu.bio.ifi;

public class AI {
    TicTacToe game;
    boolean P1 = false;
    SETTING set = SETTING.RANDOM;

    public AI(TicTacToe t){
        game = t;
    }
    public AI(TicTacToe t, boolean P1){
        game = t;
        this.P1 = P1;
    }

    public int[] makeMove(){
        if (set == SETTING.RANDOM){     // Plays a random move inside size 3 board
            while(true){
                int randX = (int)Math.round(Math.random()*2);
                int randY = (int)Math.round(Math.random()*2);
                if(game.makeMove(
                        this.P1,
                        randX,
                        randY
                )) return new int[]{randX, randY};
            }
        }
        return new int[]{};
    }

}

enum SETTING{
    RANDOM,
    WINNER,
}
