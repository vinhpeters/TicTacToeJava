public class MinimaxBoard {

    private char[][] board;
    private char currentPlayer;

    /**
     * This is the constructor for the class. It initializes the board so all cells in the board are equal to '-'. The currentPlayer
     * is initialized to 'x'.
     */
    public MinimaxBoard() {
        this.currentPlayer = 'x';
        this.board = new char[3][3];
        // Initialize each cell in board to '-'
        // Loop through rows
        for (int i = 0; i < 3; i++) {
            // Loop through columns
            for (int k = 0; k < 3; k++) {
                this.board[i][k] = '-';
            }
        }
    }

    /**
     * The method outputs the board in the following format. First the message "Current board" underlined is printed.
     * Then the content of the board is printed as a 3 by 3 matrix.
     */
    public void print() {
        // Print header
        System.out.println("Current Board");
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                // Print each cell in the row
                System.out.print(this.board[i][k] + "  ");
            }
            // New line at end of row
            System.out.println();
        }
    }

    /**
     * The method checks if all the positions on the board have been played.
     *
     * @return true if all the cells in the board are different than '-', false otherwise.
     */
    public boolean isFull() {
        // Check every cell
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                // Return as soon as empty cell found
                if (this.board[i][k] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The method checks if all the positions on the board have been played.
     * Static method for checking a char[][] boardState
     *
     * @return true if all the cells in the board are different than '-', false otherwise.
     */
    public static boolean isFull(char[][] boardState) {
        // Check every cell
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                // Return as soon as empty cell found
                if (boardState[i][k] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The method checks if there is a winner.
     *
     * @return true if either a column, a row or a diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    public boolean isWin() {
        // Check if any columns or rows or diagonals have matching cells
        return this.checkColumns() || this.checkRows() || this.checkDiagonals();
    }


    /**
     * The method checks if a player is a winner.
     *
     * @return true if either a column, a row or a diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    public static boolean isWin(char[][] boardState, char player) {
        // Check if any columns or rows or diagonals have matching cells
        return checkColumns(boardState, player) || checkRows(boardState, player) || checkDiagonals(boardState, player);
    }


    /**
     * The method checks if at least one row is occupied by the same player.
     *
     * @return true if any row is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkRows() {
        char checkChar;
        // Check first cell of each row, if the first cell is occupied,
        // check if other two cells in the row also match the first cell

        // Start by rows
        for (int i = 0; i < 3; i++) {
            // Check if first cell is row is empty
            if (this.board[i][0] != '-') {
                // Set checkChar to first cell in row
                checkChar = this.board[i][0];
                // Check if remaining two cells in row match first cell in row
                if (this.board[i][1] == checkChar && this.board[i][2] == checkChar) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The method checks if at least one row is occupied by the same player.
     * Static method for a char[][] boardState
     *
     * @return true if any row is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    public static boolean checkRows(char[][] boardState, char player) {

        // Check first cell of each row, if the first cell is occupied,
        // check if other two cells in the row also match the first cell

        // Start by rows
        for (int i = 0; i < 3; i++) {
            // Check if first cell is row is player
            if (boardState[i][0] == player) {
                // Check if remaining two cells in row match first cell in row
                if (boardState[i][1] == player && boardState[i][2] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The method checks if at least one column is occupied by the same player.
     *
     * @return true if any column is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkColumns() {
        char checkChar;
        // Check first cell of each col, if the first cell is occupied,
        // check if other two cells in the col also match the first cell

        // Start by columns
        for (int i = 0; i < 3; i++) {
            if (this.board[0][i] != '-') {
                // Set checkChar to first cell in col
                checkChar = this.board[0][i];
                // Check if remaining two cells in col match first cell in col
                if (this.board[1][i] == checkChar && this.board[2][i] == checkChar) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The method checks if at least one column is occupied by the same player.
     * Static method for checking a char[][] boardState
     *
     * @return true if any column is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    public static boolean checkColumns(char[][] board, char player) {
        char checkChar;
        // Check first cell of each col, if the first cell is occupied,
        // check if other two cells in the col also match the first cell

        // Start by columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player) {

                // Check if remaining two cells in col match first cell in col
                if (board[1][i] == player && board[2][i] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The method checks if at least one diagonal is occupied by the same player.
     *
     * @return true if any diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkDiagonals() {
        char checkChar;
        // Check two diagonals, from top left to bottom right and top left to bottom left
        // Check if first cell in diagonal is occupied, then check if

        // Check first diagonal starting from upper left corner
        // Check if upper left corner is empty
        if (this.board[0][0] != '-') {
            checkChar = this.board[0][0];
            // Check if next two cells in diagonal match first cell
            if (this.board[1][1] == checkChar && this.board[2][2] == checkChar) {
                return true;
            }
        }

        // Check first diagonal starting from upper right corner
        // Check if upper right corner is empty
        if (this.board[0][2] != '-') {
            checkChar = this.board[0][0];
            // Check if next two cells in diagonal match first cell
            if (this.board[1][1] == checkChar && this.board[2][0] == checkChar) {
                return true;
            }
        }

        // Return false if diagonals not found
        return false;
    }

    /**
     * The method checks if at least one diagonal is occupied by the same player.
     * Static method for checking a char[][] boardState
     *
     * @return true if any diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    public static boolean checkDiagonals(char[][] board, char player) {

        // Check two diagonals, from top left to bottom right and top left to bottom left
        // Check if first cell in diagonal is occupied, then check if

        // Check first diagonal starting from upper left corner
        // Check if upper left corner is empty
        if (board[0][0] == player) {

            // Check if next two cells in diagonal match first cell
            if (board[1][1] == player && board[2][2] == player) {
                return true;
            }
        }

        // Check first diagonal starting from upper right corner
        // Check if upper right corner is empty
        if (board[0][2] == player) {
            // Check if next two cells in diagonal match first cell
            if (board[1][1] == player && board[2][0] == player) {
                return true;
            }
        }
        // Return false if diagonals not found
        return false;
    }

    /**
     * The method changes the currentPlayer. If the currentPlayer is 'x', it changes to 'o'.
     * If the currentPlayer is 'o', it changes to 'x'.
     */
    public void changePlayer() {
        // Switch currentPlayer to other value
        this.currentPlayer = (this.currentPlayer == 'x') ? 'o' : 'x';
    }

    /**
     * The method attempts to set the cell on the position indicated by the row and column given to the currentPlayer value if
     * the cell is unoccupied (equal to '-') in which case returns true. If the position is occupied (not equal to '-') the cell
     * remains unchanged and the method returns false.
     */
    public boolean setRowCol(int row, int col) {
        System.out.println("Row " + row + " Col " + col);

        // Check if row and col within bounds
        if (row < 0 || col < 0 || row > 2 || col > 2) {
            System.out.println("Incorrect cell. Try again!");
            return false;
        }
        // Check if selected cell is empty
        if (this.board[row][col] != '-') {
            System.out.println("Incorrect cell. Try again!");
            return false;
        }
        // Else set cell to currentPlayer
        this.board[row][col] = this.currentPlayer;

        return true;
    }

    /**
     * Clears board object for a new game
     * Clear board to initial state
     * Set current player to 'x'
     */
    public void clear() {
        this.currentPlayer = 'x';
        this.board = new char[3][3];
        // Initialize each cell in board to '-'
        // Loop through rows
        for (int i = 0; i < 3; i++) {
            // Loop through columns
            for (int k = 0; k < 3; k++) {
                this.board[i][k] = '-';
            }
        }
    }

    public char[][] getBoard() {
        return this.board;
    }

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }


}
