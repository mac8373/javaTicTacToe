import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[4][4];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        char winner = '\0';

        while (winner == '\0') {
            displayBoard();
            getPlayerMove();
            winner = checkWinner();
            switchPlayer();
        }

        displayBoard();
        if (winner == 'D') {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("  0 1 2 3");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]);
                if (j < 3) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 3) {
                System.out.println("  -+-+-+-");
            }
        }
        System.out.println();
    }

    private static void getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + ", enter row (0, 1, 2, or 3): ");
            row = scanner.nextInt();
            System.out.print("Player " + currentPlayer + ", enter column (0, 1, 2, or 3): ");
            col = scanner.nextInt();
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4 || board[row][col] != ' ') {
            System.out.println("Invalid move! Try again.");
            return false;
        }
        return true;
    }

    private static char checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 4; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer && board[i][3] == currentPlayer) {
                return currentPlayer; // Row win
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer && board[3][i] == currentPlayer) {
                return currentPlayer; // Column win
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer && board[3][3] == currentPlayer) {
            return currentPlayer; // Diagonal win
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer && board[3][3] == currentPlayer) {
            return currentPlayer; // Diagonal win
        }

        // Check for a draw
        boolean isBoardFull = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == ' ') {
                    isBoardFull = false;
                    break;
                }
            }
        }

        return isBoardFull ? 'D' : '\0'; // 'D' for Draw, '\0' for no winner yet
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}

