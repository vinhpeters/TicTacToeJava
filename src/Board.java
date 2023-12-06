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
        for (int i = 0; i < 3; i++) {
            System.out.println(this.board[i]);
        }

    }

    /**
     * The method checks if all the positions on the board have been played.
     *
     * @return true if all the cells in the board are different than '-', false otherwise.
     */
    public boolean isFull() {
        return true;
    }

    /**
     * The method checks if there is a winner.
     *
     * @return true if either a column, a row or a diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    public boolean isWin() {
        return true;
    }

    /**
     * The method checks if at least one row is occupied by the same player.
     *
     * @return true if any row is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkRows() {
        return true;
    }

    /**
     * The method checks if at least one column is occupied by the same player.
     *
     * @return true if any column is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkColumns() {
        return true;
    }

    /**
     * The method checks if at least one diagonal is occupied by the same player.
     *
     * @return true if any diagonal is filled by the same character and the character is different than '-',
     * false otherwise.
     */
    private boolean checkDiagonals() {
        return true;
    }

    /**
     * The method changes the currentPlayer. If the currentPlayer is 'x', it changes to 'o'.
     * If the currentPlayer is 'o', it changes to 'x'.
     */
    public void changePlayer() {

    }

    /**
     * The method attempts to set the cell on the position indicated by the row and column given to the currentPlayer value if
     * the cell is unoccupied (equal to '-') in which case returns true. If the position is occupied (not equal to '-') the cell
     * remains unchanged and the method returns false.
     */
    public boolean setRowCol(int row, int col) {
        return true;
    }
}
