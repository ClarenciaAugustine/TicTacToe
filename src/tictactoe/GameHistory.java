package tictactoe;

import java.util.ArrayList;

class GameHistory {
    private ArrayList<GameResult> history;
    private static final int MAX_HISTORY = 20;
    
    public GameHistory() {
        history = new ArrayList<>();
    }
    
    public void addResult(GameResult result) {
        history.add(0, result);
        if (history.size() > MAX_HISTORY) {
            history.remove(history.size() - 1);
        }
    }
    
    public ArrayList<GameResult> getHistory() {
        return history;
    }
    
    public void clear() {
        history.clear();
    }
}