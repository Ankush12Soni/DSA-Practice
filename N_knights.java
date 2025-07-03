public class N_knights {
    public static void main(String[] args) {
        int n = 3;
        boolean[][] board = new boolean[n][n];
        System.out.println(Knight(board, 0));
    }

    static int Knight(boolean[][] board, int row) {
        if (row == board.length) {
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                count += Knight(board, row + 1);
                board[row][col] = false;
            }
        }
        return count;
    }

    static boolean isSafe(boolean[][] board, int row, int col) {
        // Check only positions from where a knight can attack this cell
        if (row - 2 >= 0 && col - 1 >= 0 && board[row - 2][col - 1]) return false;
        if (row - 2 >= 0 && col + 1 < board.length && board[row - 2][col + 1]) return false;
        if (row - 1 >= 0 && col - 2 >= 0 && board[row - 1][col - 2]) return false;
        if (row - 1 >= 0 && col + 2 < board.length && board[row - 1][col + 2]) return false;

        return true;
    }

    static void display(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean cell : row) {
                System.out.print(cell ? "K  " : "X  ");
            }
            System.out.println();
        }
    }
}
