package tictactoe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class GameResult {
    private String winner;
    private String mode;
    private String date;
    private char[] boardState;
    
    public GameResult(String winner, String mode, char[] boardState) {
        this.winner = winner;
        this.mode = mode;
        this.boardState = boardState;
        this.date = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    
    public String getWinner() { return winner; }
    public String getMode() { return mode; }
    public String getDate() { return date; }
    public char[] getBoardState() { return boardState; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - Mode: %s", date, winner, mode);
    }
}