public class Board {

    private char[][] board;
    private char currentPlayer;

    /**
     * This is the constructor for the class. It initializes the board so all cells in the board are equal to '-'. The currentPlayer
     * is initialized to 'x'.
     */
    public Board() {
        this.currentPlayer = 'x';
        this.board = new char[3][3];

        // Initialize each element in board to '-'
        // Loop through rows
        for (int i = 0; i < 3; i++) {
            // Loop through columns
            for (int k = 0; k < 3; k++) {
                this.board[i][k] = '-';
            }
        }

    }

    /**
     * The method outputs the board in the following format. First the message "Current board" underligned is printed.
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
     * The method checks if at least one row is occupied by the same player.
     *
     * @return true if any row is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkRows() {
        char checkChar;

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
     * The method checks if at least one column is occupied by the same player.
     *
     * @return true if any column is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkColumns() {
        char checkChar;
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
     * The method checks if at least one diagonal is occupied by the same player.
     *
     * @return true if any diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkDiagonals() {

        char checkChar;
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
        // Check if row and col within bounds
        if (row < 1 || col < 1 || row > 3 || col > 3 ) {
            System.out.println("Incorrect cell. Try again!");
            return false;
        }
        // Check if selected cell is empty
        if (this.board[row-1][col-1] != '-') {
            System.out.println("Incorrect cell. Try again!");
            return false;
        }
        // Else set cell to currentPlayer
        this.board[row-1][col-1] = this.currentPlayer;
        return true;
    }
}
