import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        // Initialize input
        Scanner input = new Scanner(System.in);
        // Initialize board
        Board board = new Board();

        // Variables for collecting input
        int row;
        int col;
        // Flag if board.setRowCol() is successful
        boolean turnComplete = false;

        // Initial board print
        board.print();

        while (true) {
            // Ask for x player move
            System.out.print("x player: Enter row and column numbers:");
            row = input.nextInt();
            col = input.nextInt();
            // Continue prompts if move was not valid
            while (!turnComplete) {
                turnComplete = board.setRowCol(row,col);
                if (!turnComplete) {
                    System.out.print("Enter row and column numbers:");
                    row = input.nextInt();
                    col = input.nextInt();
                }
            }
            // Reset flag for next player
            turnComplete = false;
            // Change currentPlayer
            board.changePlayer();
            // Show new board state
            board.print();
            // Check if last move won the game
            if (board.isWin()) {
                System.out.println("x player wins!");
                break;
            }
            // Check if last move filled the board
            if (board.isFull()) {
                System.out.println("The game is a tie!");
                break;
            }
            // Ask for o player move
            System.out.print("o player: Enter row and column numbers:");
            row = input.nextInt();
            col = input.nextInt();
            // Continue prompts if move was not valid
            while (!turnComplete) {
                turnComplete = board.setRowCol(row,col);
                if (!turnComplete) {
                    System.out.print("Enter row and column numbers:");
                    row = input.nextInt();
                    col = input.nextInt();
                }
            }

            // Reset flag for next player
            turnComplete = false;
            // Change currentPlayer
            board.changePlayer();
            // Show new board state
            board.print();
            // Check if last move won the game
            if (board.isWin()) {
                System.out.println("o player wins!");
                break;
            }
            // Check if last move filled the board
            if (board.isFull()) {
                System.out.println("The game is a tie!");
                break;
            }

        }
        System.out.println("Goodbye!");

    }
}

