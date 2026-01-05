package tictactoe;

import tictactoe.Board;
import tictactoe.Player;

class HumanPlayer extends Player {
    private int selectedMove = -1;
    
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }
    
    @Override
    public int makeMove(Board board) {
        return selectedMove;
    }
    
    public void setSelectedMove(int move) {
        this.selectedMove = move;
    }
}