import java.util.Scanner;

public class TicTacToeMinimax {
    public static void main(String[] args) {
        // Initialize input
        Scanner input = new Scanner(System.in);
        // Initialize board
        MinimaxBoard board = new MinimaxBoard();

        MinimaxAgent agent = new MinimaxAgent('o');

        // Variable for depth (difficulty)
        int depth;

        // Variables for collecting input
        int row;
        int col;

        // Flag if board.setRowCol() is successful
        boolean turnComplete = false;

        // Flag to end game loop
        boolean gameOver = false;

        char answer;

        int[] agentMove;
        while (!gameOver) {

            while (true) {
                // Can probably handle larger depths, but not sure if difficulty will really increase
                System.out.print("Choose a difficulty: 1-2-3 \nEnter 0 for a custom depth: ");
                depth = input.nextInt();

                if (depth == 0) {
                    System.out.print("Enter a depth: ");
                    depth = input.nextInt();
                    break;
                }

                if (depth > 0 && depth < 4) {
                    break;
                }
                System.out.println("Invalid input");

            }
            // Initial board print
            board.print();

            while (true) {
                // Ask for x player move
                System.out.print("x player: Enter row and column numbers:");
                row = input.nextInt() - 1;
                col = input.nextInt() - 1;
                // Continue prompts if move was not valid
                while (!turnComplete) {
                    turnComplete = board.setRowCol(row, col);
                    if (!turnComplete) {
                        System.out.print("Enter row and column numbers:");
                        row = input.nextInt() - 1;
                        col = input.nextInt() - 1;
                    }
                }
                // Reset flag for next turn
                turnComplete = false;
                // Change currentPlayer
                board.changePlayer();

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

                System.out.println(board.getCurrentPlayer() + " player turn!");
                System.out.println("Thinking!...");

                // Minimax agent move
                agentMove = agent.minimax(board.getBoard(), depth, -10000, 10000, true);
                board.setRowCol(agentMove[0], agentMove[1]);

                // Change currentPlayer
                board.changePlayer();

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

            while (true) {
                System.out.print("Would you like to play again? Y/N: ");
                answer = input.next().charAt(0);

                if (Character.toLowerCase(answer) == 'y') {
                    board.clear();
                    break;
                }
                if (Character.toLowerCase(answer) == 'n') {
                    gameOver = true;
                    break;
                }
                    System.out.println("Invalid input!");
            }
        }
            System.out.println("Goodbye!");
    }
}

