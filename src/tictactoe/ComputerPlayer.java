package tictactoe;

import tictactoe.Board;
import tictactoe.Player;

class ComputerPlayer extends Player {
    
    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }
    
    @Override
    public int makeMove(Board board) {
        int move = findBestMove(board, symbol);
        if (move != -1) return move;
        
        char opponentSymbol = (symbol == 'X') ? 'O' : 'X';
        move = findBestMove(board, opponentSymbol);
        if (move != -1) return move;
        
        if (board.isCellEmpty(4)) return 4;
        
        int[] corners = {0, 2, 6, 8};
        for (int corner : corners) {
            if (board.isCellEmpty(corner)) return corner;
        }
        
        for (int i = 0; i < 9; i++) {
            if (board.isCellEmpty(i)) return i;
        }
        
        return -1;
    }
    
    private int findBestMove(Board board, char testSymbol) {
        for (int i = 0; i < 9; i++) {
            if (board.isCellEmpty(i)) {
                char originalValue = board.getCell(i);
                
                board.setCell(i, testSymbol);
                boolean wins = board.checkWinner() == testSymbol;
                
                board.setCell(i, originalValue);
                
                if (wins) {
                    return i;
                }
            }
        }
        return -1;
    }
}