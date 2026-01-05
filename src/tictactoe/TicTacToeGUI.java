package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class TicTacToeGUI extends JFrame {
    private GameEngine engine;
    private FileManager fileManager;
    private JButton[][] cells;
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JPanel boardPanel;
    private String gameMode;
    private boolean isProcessing = false;
    
    private static final Color BG_PRIMARY = new Color(248, 250, 252);
    private static final Color BG_SECONDARY = new Color(255, 255, 255);
    private static final Color CARD_BG = new Color(255, 255, 255);
    private static final Color ACCENT_BLUE = new Color(59, 130, 246);
    private static final Color ACCENT_PURPLE = new Color(147, 51, 234);
    private static final Color ACCENT_CYAN = new Color(6, 182, 212);
    private static final Color ACCENT_GREEN = new Color(16, 185, 129);
    private static final Color ACCENT_ORANGE = new Color(251, 146, 60);
    private static final Color ACCENT_RED = new Color(239, 68, 68);
    private static final Color TEXT_PRIMARY = new Color(15, 23, 42);
    private static final Color TEXT_SECONDARY = new Color(71, 85, 105);
    private static final Color BORDER_COLOR = new Color(226, 232, 240);
    private static final Color CELL_BG = new Color(241, 245, 249);
    private static final Color CELL_HOVER = new Color(226, 232, 240);
    
    private Font poppinsLight;
    private Font poppinsRegular;
    private Font poppinsMedium;
    private Font poppinsSemiBold;
    private Font poppinsBold;
    
    public TicTacToeGUI() {
        loadFonts();
        
        engine = new GameEngine();
        fileManager = new FileManager();
        fileManager.loadData(engine.getStats(), engine.getHistory());
        
        setTitle("Tic Tac Toe - OOP Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        
        showMainMenu();
        setVisible(true);
    }
    
    private void loadFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] fonts = ge.getAvailableFontFamilyNames();
            boolean hasPoppins = false;
            for (String font : fonts) {
                if (font.equals("Poppins")) {
                    hasPoppins = true;
                    break;
                }
            }
            
            if (hasPoppins) {
                poppinsLight = new Font("Poppins", Font.PLAIN, 14);
                poppinsRegular = new Font("Poppins", Font.PLAIN, 14);
                poppinsMedium = new Font("Poppins", Font.PLAIN, 14);
                poppinsSemiBold = new Font("Poppins", Font.BOLD, 14);
                poppinsBold = new Font("Poppins", Font.BOLD, 14);
            } else {
                poppinsLight = new Font("SF Pro Display", Font.PLAIN, 14);
                poppinsRegular = new Font("SF Pro Display", Font.PLAIN, 14);
                poppinsMedium = new Font("SF Pro Display", Font.PLAIN, 14);
                poppinsSemiBold = new Font("SF Pro Display", Font.BOLD, 14);
                poppinsBold = new Font("SF Pro Display", Font.BOLD, 14);
            }
        } catch (Exception e) {
            poppinsLight = new Font("Arial", Font.PLAIN, 14);
            poppinsRegular = new Font("Arial", Font.PLAIN, 14);
            poppinsMedium = new Font("Arial", Font.PLAIN, 14);
            poppinsSemiBold = new Font("Arial", Font.BOLD, 14);
            poppinsBold = new Font("Arial", Font.BOLD, 14);
        }
    }
    
    private void showMainMenu() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_PRIMARY);
        mainPanel.setLayout(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(BG_PRIMARY);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(35, 40, 20, 40));
        
        JLabel titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setFont(poppinsBold.deriveFont(50f));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Clarencia, Shanaa, Vania - OOP Project");
        subtitleLabel.setFont(poppinsRegular.deriveFont(15f));
        subtitleLabel.setForeground(TEXT_SECONDARY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitleLabel);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(BG_PRIMARY);
        menuPanel.setLayout(new GridLayout(3, 2, 15, 15));
        menuPanel.setBorder(new EmptyBorder(10, 70, 20, 70));
        
        JButton pvpBtn = createModernButton("Player vs Player", ACCENT_BLUE, "⚔");
        pvpBtn.addActionListener(e -> startGame("PVP"));
        
        JButton pvcBtn = createModernButton("vs Computer", ACCENT_PURPLE, "🤖");
        pvcBtn.addActionListener(e -> startGame("PVC"));
        
        JButton statsBtn = createModernButton("Statistics", ACCENT_CYAN, "📊");
        statsBtn.addActionListener(e -> showStatistics());
        
        JButton historyBtn = createModernButton("History", ACCENT_GREEN, "📜");
        historyBtn.addActionListener(e -> showHistory());
        
        JButton saveBtn = createModernButton("Save Data", ACCENT_ORANGE, "💾");
        saveBtn.addActionListener(e -> {
            fileManager.saveData(engine.getStats(), engine.getHistory());
            showModernDialog("Data saved successfully!", "Success");
        });
        
        JButton resetBtn = createModernButton("Reset", ACCENT_RED, "🔄");
        resetBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Reset all data?", "Confirm", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                engine.getStats().reset();
                engine.getHistory().clear();
                fileManager.clearFile();
                fileManager.saveData(engine.getStats(), engine.getHistory());
                showModernDialog("Data reset successfully!", "Success");
                showMainMenu();
            }
        });
        
        menuPanel.add(pvpBtn);
        menuPanel.add(pvcBtn);
        menuPanel.add(statsBtn);
        menuPanel.add(historyBtn);
        menuPanel.add(saveBtn);
        menuPanel.add(resetBtn);
        
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(BG_PRIMARY);
        footerPanel.setBorder(new EmptyBorder(10, 70, 25, 70));
        
        JPanel statsCard = createCard();
        statsCard.setLayout(new BorderLayout());
        statsCard.setBorder(new EmptyBorder(18, 25, 18, 25));
        
        Statistics stats = engine.getStats();
        String statsText = String.format(
            "X: %d  •  Draw: %d  •  O: %d  •  Total: %d",
            stats.getXWins(), stats.getDraws(), stats.getOWins(), stats.getTotalGames()
        );
        
        JLabel footerLabel = new JLabel(statsText);
        footerLabel.setFont(poppinsSemiBold.deriveFont(17f));
        footerLabel.setForeground(TEXT_PRIMARY);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statsCard.add(footerLabel);
        
        footerPanel.add(statsCard);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        revalidate();
        repaint();
    }
    
    private JPanel createCard() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(CARD_BG);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                
                g2d.setColor(BORDER_COLOR);
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                
                g2d.setColor(new Color(0, 0, 0, 10));
                g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 16, 16);
            }
        };
    }
    
    private JButton createModernButton(String text, Color accentColor, String emoji) {
        JButton btn = new JButton() {
            private boolean isHovered = false;
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2.setColor(accentColor.darker());
                } else if (isHovered) {
                    g2.setColor(accentColor);
                } else {
                    g2.setColor(accentColor);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                
                g2.dispose();
                super.paintComponent(g);
            }
            
            {
                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        isHovered = true;
                        repaint();
                    }
                    public void mouseExited(MouseEvent e) {
                        isHovered = false;
                        repaint();
                    }
                });
            }
        };
        
        btn.setLayout(new BorderLayout());
        
        JPanel emojiPanel = new JPanel();
        emojiPanel.setOpaque(false);
        emojiPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        emojiPanel.add(emojiLabel);
        emojiPanel.setBorder(new EmptyBorder(14, 0, 5, 0));
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(poppinsSemiBold.deriveFont(17f));
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(0, 0, 14, 0));
        
        btn.add(emojiPanel, BorderLayout.NORTH);
        btn.add(textLabel, BorderLayout.CENTER);
        
        btn.setPreferredSize(new Dimension(330, 105));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return btn;
    }
    
    private void startGame(String mode) {
        this.gameMode = mode;
        
        String p1 = JOptionPane.showInputDialog(this, 
            "Enter Player 1 name (X):", "Game Setup", 
            JOptionPane.PLAIN_MESSAGE);
        if (p1 == null || p1.trim().isEmpty()) p1 = "Player 1";
        
        String p2 = "Computer";
        if (mode.equals("PVP")) {
            p2 = JOptionPane.showInputDialog(this, 
                "Enter Player 2 name (O):", "Game Setup", 
                JOptionPane.PLAIN_MESSAGE);
            if (p2 == null || p2.trim().isEmpty()) p2 = "Player 2";
        }
        
        engine.initializePlayers(mode, p1, p2);
        showGameBoard();
    }
    
    private void showGameBoard() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        
        engine.resetGame();
        isProcessing = false;
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_PRIMARY);
        mainPanel.setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(BG_PRIMARY);
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 35, 12, 35));
        
        JButton menuBtn = new JButton("← Back") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed() || getModel().isRollover()) {
                    g2.setColor(CELL_HOVER);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        menuBtn.setFont(poppinsMedium.deriveFont(14f));
        menuBtn.setForeground(TEXT_SECONDARY);
        menuBtn.setFocusPainted(false);
        menuBtn.setBorderPainted(false);
        menuBtn.setContentAreaFilled(false);
        menuBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Exit current game?", "Confirm", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                showMainMenu();
            }
        });
        
        statusLabel = new JLabel(engine.getCurrentPlayer().getName() + " (X)");
        statusLabel.setFont(poppinsBold.deriveFont(28f));
        statusLabel.setForeground(TEXT_PRIMARY);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        topPanel.add(menuBtn, BorderLayout.WEST);
        topPanel.add(statusLabel, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        boardPanel = new JPanel(new GridLayout(3, 3, 14, 14));
        boardPanel.setBackground(BG_PRIMARY);
        boardPanel.setBorder(new EmptyBorder(15, 90, 15, 90));
        
        cells = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                cells[i][j] = createCell(index);
                boardPanel.add(cells[i][j]);
            }
        }
        
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        updateBoard();
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(BG_PRIMARY);
        scorePanel.setBorder(new EmptyBorder(12, 90, 25, 90));
        
        JPanel scoreCard = createCard();
        scoreCard.setLayout(new BorderLayout());
        scoreCard.setBorder(new EmptyBorder(16, 25, 16, 25));
        
        Statistics stats = engine.getStats();
        String scoreText = String.format(
            "%s: %d  •  Draw: %d  •  %s: %d",
            engine.getPlayer1().getName(), stats.getXWins(),
            stats.getDraws(),
            engine.getPlayer2().getName(), stats.getOWins()
        );
        
        scoreLabel = new JLabel(scoreText);
        scoreLabel.setFont(poppinsMedium.deriveFont(17f));
        scoreLabel.setForeground(TEXT_PRIMARY);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreCard.add(scoreLabel);
        
        scorePanel.add(scoreCard);
        mainPanel.add(scorePanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        revalidate();
        repaint();
    }
    
    private JButton createCell(int index) {
        JButton cell = new JButton("") {
            private boolean isHovered = false;
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getText().isEmpty()) {
                    if (isHovered && !isProcessing) {
                        g2.setColor(CELL_HOVER);
                    } else {
                        g2.setColor(CELL_BG);
                    }
                } else {
                    g2.setColor(CELL_BG);
                }
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                
                g2.setColor(BORDER_COLOR);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 16, 16);
                
                g2.dispose();
                super.paintComponent(g);
            }
            
            {
                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        if (getText().isEmpty() && !isProcessing) {
                            isHovered = true;
                            repaint();
                        }
                    }
                    public void mouseExited(MouseEvent e) {
                        isHovered = false;
                        repaint();
                    }
                });
            }
        };
        
        cell.setFont(poppinsBold.deriveFont(65f));
        cell.setFocusPainted(false);
        cell.setBorderPainted(false);
        cell.setContentAreaFilled(false);
        cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        cell.addActionListener(e -> handleCellClick(index));
        
        return cell;
    }
    
    private void handleCellClick(int index) {
        if (isProcessing) {
            return;
        }
        
        if (engine.isComputerTurn()) {
            return;
        }
        
        int row = index / 3;
        int col = index % 3;
        if (!cells[row][col].getText().isEmpty()) {
            showModernDialog("Cell is already occupied!", "Invalid Move");
            return;
        }
        
        if (!engine.getBoard().isCellEmpty(index)) {
            showModernDialog("Cell is already occupied!", "Invalid Move");
            return;
        }
        
        boolean success = engine.makeMove(index);
        if (!success) {
            showModernDialog("Move failed! Try again.", "Error");
            return;
        }
        
        if (engine.getBoard().getCell(index) != engine.getCurrentPlayer().getSymbol()) {
            showModernDialog("An error occurred! Try again.", "Error");
            return;
        }
        
        updateBoard();
        
        char winner = engine.checkWinner();
        if (winner != ' ') {
            endGame(winner);
            return;
        }
        
        if (engine.isBoardFull()) {
            endGame(' ');
            return;
        }
        
        engine.switchPlayer();
        updateStatusLabel();
        
        if (engine.isComputerTurn()) {
            handleComputerTurn();
        }
    }
    
    private void handleComputerTurn() {
        isProcessing = true;
        statusLabel.setText("Computer is thinking...");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setEnabled(false);
            }
        }
        
        Timer timer = new Timer(1000, evt -> {
            int move = engine.makeComputerMove();
            
            if (move == -1) {
                isProcessing = false;
                showModernDialog("Error: Computer cannot move!", "Error");
                showMainMenu();
                return;
            }
            
            updateBoard();
            
            char winner = engine.checkWinner();
            if (winner != ' ') {
                isProcessing = false;
                endGame(winner);
                return;
            }
            
            if (engine.isBoardFull()) {
                isProcessing = false;
                endGame(' ');
                return;
            }
            
            engine.switchPlayer();
            updateStatusLabel();
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int idx = i * 3 + j;
                    if (engine.getBoard().isCellEmpty(idx)) {
                        cells[i][j].setEnabled(true);
                    }
                }
            }
            
            isProcessing = false;
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void updateStatusLabel() {
        Player current = engine.getCurrentPlayer();
        statusLabel.setText(current.getName() + " (" + current.getSymbol() + ")");
    }
    
    private void updateBoard() {
        Board board = engine.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                char cell = board.getCell(index);
                
                if (cell == 'X') {
                    cells[i][j].setText("X");
                    cells[i][j].setForeground(ACCENT_BLUE);
                    cells[i][j].setEnabled(false);
                } else if (cell == 'O') {
                    cells[i][j].setText("O");
                    cells[i][j].setForeground(ACCENT_PURPLE);
                    cells[i][j].setEnabled(false);
                } else {
                    cells[i][j].setText("");
                    cells[i][j].setEnabled(true);
                }
            }
        }
        repaint();
    }
    
    private void endGame(char winner) {
        engine.recordWinner(winner);
        
        String message;
        if (winner == ' ') {
            message = "It's a draw!";
            statusLabel.setText("DRAW");
        } else {
            String winnerName = (winner == 'X') ? 
                engine.getPlayer1().getName() : engine.getPlayer2().getName();
            message = winnerName + " WINS!";
            statusLabel.setText(winnerName + " wins!");
            
            int[] winLine = engine.getBoard().getWinningLine();
            for (int idx : winLine) {
                int row = idx / 3;
                int col = idx % 3;
                cells[row][col].setBackground(ACCENT_GREEN);
            }
        }
        
        Statistics stats = engine.getStats();
        String scoreText = String.format(
            "%s: %d  •  Draw: %d  •  %s: %d",
            engine.getPlayer1().getName(), stats.getXWins(),
            stats.getDraws(),
            engine.getPlayer2().getName(), stats.getOWins()
        );
        scoreLabel.setText(scoreText);
        
        fileManager.saveData(engine.getStats(), engine.getHistory());
        
        Timer delayTimer = new Timer(1500, evt -> {
            int choice = JOptionPane.showConfirmDialog(this, 
                message + "\n\nPlay again?", "Game Over", 
                JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION) {
                engine.resetGame();
                showGameBoard();
            } else {
                showMainMenu();
            }
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
    }
    
    private void showStatistics() {
        Statistics stats = engine.getStats();
        
        JPanel panel = new JPanel();
        panel.setBackground(BG_PRIMARY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));
        
        JLabel titleLabel = new JLabel("GAME STATISTICS");
        titleLabel.setFont(poppinsBold.deriveFont(28f));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        
        String[] lines = {
            String.format("Total Games: %d", stats.getTotalGames()),
            " ",
            String.format("X Wins: %d (%.1f%%)", stats.getXWins(), stats.getWinPercentage('X')),
            String.format("O Wins: %d (%.1f%%)", stats.getOWins(), stats.getWinPercentage('O')),
            String.format("Draws: %d", stats.getDraws())
        };
        
        for (String line : lines) {
            JLabel label = new JLabel(line);
            label.setFont(poppinsRegular.deriveFont(18f));
            label.setForeground(TEXT_PRIMARY);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
            panel.add(Box.createVerticalStrut(12));
        }
        
        JOptionPane.showMessageDialog(this, panel, "Statistics", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void showHistory() {
        java.util.ArrayList<GameResult> history = engine.getHistory().getHistory();
        
        if (history.isEmpty()) {
            showModernDialog("No game history available", "History");
            return;
        }
        
        JPanel panel = new JPanel();
        panel.setBackground(BG_PRIMARY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        JLabel titleLabel = new JLabel("GAME HISTORY");
        titleLabel.setFont(poppinsBold.deriveFont(24f));
        titleLabel.setForeground(TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(25));
        
        for (int i = 0; i < Math.min(10, history.size()); i++) {
            GameResult result = history.get(i);
            JLabel label = new JLabel(String.format("%2d. %s - %s (%s)", 
                i + 1, result.getWinner(), result.getMode(), result.getDate()));
            label.setFont(poppinsRegular.deriveFont(15f));
            label.setForeground(TEXT_PRIMARY);
            panel.add(label);
            panel.add(Box.createVerticalStrut(10));
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        scrollPane.setBorder(null);
        
        JOptionPane.showMessageDialog(this, scrollPane, "History", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void showModernDialog(String message, String title) {
        JPanel panel = new JPanel();
        panel.setBackground(BG_PRIMARY);
        panel.setBorder(new EmptyBorder(35, 45, 35, 45));
        
        JLabel label = new JLabel(message);
        label.setFont(poppinsMedium.deriveFont(16f));
        label.setForeground(TEXT_PRIMARY);
        panel.add(label);
        
        JOptionPane.showMessageDialog(this, panel, title, JOptionPane.PLAIN_MESSAGE);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}