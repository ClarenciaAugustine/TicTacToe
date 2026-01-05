package tictactoe;

class CellOccupiedException extends Exception {
    public CellOccupiedException(String message) {
        super(message);
    }
}