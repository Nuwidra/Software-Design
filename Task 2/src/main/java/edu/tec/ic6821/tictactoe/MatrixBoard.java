package edu.tec.ic6821.tictactoe;

public final class MatrixBoard implements Board {

    private static final int BOARD_NUMBER_OF_ROWS = 3;
    private static final int BOARD_NUMBER_OF_COLS = 3;

    private final Token[][] board =
        new Token[BOARD_NUMBER_OF_ROWS][BOARD_NUMBER_OF_COLS];

    private int freeCells = BOARD_NUMBER_OF_ROWS * BOARD_NUMBER_OF_COLS;

    private int indexEachRow(Row row) {
        if (row.equals(row.MIDDLE)) {
            return 1;
        } else if (row.equals(row.BOTTOM)) {
            return 2;
        } else {
            return 0;
        }
    }

    private int indexEachColumn(Column col) {
        if (col.equals(col.CENTER)) {
            return 1;
        } else if (col.equals(col.RIGHT)) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public MoveStatus applyMove(Row row, Column col, Token token) {
        if (this.board[indexEachRow(row)][indexEachColumn(col)] != null) {
            return MoveStatus.BOX_OCCUPIED;
        } else {
            this.board[indexEachRow(row)][indexEachColumn(col)] = token;
            this.freeCells--;
        }
        return verifyGameState(indexEachRow(row), indexEachColumn(col), token);
    }

    private MoveStatus verifyGameState(int row, int col, Token token) {
        MoveStatus winStatus = token == Token.O ? MoveStatus.O_WINS : MoveStatus.X_WINS;

        if (verifyRow(row, token)
            || verifyCol(col, token)
            || verifyDiagonal(row, col, token)) {
            return winStatus;
        }

        if (this.freeCells == 0) {
            return MoveStatus.GAME_TIED;
        }

        return MoveStatus.TOKEN_SET;
    }

    private boolean verifyDiagonal(int row, int col, Token token) {
        if (row == 1 ^ col == 1) {
            return false;
        }

        return (this.board[0][0] == token
            && this.board[1][1] == token
            && this.board[2][2] == token)
            || (this.board[0][2] == token
            && this.board[1][1] == token
            && this.board[2][0] == token);
    }

    private boolean verifyCol(int col, Token token) {
        return this.board[0][col] == token
            && this.board[1][col] == token
            && this.board[2][col] == token;
    }

    private boolean verifyRow(int row, Token token) {
        return this.board[row][0] == token
            && this.board[row][1] == token
            && this.board[row][2] == token;
    }

    private boolean validateMove(int row, int col) {
        return 0 <= row && row <= 2 && 0 <= col && col <= 2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("+---+---+---+\n");
        for (Token[] row : this.board) {
            for (Token token : row) {
                builder.append("| ")
                    .append(token != null ? token.toString() : " ")
                    .append(" ");
            }
            builder.append("|\n")
                .append("+---+---+---+\n");
        }

        return builder.toString();
    }
}
