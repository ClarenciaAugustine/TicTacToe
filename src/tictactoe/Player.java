package tictactoe;

import tictactoe.Board;

abstract class Player {
    protected String name;
    protected char symbol;
    protected int wins;
    
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }
    
    public abstract int makeMove(Board board);
    
    public String getName() { return name; }
    public char getSymbol() { return symbol; }
    public int getWins() { return wins; }
    public void incrementWins() { wins++; }
    public void setName(String name) { this.name = name; }
}