import java.util.Scanner;

public class Queen {
    public static final int SIZE = 8;
    public static final char QUEEN_SYMBOL = 'q';
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] chessBoard = readChessBoard(scanner);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (chessBoard[row][col] == QUEEN_SYMBOL) {
                    if (isValidQueen(chessBoard, row, col)) {
                        System.out.println(row + " " + col);
                    }
                }
            }
        }
    }

    private static boolean isValidQueen(char[][] chessBoard, int row, int col) {
        return isQueenColumnValid(chessBoard, col) &&
                isQueenRowValid(chessBoard, row) &&
                isQueenFirstDiagonalValid(chessBoard, row, col) &&
                isQueenSecondDiagonalValid(chessBoard, row, col);
    }

    private static boolean isQueenSecondDiagonalValid(char[][] board, int row, int col) {
        int queenCount = 0;

        for (int i = 1; row - i >= 0 && col + i < SIZE; i++) {
            if (board[row - i][col + i] == QUEEN_SYMBOL) {
                queenCount++;
            }
        }
        for (int i = 1; row + i < SIZE && col - i >= 0; i++) {
            if (board[row + i][col - i] == QUEEN_SYMBOL) {
                queenCount++;
            }
        }

        return queenCount == 0;
    }

    private static boolean isQueenFirstDiagonalValid(char[][] board, int row, int col) {
        int queenCount = 0;
        
        //Check upper left part of diagonal
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (board[row - i][col - i] == QUEEN_SYMBOL) {
                queenCount++;
            }
        }

        //Check lower right part of diagonal
        for (int i = 1; row + i < SIZE && col + i < SIZE; i++) {
            if (board[row + i][col + i] == QUEEN_SYMBOL) {
                queenCount++;
            }
        }

        return queenCount == 0;
    }

    private static boolean isQueenRowValid(char[][] board, int row) {
        int queenCount = 0;
        for (int col = 0; col < board[row].length; col++) {
            if (board[row][col] == QUEEN_SYMBOL) {
                queenCount++;
            }
        }
        return queenCount == 1;
    }

    private static boolean isQueenColumnValid(char[][] board, int col) {
        int queenCount = 0;
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == QUEEN_SYMBOL) {
                queenCount++;
            }
        }

        return queenCount == 1;
    }

    private static char[][] readChessBoard(Scanner scanner) {
        char[][] board = new char[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            String[] line = scanner.nextLine().split("\\s+");
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = line[col].charAt(0);
            }
        }
        return board;
    }
}
