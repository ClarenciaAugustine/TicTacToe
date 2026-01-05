package tictactoe;

class Statistics {
    private int xWins;
    private int oWins;
    private int draws;
    private int totalGames;
    
    public Statistics() {
        this.xWins = 0;
        this.oWins = 0;
        this.draws = 0;
        this.totalGames = 0;
    }
    
    public void recordWin(char winner) {
        totalGames++;
        if (winner == 'X') xWins++;
        else if (winner == 'O') oWins++;
        else draws++;
    }
    
    public int getXWins() { return xWins; }
    public int getOWins() { return oWins; }
    public int getDraws() { return draws; }
    public int getTotalGames() { return totalGames; }
    
    public void setXWins(int wins) { this.xWins = wins; }
    public void setOWins(int wins) { this.oWins = wins; }
    public void setDraws(int draws) { this.draws = draws; }
    
    public double getWinPercentage(char symbol) {
        if (totalGames == 0) return 0.0;
        int wins = (symbol == 'X') ? xWins : oWins;
        return (wins * 100.0) / totalGames;
    }
    
    public void reset() {
        xWins = 0;
        oWins = 0;
        draws = 0;
        totalGames = 0;
    }
}