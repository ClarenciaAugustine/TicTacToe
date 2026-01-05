package tictactoe;

import java.util.Arrays;

class Board {
    private char[] cells;
    private static final int SIZE = 9;
    
    public Board() {
        cells = new char[SIZE];
        reset();
    }
    
    public void reset() {
        Arrays.fill(cells, ' ');
    }
    
    public boolean isCellEmpty(int position) {
        return position >= 0 && position < SIZE && cells[position] == ' ';
    }
    
    public void setCell(int position, char symbol) {
        if (position >= 0 && position < SIZE) {
            cells[position] = symbol;
        }
    }
    
    public char getCell(int position) {
        if (position >= 0 && position < SIZE) {
            return cells[position];
        }
        return ' ';
    }
    
    public char[] getCells() {
        return cells.clone();
    }
    
    public boolean isFull() {
        for (char cell : cells) {
            if (cell == ' ') return false;
        }
        return true;
    }
    
    public char checkWinner() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        
        for (int[] pattern : winPatterns) {
            if (cells[pattern[0]] != ' ' &&
                cells[pattern[0]] == cells[pattern[1]] &&
                cells[pattern[0]] == cells[pattern[2]]) {
                return cells[pattern[0]];
            }
        }
        
        return ' ';
    }
    
    public int[] getWinningLine() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        
        for (int[] pattern : winPatterns) {
            if (cells[pattern[0]] != ' ' &&
                cells[pattern[0]] == cells[pattern[1]] &&
                cells[pattern[0]] == cells[pattern[2]]) {
                return pattern;
            }
        }
        
        return new int[0];
    }
}