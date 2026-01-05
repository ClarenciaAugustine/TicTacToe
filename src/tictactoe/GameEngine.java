package tictactoe;

import tictactoe.Board;
import tictactoe.ComputerPlayer;
import tictactoe.GameHistory;
import tictactoe.GameResult;
import tictactoe.HumanPlayer;
import tictactoe.Player;
import tictactoe.Statistics;

class GameEngine {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Statistics stats;
    private GameHistory history;
    private String gameMode;
    
    public GameEngine() {
        this.board = new Board();
        this.stats = new Statistics();
        this.history = new GameHistory();
    }
    
    public void initializePlayers(String mode, String p1Name, String p2Name) {
        this.gameMode = mode;
        player1 = new HumanPlayer(p1Name, 'X');
        
        if (mode.equals("PVP")) {
            player2 = new HumanPlayer(p2Name, 'O');
        } else {
            player2 = new ComputerPlayer("Computer", 'O');
        }
        
        currentPlayer = player1;
    }
    
    public void resetGame() {
        board.reset();
        currentPlayer = player1;
    }
    
    public boolean makeMove(int position) {
        if (position < 0 || position >= 9) {
            return false;
        }
        
        if (!board.isCellEmpty(position)) {
            return false;
        }

        board.setCell(position, currentPlayer.getSymbol());

        if (board.getCell(position) != currentPlayer.getSymbol()) {
            return false;
        }
        
        return true;
    }
    
    public int makeComputerMove() {
        if (!(currentPlayer instanceof ComputerPlayer)) {
            return -1;
        }
        
        int move = currentPlayer.makeMove(board);
        
        if (move == -1) {
            return -1;
        }
        
        if (!board.isCellEmpty(move)) {
            for (int i = 0; i < 9; i++) {
                if (board.isCellEmpty(i)) {
                    move = i;
                    break;
                }
            }
        }
        
        if (move != -1 && board.isCellEmpty(move)) {
            board.setCell(move, currentPlayer.getSymbol());
            return move;
        }
        
        return -1;
    }
    
    public char checkWinner() {
        return board.checkWinner();
    }
    
    public boolean isBoardFull() {
        return board.isFull();
    }
    
    public void recordWinner(char winner) {
        stats.recordWin(winner);
        String winnerName = (winner == ' ') ? "Draw" : 
            ((winner == 'X') ? player1.getName() : player2.getName()) + " menang";
        history.addResult(new GameResult(winnerName, gameMode, board.getCells()));
    }
    
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
    
    public Board getBoard() { return board; }
    public Player getCurrentPlayer() { return currentPlayer; }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public Statistics getStats() { return stats; }
    public GameHistory getHistory() { return history; }
    public boolean isComputerTurn() { return currentPlayer instanceof ComputerPlayer; }
}