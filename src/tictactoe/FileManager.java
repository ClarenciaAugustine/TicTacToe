package tictactoe;

import java.io.*;
import java.util.ArrayList;

class FileManager {
    private static final String FILENAME = "tictactoe_data.txt";
    
    public FileManager() {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }
    
    public void saveData(Statistics stats, GameHistory history) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            writer.println("=".repeat(60));
            writer.println("             TIC TAC TOE - GAME DATA");
            writer.println("=".repeat(60));
            writer.println();
            
            writer.println("STATISTIK:");
            writer.println("-".repeat(60));
            writer.println("Total Games: " + stats.getTotalGames());
            writer.println("X Wins: " + stats.getXWins() + 
                " (" + String.format("%.1f%%", stats.getWinPercentage('X')) + ")");
            writer.println("O Wins: " + stats.getOWins() + 
                " (" + String.format("%.1f%%", stats.getWinPercentage('O')) + ")");
            writer.println("Draws: " + stats.getDraws());
            writer.println();
            
            writer.println("HISTORY PERMAINAN:");
            writer.println("-".repeat(60));
            ArrayList<GameResult> results = history.getHistory();
            if (results.isEmpty()) {
                writer.println("Belum ada history permainan.");
            } else {
                for (int i = 0; i < results.size(); i++) {
                    writer.println((i + 1) + ". " + results.get(i).toString());
                }
            }
            writer.println();
            writer.println("=".repeat(60));
            
            System.out.println("✓ Data saved to: " + new File(FILENAME).getAbsolutePath());
            
        } catch (IOException e) {
            System.err.println("✗ Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void loadData(Statistics stats, GameHistory history) {
        File file = new File(FILENAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No saved data found or file is empty.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Total Games:")) {
                    int total = Integer.parseInt(line.split(": ")[1]);
                    if (total == 0) {
                        System.out.println("Stats is reset, not loading.");
                        return;
                    }
                }
                
                if (line.startsWith("X Wins:")) {
                    String[] parts = line.split(": ")[1].split(" ");
                    stats.setXWins(Integer.parseInt(parts[0]));
                } else if (line.startsWith("O Wins:")) {
                    String[] parts = line.split(": ")[1].split(" ");
                    stats.setOWins(Integer.parseInt(parts[0]));
                } else if (line.startsWith("Draws:")) {
                    stats.setDraws(Integer.parseInt(line.split(": ")[1]));
                }
            }
            
            System.out.println("✓ Data loaded from file.");
        } catch (IOException e) {
            System.err.println("✗ Error loading data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("✗ Error parsing data: " + e.getMessage());
        }
    }
    
    public void clearFile() {
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();
                System.out.println("✓ File cleared.");
            }
        } catch (IOException e) {
            System.err.println("✗ Error clearing file: " + e.getMessage());
        }
    }
}