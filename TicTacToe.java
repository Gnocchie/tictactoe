package de.lmu.bio.ifi;

import de.lmu.bio.ifi.basicpackage.BasicBoard;

public class TicTacToe extends BasicBoard implements Game {

    protected boolean TURN_P1 = true;
    protected boolean OVER = false;

    public TicTacToe(int size) {
        setBoardtype(size + "x" + size);
        this.board = new int[size][size];
        for (int[] cols : board)
            for(int e : cols) e = 0;
    }
    public boolean getTURN_P1(){
        return TURN_P1;
    }
    @Override
    public boolean makeMove(boolean p1, int x, int y) {
        int board_sum = 0;
        for (int[] cols : board)
            for(int e : cols) board_sum += e;
        try{
            if(board[y][x] == 0){
                if(p1){
                    board[y][x] = 1;
                    TURN_P1 = false;
                    return true;
                }
                else{
                    board[y][x] = 2;
                    TURN_P1 = true;
                    return true;
                }
            }
        }catch(Exception ArrayIndexOutOfBoundsException){return false;}
        return false;
    }

    @Override
    public GameStatus gameStatus() {
        GameStatus temp = GameStatus.DRAW;
        // check_columns()
            for (int i=0; i<board.length; i++){
                int col_sum = 0;
                for (int j=0; j<board.length; j++){
                    if(board[j][i]==0){
                        temp = GameStatus.RUNNING;
                        col_sum = 0;
                        break;
                    }
                    else{col_sum += board[j][i];}
                }
                if(col_sum == board.length) {
                    OVER = true;
                    return GameStatus.PLAYER_1_WON;
                }
                if(col_sum == board.length * 2) {
                    OVER = true;
                    return GameStatus.PLAYER_2_WON;
                }
            }
        // check_rows
        for (int i=0; i<board.length; i++){
            int row_sum = 0;
            for (int j=0; j<board.length; j++){
                if(board[i][j]==0){
                    row_sum = 0;
                    break;
                }
                else{row_sum += board[i][j];}
            }
            if(row_sum == board.length) {
                OVER = true;
                return GameStatus.PLAYER_1_WON;
            }
            if(row_sum == board.length * 2) {
                OVER = true;
                return GameStatus.PLAYER_2_WON;
            }
        }

        // check_diagonals
        int diag1_sum = 0;
        int diag2_sum = 0;
        for (int i=0; i<board.length; i++){
            if(board[i][i]==0){
                diag1_sum = 0;
                break;
            }
            else{diag1_sum += board[i][i];}
        }
        for(int i=0; i<board.length; i++){
            if(board[board.length-i-1][i]==0){
                diag2_sum = 0;
                break;
            }
            else{diag2_sum += board[board.length-i-1][i];}
        }
        if(diag1_sum == board.length || diag2_sum == board.length) {
            OVER = true;
            return GameStatus.PLAYER_1_WON;
        }
        if(diag1_sum == board.length * 2 || diag2_sum == board.length * 2) {
            OVER = true;
            return GameStatus.PLAYER_2_WON;
        }

        // all possibilities checked
        // if there was a 0 present, the temp will be changed to RUNNING at check_columns, if not it stays as DRAW
        if(temp == GameStatus.DRAW) OVER = true;
        return temp;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                String k = "";
                if(board[i][j]==0){
                    k = " ";
                }else if (board[i][j]==1) {
                    k = "X";
                }else if (board[i][j]==2) {
                    k = "O";
                }
                out += "["
                        +
                        k
                        +
                        "]";
            }
            out += "\n";
        }
        return out;
    }
}
