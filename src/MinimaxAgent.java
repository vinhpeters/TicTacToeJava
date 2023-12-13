import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinimaxAgent {
    private char agentChar;
    private char opponentChar;
    public MinimaxAgent(char agentChar) {
        // Set
        this.agentChar = agentChar;
        this.opponentChar = (agentChar == 'o') ? 'x' : 'o';
    }


    /**
    * Calculates the best move for the agent using the minimax algorithm with alpha-beta pruning
    * Recurvisley calculates score for each possible move using the heuristic scoring function
    * Assumes that the opponent will always choose the best available move for themselves
    * The agent will also choose the best move each time
    *
    * Depth limits the size of the game tree
    *
    * function minimax(node, depth, maximizingPlayer) is
    *   if depth = 0 or node is a terminal node then
    *       return the heuristic value of node
    *   if maximizingPlayer then
    *       value := −∞
    *       for each child of node do
    *           value := max(value, minimax(child, depth − 1, FALSE))
    *       return value
    *   else (* minimizing player *)
    *       value := +∞
    *       for each child of node do
    *           value := min(value, minimax(child, depth − 1, TRUE))
    *       return value
    *
    * @return int[] containing row and col of best move, heuristic score
    **/
    public int[] minimax(char[][] boardState, int depth, int alpha, int beta, boolean maxPlayer) {

        List<int[]> openCells = getOpenCells(boardState);
        boolean terminal = isTerminal(boardState);

        Random rdm = new Random();

        if (depth == 0 || terminal) {
            if (terminal) {
                if (MinimaxBoard.isWin(boardState, this.agentChar)) {
                    return new int[]{-1, -1, 10000};
                } else if (MinimaxBoard.isWin(boardState, this.opponentChar)) {
                    return new int[]{-1, -1, -10000};
                } else {
                    return new int[]{-1, -1, 0};
                }
            } else {
                return new int[]{-1, -1, this.heuristicScore(boardState, this.agentChar)};
            }
        }

        // Maximizing branch
        // Chooses the best move for the maximizing player (agent)
        // Maximize bestScore
        if (maxPlayer) {
            // Initialize the agent score to -inf
            int bestScore = -10000;
            int[] bestMove = openCells.get(rdm.nextInt(openCells.size()));
            int runningScore;

            for (int i = 0; i < openCells.size(); i++) {
                int[] move = openCells.get(i);
                // Create a deep copy of the board state to avoid altering the real game state
                char[][] boardStateCopy = copyBoard(boardState);
                boardStateCopy[move[0]][move[1]] = agentChar;

                // Recurvisley call minimax to calculate game tree from new board state
                runningScore = minimax(boardStateCopy, depth - 1, alpha, beta, false)[2];
                if (runningScore > bestScore) {
                    bestScore = runningScore;
                    bestMove = move;
                }
                // Can stop early if best possible value is already found
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }

            // Return best move and score
            return new int[]{bestMove[0], bestMove[1], bestScore};

        }

        // Minimizng branch
        // Goal is to find moves that minimizes the opponents score
        // Minimize bestScore
        else {
            // Initialize the opponents score to +inf
            int bestScore = 10000;
            // Initialize the bestMove to a random choice of available moves
            int[] bestMove = openCells.get(rdm.nextInt(openCells.size()));

            // Initialize running score to keep track of min score
            int runningScore;

            // Loop through all available moves
            for (int i = 0; i < openCells.size(); i++) {
                int[] move = openCells.get(i);

                // Create a deep copy of the board state to avoid altering the real game state
                char[][] boardStateCopy = copyBoard(boardState);
                boardStateCopy[move[0]][move[1]] = opponentChar;

                // Recurvisley call minimax to calculate game tree from new board state
                runningScore = minimax(boardStateCopy, depth - 1, alpha, beta, true)[2];

                // Assign min score to bestScore
                if (runningScore < bestScore) {
                    bestScore = runningScore;
                    bestMove = move;
                }

                // Can stop early if best possible value is already found
                beta = Math.min(beta, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
            return new int[]{bestMove[0], bestMove[1], bestScore};
        }

    }

    public static boolean isTerminal(char[][] boardState) {
        return (MinimaxBoard.isFull(boardState) || MinimaxBoard.isWin(boardState, 'x') || MinimaxBoard.isWin(boardState, 'o'));
    }

    private List<int[]> getOpenCells(char[][] boardState) {
        List<int[]> openCells = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardState[i][j] == '-') {
                    int[] coordinate = {i, j};
                    openCells.add(coordinate);
                }
            }
        }
        return openCells;
    }

    /**
     * Heuristic scoring function for the minimax algorithm
     * Scores a boardState for one player
     * Because the center cell has the most possibilities for sequences, it is worth 100 pts
     * The agent will choose the center cell if it is open
     * 2 occupied cells in a row, column or diagonal means victory for that player is imminent
     * The agent will block sequences from becoming 3
     * 1 cell by itself is not as much as a threat, so they are worth 1/4 the value of 2 cells in a row, col or diagonal
     *
     * @return char[3][3] array
     */
    private int heuristicScore(char[][] boardState, char player) {
        int score = 0;

        // Score for occupying center cell
        score += this.centerScore(boardState, player);

        // Score for row sequences
        score += this.rowScore(boardState, player);

        // Score for col sequences
        score += this.colScore(boardState, player);

        // Score for diagonal sequences
        score += this.diagonalScore(boardState, player);


        return score;
    }

    /**
     * The method adds 100 points to the score if the player occupies the
     * center cell
     * @return int score, 100 if center cell is occupied, 0 if cell is not occupied
     */

    private int centerScore(char[][] boardState, char player) {
        return (boardState[1][1] == player) ? 100 : 0;
    }

    /**
     * The method scores sequences along rows
     * Gives 50 pts for 1 cell in a row, 200 pts for 2 cells in a row
     * @return int score
     */
    private int rowScore(char[][] boardState, char player) {
        int score = 0;
        int count;
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 0; j < 3; j++) {
                if (boardState[i][j] == player) {
                    count++;
                }
            }
            switch (count) {
                case 0:
                    break;
                case 1:
                    score += 50;
                    break;
                case 2:
                    score += 200;
                    break;
            }
        }
        return score;
    }

    /**
     * The method scores sequences along the columns
     * Gives 50 pts for 1 cell in a col, 200 pts for 2 cells in a col
     * @return int score
     */
    private int colScore(char[][] boardState, char player) {
        int score = 0;
        int count;
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 0; j < 3; j++) {
                if (boardState[j][i] == player) {
                    count++;
                }
            }
            switch (count) {
                case 0:
                    break;
                case 1:
                    score += 50;
                    break;
                case 2:
                    score += 200;
                    break;
            }
        }
        return score;
    }

    /**
     * The method scores sequences along the two diagonals
     * Gives 50 pts for 1 cell in a diagonal, 200 pts for 2 cells in a diagonal
     * @return int score
     */
    private int diagonalScore(char[][] boardState, char player) {
        char[] topLeft = {boardState[0][0], boardState[1][1], boardState[2][2]};
        char[] topRight = {boardState[0][2], boardState[1][1], boardState[2][0]};
        char[] diagonal = topLeft;
        int count;
        int score = 0;
        for (int i = 0; i < 2; i++) {
            count = 0;
            for (int j = 0; j < 3; j++) {
                if (diagonal[j] == player) {
                    count++;
                }
            }
            switch (count) {
                case 0:
                    break;
                case 1:
                    score += 50;
                    break;
                case 2:
                    score += 200;
                    break;
            }
            diagonal = topRight;
        }
        return score;
    }


    /**
     * The method creates a deep copy of a boardState array
     *
     * @return char[3][3] array
     */
    public static char [][] copyBoard(char[][] boardState){
        char[][] boardStateCopy = new char[3][3];

        for ( int i = 0; i < 3; i++ ) {
            for (int j=0; j<3; j++){
                boardStateCopy[i][j] = boardState[i][j];
            }
        }
        return boardStateCopy;
    }
}
