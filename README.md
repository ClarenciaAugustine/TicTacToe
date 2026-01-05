TIC TAC TOE - Project OOP 
Dosen : D5387 - Pak Harvianto Harvianto S.Kom, M.T.I. 
Kelas : LX01 
Anggota Kelompok : 
1) Clarencia Augustine - 2802445311
2) Shanaa Salsabil Erawan - 2802445223
3) Vania Alicia Yocelin - 2802442612

DESKRIPSI APLIKASI
Tic Tac Toe adalah implementasi modern dari game klasik Tic Tac Toe dengan antarmuka grafis yang elegan menggunakan Java Swing. 
Aplikasi ini dikembangkan dengan menerapkan konsep Object Oriented Programming secara menyeluruh, memungkinkan pemain untuk 
bermain melawan pemain lain (Player vs Player) atau melawan komputer dengan AI (Player vs Computer).

FITUR UTAMA 
1. Antarmuka Modern - GUI yang clean dengan design light mode dan smooth animations
2. Dua Mode Permainan - Player vs Player dan Player vs Computer
3. AI Computer - Algoritma cerdas dengan strategi menang/block/center/corner
4. Statistics Tracking - Pencatatan lengkap statistik permainan dengan persentase
5. Game History - Riwayat 20 permainan terakhir dengan detail lengkap
6. Save/Load System - Penyimpanan otomatis data ke file TXT
7. Reset Functionality - Reset semua data dengan konfirmasi
8. Input Validation - Validasi multi-layer untuk mencegah error
9. Exception Handling - Penanganan error yang robust

CARA MENJALANKAN 
1. Buka IDE Eclipse
2. Import Project -> file TicTacToe
3. Pastikan struktur folder sudah seperti ini :
project/
   └── tictactoe/
       ├── TicTacToeGUI.java
       ├── GameEngine.java
       ├── Board.java
       ├── Player.java
       ├── HumanPlayer.java
       ├── ComputerPlayer.java
       ├── Statistics.java
       ├── GameHistory.java
       ├── GameResult.java
       ├── FileManager.java
       ├── InvalidInputException.java
       └── CellOccupiedException.java
4. Run TicTacToeGUI.java sebagai main class
5. Aplikasi akan terbuka dalam window GUI

DAFTAR CLASS DAN FUNGSINYA
1. TicTacToeGUI.java
Fungsi: Main class untuk GUI dan user interface
Komponen:
- Mengelola semua tampilan (Main Menu, Game Board, Dialogs)
- Handle user interactions (button clicks, events)
- Koordinasi antara GUI dan game logic
- Rendering visual elements dengan Swing components
Key Attributes:
- GameEngine engine - Instance game logic
- FileManager fileManager - Instance file operations
- JButton[][] cells - Array 3x3 button untuk game board
- JLabel statusLabel - Label untuk status game
- boolean isProcessing - Flag untuk prevent concurrent actions
Key Methods:
- showMainMenu() - Menampilkan menu utama
- showGameBoard() - Menampilkan papan permainan
- startGame(String mode) - Inisialisasi game baru
- handleCellClick(int index) - Handle klik pada cell
- handleComputerTurn() - Menangani giliran computer
- createModernButton() - Factory method untuk button dengan style

2. GameEngine.java
Fungsi: Core business logic dan game state management
Komponen:
- Mengatur alur permainan dari awal hingga akhir
- Koordinasi antara Board, Player, Statistics, dan History
- Validasi moves dan turn management
- Winner detection dan game result recording
Key Attributes:
- Board board - Instance papan permainan
- Player player1, player2, currentPlayer - Object pemain
- Statistics stats - Instance statistics tracker
- GameHistory history - Instance history manager
- String gameMode - Mode game saat ini (PVP/PVC)
Key Methods:
- initializePlayers(mode, p1, p2) - Setup pemain berdasarkan mode
- makeMove(int position) - Validasi dan eksekusi move pemain
- makeComputerMove() - Eksekusi move computer dengan AI
- checkWinner() - Cek apakah ada pemenang
- switchPlayer() - Ganti pemain aktif
- recordWinner(char winner) - Catat hasil game
- resetGame() - Reset board untuk game baru

3. Board.java
Fungsi: Representasi dan management state board 3x3
Komponen:
- Menyimpan state 9 cells dalam array
- Validasi cell availability
- Winner detection dengan checking patterns
- Board state operations (get, set, reset)
Key Attributes:
- char[] cells - Array 9 elemen untuk state board
- static final int SIZE = 9 - Ukuran board
Key Methods:
- setCell(int position, char symbol) - Set nilai cell
- getCell(int position) - Get nilai cell
- isCellEmpty(int position) - Cek apakah cell kosong
- checkWinner() - Deteksi pemenang dengan 8 winning patterns
- getWinningLine() - Return indices winning line untuk highlight
- isFull() - Cek apakah board penuh (draw)
- reset() - Reset semua cell ke kosong
- getCells() - Return copy array untuk save state

4. Player.java (Abstract Class)
Fungsi: Blueprint/template untuk semua tipe player
Komponen:
- Mendefinisikan attribute dasar setiap player
- Abstract method yang wajib diimplementasi subclass
- Template pattern untuk player behavior
Key Attributes:
- protected String name - Nama pemain
- protected char symbol - Symbol pemain (X/O)
- protected int wins - Jumlah kemenangan
Key Methods:
- abstract int makeMove(Board board) - Method abstract (wajib override)
- getName() - Getter nama
- getSymbol() - Getter symbol
- getWins() - Getter jumlah wins
- incrementWins() - Increment win counter

5. HumanPlayer.java (extends Player)
Fungsi: Representasi pemain manusia
Komponen:
- Implementasi player untuk input dari user
- Store selected move dari GUI interaction
Key Attributes:
- private int selectedMove - Move yang dipilih user
Key Methods:
- makeMove(Board board) - Override: return selectedMove
- setSelectedMove(int move) - Set move dari GUI

6. ComputerPlayer.java (extends Player)
Fungsi: Representasi pemain computer dengan AI
Komponen:
- Implementasi AI algorithm untuk strategic gameplay
- Simulasi moves untuk decision making
- Prioritized strategy: win → block → center → corner → random
Key Methods:
- makeMove(Board board) - Override: return best move dari AI
- findBestMove(Board, char) - Simulasi move untuk cek win/block
AI Logic:
- Cek apakah bisa menang → ambil move tersebut
- Cek apakah lawan bisa menang → block move tersebut
- Ambil center (posisi 4) jika kosong
- Ambil corner (0,2,6,8) yang kosong
- Ambil cell manapun yang kosong

7. Statistics.java
Fungsi: Tracking dan kalkulasi statistik permainan
Komponen:
- Record wins untuk X, O, dan draws
- Calculate percentages
- Manage total games counter
Key Attributes:
- private int xWins - Jumlah kemenangan X
- private int oWins - Jumlah kemenangan O
- private int draws - Jumlah draw
- private int totalGames - Total game yang dimainkan
Key Methods:
- recordWin(char winner) - Catat pemenang dan increment counter
- getWinPercentage(char symbol) - Hitung persentase win
- reset() - Reset semua statistics ke 0
- Getter/Setter untuk semua attributes

8. GameHistory.java
Fungsi: Management riwayat permainan
Komponen:
- Store history dalam ArrayList dengan max 20 entries
- FIFO queue implementation (oldest removed when full)
- Provide access ke history data
Key Attributes:
- private ArrayList<GameResult>> history - List hasil game
- static final int MAX_HISTORY = 20 - Batas maksimal history
Key Methods:
- addResult(GameResult result) - Tambah game baru ke history
- getHistory() - Return list history
- clear() - Hapus semua history

9. GameResult.java
Fungsi: Data class untuk satu hasil permainan
Komponen:
- Store informasi detail satu game yang selesai
- Auto-generate timestamp saat object created
- Immutable data holder
Key Attributes:
- private String winner - Nama pemenang atau "Draw"
- private String mode - Mode game (PVP/PVC)
- private String date - Timestamp format dd/MM/yyyy HH:mm:ss
- private char[] boardState - State board akhir
Key Methods:
- Constructor - Auto-set date saat pembuatan
- Getter methods untuk semua attributes
- toString() - Format string untuk display

10. FileManager.java
Fungsi: Handle file I/O operations
Komponen:
- Save data ke file tictactoe_data.txt
- Load data dari file saat startup
- Format data dalam struktur readable
- Exception handling untuk I/O errors
Key Attributes:
- static final String FILENAME = "tictactoe_data.txt" - Nama file
Key Methods:
- saveData(Statistics, GameHistory) - Write data ke file
    - Format terstruktur dengan separator lines
    - Include statistics dan history
    - Auto-create file jika belum ada
- loadData(Statistics, GameHistory) - Read data dari file
    - Parse statistics values
    - Handle missing file scenario
    - Exception handling untuk corrupted data
- clearFile() - Clear file content untuk reset

File Format 
============================================================
             TIC TAC TOE - GAME DATA
============================================================

STATISTIK:
------------------------------------------------------------
Total Games: 10
X Wins: 4 (40.0%)
O Wins: 3 (30.0%)
Draws: 3

HISTORY PERMAINAN:
------------------------------------------------------------
1. [01/01/2026 14:30:15] Clarence menang - Mode: PVP
2. [01/01/2026 14:25:10] Draw - Mode: PVC
...

11. InvalidInputException.java
Fungsi: Custom exception untuk input tidak valid
Komponen:
- Extends Exception class
- Custom exception untuk input validation failures
Usage Example:
if (position < 0 || position >= 9) {
    throw new InvalidInputException("Position must be 0-8");
}

12. CellOccupiedException.java
Fungsi: Custom exception untuk cell yang sudah terisi
Komponen:
- Extends Exception class
- Custom exception untuk occupied cell scenarios
Usage Example:
if (!board.isCellEmpty(position)) {
    throw new CellOccupiedException("Cell already occupied");
}

PENJELASAN KONSEP OOP YANG DIPAKAI

1. Encapsulation (Enkapsulasi)
Definisi: Menyembunyikan data internal class dan hanya menyediakan akses melalui method public.

Implementasi dalam Project:

Contoh 1: Board Class
class Board {
    private char[] cells;  // Tidak bisa diakses langsung dari luar
    
    // Akses hanya melalui method public dengan validasi
    public char getCell(int position) {
        if (position >= 0 && position < SIZE) {
            return cells[position];
        }
        return ' ';
    }
    
    public void setCell(int position, char symbol) {
        if (position >= 0 && position < SIZE) {
            cells[position] = symbol;
        }
    }
}

Manfaat:
- Data cells terlindungi dari akses/modifikasi tidak valid
- Validasi terpusat di setter method
- Perubahan implementasi internal tidak affect code luar

Contoh 2: Statistics Class
class Statistics {
    private int xWins;     // Private data
    private int oWins;
    private int draws;
    private int totalGames;
    
    // Public interface dengan logic
    public void recordWin(char winner) {
        totalGames++;
        if (winner == 'X') xWins++;
        else if (winner == 'O') oWins++;
        else draws++;
    }
    
    public double getWinPercentage(char symbol) {
        if (totalGames == 0) return 0.0;
        int wins = (symbol == 'X') ? xWins : oWins;
        return (wins * 100.0) / totalGames;
    }
}

Manfaat:
- Logic calculation tersembunyi dalam method
- Prevent invalid state (misalnya totalGames < xWins + oWins + draws)
- Consistency terjamin

Prinsip Encapsulation di Semua Class

Class           Private Attributes         Public Methods
------------------------------------------------------------------------------------
Board           cells[]                    getCell(), setCell(), isCellEmpty()
------------------------------------------------------------------------------------
Player          name, symbol, wins         getName(), getSymbol(), getWins()
------------------------------------------------------------------------------------
GameEngine      board, players, stats      makeMove(), checkWinner(), switchPlayer()
------------------------------------------------------------------------------------
GameHistory     ArrayList history          addResult(), getHistory()
------------------------------------------------------------------------------------

2. Inheritance (Pewarisan) 
Definisi: Mekanisme dimana class baru (child) mewarisi properties dan methods dari class yang sudah ada (parent).

Implementasi dalam Project:
Hierarki Class:
    Player (Abstract Parent Class)
                  |
           -------|-------
           |             |
     HumanPlayer   ComputerPlayer
      (Child)        (Child)

Parent Class: Player.java
abstract class Player {
    protected String name;      // protected = bisa diakses child
    protected char symbol;
    protected int wins;
    
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
    }
    
    // Abstract method - child wajib implement
    public abstract int makeMove(Board board);
    
    // Concrete method - child bisa pakai langsung
    public String getName() { return name; }
    public char getSymbol() { return symbol; }
    public void incrementWins() { wins++; }
}

Child Class 1: HumanPlayer.java
class HumanPlayer extends Player {
    private int selectedMove;  // Attribute tambahan
    
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);  // Panggil constructor parent
    }
    
    @Override
    public int makeMove(Board board) {
        return selectedMove;  // Implementasi spesifik untuk human
    }
    
    public void setSelectedMove(int move) {
        this.selectedMove = move;
    }
}

Child Class 2: ComputerPlayer.java
class ComputerPlayer extends Player {
    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);  // Reuse parent constructor
    }
    
    @Override
    public int makeMove(Board board) {
        // Implementasi berbeda - menggunakan AI algorithm
        int move = findBestMove(board, symbol);
        if (move != -1) return move;
        
        // Strategy: win → block → center → corner → random
        // ...
    }
    
    private int findBestMove(Board board, char testSymbol) {
        // AI logic specific untuk computer
    }
}

Manfaat Inheritance:
- Code Reuse: name, symbol, wins tidak perlu ditulis ulang
- Consistent Interface: Semua Player punya getName(), getSymbol()
- Extensibility: Mudah tambah player type baru (EasyAI, HardAI, etc)
- Maintenance: Perubahan di Player otomatis berlaku untuk semua child

3. Polymorphism (Polimorfisme)
Definisi: Kemampuan object untuk mengambil berbagai bentuk. Satu interface dapat diimplementasikan dengan cara berbeda.
Implementasi dalam Project:

Polymorphic Variables:
class GameEngine {
    private Player player1;  // Tipe Player (parent)
    private Player player2;  // Bisa hold HumanPlayer ATAU ComputerPlayer
    private Player currentPlayer;
    
    public void initializePlayers(String mode, String p1, String p2) {
        // Polymorphic assignment
        player1 = new HumanPlayer(p1, 'X');  // HumanPlayer object
        
        if (mode.equals("PVP")) {
            player2 = new HumanPlayer(p2, 'O');  // HumanPlayer object
        } else {
            player2 = new ComputerPlayer("Computer", 'O');  // ComputerPlayer object
        }
        
        currentPlayer = player1;
    }
}

Variabel player1, player2, currentPlayer bertipe Player tapi bisa hold object HumanPlayer atau ComputerPlayer

Runtime Polymorphism (Method Overriding):
// Di GameEngine
public int makeComputerMove() {
    // currentPlayer bisa HumanPlayer atau ComputerPlayer
    int move = currentPlayer.makeMove(board);  
    // Method yang dipanggil ditentukan saat RUNTIME
    // berdasarkan tipe object sebenarnya
    
    // Jika currentPlayer = HumanPlayer → panggil HumanPlayer.makeMove()
    // Jika currentPlayer = ComputerPlayer → panggil ComputerPlayer.makeMove()
}

Type Checking dengan instanceof:
public boolean isComputerTurn() {
    return currentPlayer instanceof ComputerPlayer;
}

// Usage di GUI
if (engine.isComputerTurn()) {
    handleComputerTurn();  // Special handling untuk computer
} else {
    // Normal human player turn
}

Polymorphic Method Calls:
// Contoh di berbagai scenario:

Player p = new HumanPlayer("Alice", 'X');
p.makeMove(board);  // Calls HumanPlayer.makeMove()

p = new ComputerPlayer("Bot", 'O');
p.makeMove(board);  // Calls ComputerPlayer.makeMove()

// Same variable, same method call, DIFFERENT behavior!

Manfaat Polymorphism:
- Flexibility: Satu variabel bisa handle multiple types
- Extensibility: Tambah player type baru tanpa ubah GameEngine
- Simplification: Tidak perlu if-else untuk setiap player type
- Dynamic Binding: Method resolution di runtime, bukan compile time

Real-World Analogy:
Player = "Pemain" (konsep umum)
HumanPlayer = "Pemain Manusia" (implementasi spesifik)
ComputerPlayer = "Pemain Computer" (implementasi spesifik)

GameEngine tidak peduli siapa pemainnya, yang penting bisa makeMove()

4. Abstraction (Abstraksi) 
Definisi: Menyembunyikan detail implementasi kompleks dan hanya menampilkan fungsionalitas essensial.
Implementasi dalam Project:

Level 1: Abstract Class
abstract class Player {
    protected String name;
    protected char symbol;
    
    // Abstract method - no implementation
    // Child class MUST provide implementation
    public abstract int makeMove(Board board);
    
    // Concrete methods
    public String getName() { return name; }
    public char getSymbol() { return symbol; }
}

User tidak perlu tahu HOW player make move, hanya tahu BAHWA player CAN make move

Level 2: Interface Abstraction
// Di GameEngine - user hanya lihat interface sederhana:

public char checkWinner() {
    return board.checkWinner();
}
// User tidak perlu tahu tentang 8 winning patterns
// Detail implementasi tersembunyi di Board class

public void switchPlayer() {
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
}
// User hanya panggil switchPlayer(), detail logic tersembunyi

Level 3: High-Level Abstraction di GUI
// User hanya klik button, semua complexity tersembunyi:

private void handleCellClick(int index) {
    // High-level operations
    engine.makeMove(index);      // Abstract: tidak perlu tahu validasi internal
    updateBoard();               // Abstract: tidak perlu tahu rendering detail
    
    char winner = engine.checkWinner();  // Abstract: tidak perlu tahu algorithm
    if (winner != ' ') {
        endGame(winner);         // Abstract: tidak perlu tahu save/display logic
    }
}

Layers of Abstraction:
┌─────────────────────────────────────┐
│  TicTacToeGUI (Presentation Layer)  │  ← User Interface
│  - User clicks, dialogs, display    │
└─────────────────┬───────────────────┘
                  │ calls
┌─────────────────▼───────────────────┐
│   GameEngine (Business Logic)       │  ← Game Rules
│   - makeMove(), checkWinner()       │
└─────────────────┬───────────────────┘
                  │ uses
┌─────────────────▼───────────────────┐
│   Board, Player (Data Layer)        │  ← Core Data
│   - cells[], makeMove()             │
└─────────────────────────────────────┘

Setiap layer hanya expose yang perlu, hide yang tidak perlu

Abstraction dalam FileManager:
// User hanya panggil:
fileManager.saveData(stats, history);

// Tidak perlu tahu:
// - FileWriter usage
// - Format file
// - Exception handling
// - File path resolution
// Semua detail kompleks tersembunyi!

public void saveData(Statistics stats, GameHistory history) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
        // Complex formatting logic hidden here
        writer.println("=".repeat(60));
        writer.println("TIC TAC TOE - GAME DATA");
        // ... 30+ lines of implementation detail
    } catch (IOException e) {
        // Error handling hidden from user
    }
}

Abstraction dalam ComputerPlayer AI:
// User hanya lihat:
int move = computerPlayer.makeMove(board);

// Tidak perlu tahu:
// - Winning move detection algorithm
// - Blocking strategy
// - Priority logic (center → corner → random)
// - Board state simulation

@Override
public int makeMove(Board board) {
    // Complex AI logic abstracted away
    int move = findBestMove(board, symbol);
    if (move != -1) return move;
    
    move = findBestMove(board, opponentSymbol);
    if (move != -1) return move;
    
    // ... more complex logic
}

Manfaat Abstraction:
- Simplicity: Complex system jadi simple interface
- Maintainability: Ubah implementation tanpa affect caller
- Modularity: Setiap class fokus pada tanggung jawabnya
- Understandability: Easier to understand high-level flow

DIAGRAM STRUKTUR CLASS
┌──────────────────┐
│  TicTacToeGUI    │ ◄──── Main Entry Point
└────────┬─────────┘
         │ uses
    ┌────▼─────┐
    │GameEngine│ ◄──── Core Business Logic
    └────┬─────┘
         │ uses
    ┌────▼────┬──────────┬────────────┬─────────────┐
    │         │          │            │             │
┌───▼──┐  ┌───▼──┐  ┌────▼─────┐  ┌───▼───┐  ┌──────▼────┐
│Board │  │Player│  │Statistics│  │History│  │FileManager│
└──────┘  └──┬───┘  └──────────┘  └───────┘  └───────────┘
             │
      ┌──────┴──────┐
      │             │
┌─────▼─────┐  ┌────▼─────────┐
│HumanPlayer│  │ComputerPlayer│
└───────────┘  └──────────────┘

