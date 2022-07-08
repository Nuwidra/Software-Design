package edu.tec.ic6821.tictactoe;

public class Board {

    private final int totalColumns = 3;
    private final int totalRows = 3;
    private MoveStatus fullSetToken;
    private MoveStatus occupiedBoxSection;
    private MoveStatus anInvalidPosition;
    private MoveStatus xSignToken;
    private MoveStatus oSignToken;
    private MoveStatus gameEndedDraw;
    private boolean winnerTheGame = false;
    private final Token[][] catGameBoard;

    public Board() {
        this.catGameBoard = new Token[totalColumns][totalRows];
        this.fullSetToken = MoveStatus.TOKEN_SET;
        this.occupiedBoxSection = MoveStatus.BOX_OCCUPIED;
        this.anInvalidPosition = MoveStatus.INVALID_POSITION;
        this.xSignToken = MoveStatus.O_WINS;
        this.oSignToken = MoveStatus.X_WINS;
        this.gameEndedDraw = MoveStatus.GAME_TIED;
    }

    private int getTotalColumns() {
        return this.totalColumns;
    }

    private int getTotalRows() {
        return this.totalRows;
    }

    private Token[][] getCatGameBoard() {
        return this.catGameBoard;
    }

    private MoveStatus getFullSetToken() {
        return this.fullSetToken;
    }

    private MoveStatus getOccupiedBoxSection() {
        return this.occupiedBoxSection;
    }

    private MoveStatus getAnInvalidPosition() {
        return this.anInvalidPosition;
    }

    private MoveStatus getxSignToken() {
        return this.xSignToken;
    }

    private MoveStatus getoSignToken() {
        return this.oSignToken;
    }

    private MoveStatus getGameEndedDraw() {
        return this.gameEndedDraw;
    }

    private boolean boardPositionValidity(int row, int column) {
        if ((column >= 0 && getTotalColumns() > column) && (getTotalRows() > row && row >= 0)) {
            return true;
        }
        return false;
    }

    private boolean boxAvailableOnBoard(int row, int column, Token token) {
        Token tokeInBoard = getCatGameBoard()[row][column];

        if (!token.equals(tokeInBoard)) {
            return true;
        }
        return false;
    }

    private boolean nullPositionOnBoard(Token[] position) {
        for (Token column : position) {
            if (column == null) {
                return false;
            }
        }
        return true;
    }

    private boolean validateDiagonalsInBoard(Token[][] tokens, Token token) {

        if ((tokens[0][0] != null && tokens[1][1] != null && tokens[2][2] != null)) {
            if ((tokens[0][0].equals(token) && tokens[1][1].equals(token) && tokens[2][2].equals(token))) {
                winnerTheGame = true;
            }
        }
        if ((tokens[0][2] != null && tokens[1][1] != null && tokens[2][0] != null)) {
            if ((tokens[1][1].equals(token) && tokens[0][2].equals(token) && tokens[2][0].equals(token))) {
                winnerTheGame = true;
            }
        }
        return winnerTheGame;
    }

    private boolean validateWinnerOnTheBoard(Token[][] tokens, Token token) {
        if (validateDiagonalsInBoard(tokens, token)) {
            winnerTheGame = true;
        }

        for (int rowCounter = 0; rowCounter < getTotalRows(); rowCounter++) {
            Token[] row = tokens[rowCounter];
            Token[] column = new Token[getTotalColumns()];

            for (int columnCounter = 0; columnCounter < getTotalColumns(); columnCounter++) {
                column[columnCounter] = tokens[columnCounter][rowCounter];
            }

            if (reviewTheColumns(column, token)) {
                break;
            }
            if (reviewTheRows(row, token)) {
                break;
            }
        }
        return winnerTheGame;
    }

    private boolean reviewTheColumns(Token[] column, Token token) {
        if (nullPositionOnBoard(column)) {
            if (column[0].equals(token) && column[1].equals(token) && column[2].equals(token)) {
                winnerTheGame = true;
            }
        }
        return false;
    }

    private boolean reviewTheRows(Token[] row, Token token) {
        if (nullPositionOnBoard(row)) {
            if (row[0].equals(token) && row[1].equals(token) && row[2].equals(token)) {
                winnerTheGame = true;
            }
        }
        return false;
    }
    private boolean tieValidation(Token[][] tokens) {
        for (Token[] row : tokens) {
            for (Token token : row) {
                if (token == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public final MoveStatus applyMove(int row, int column, Token token) {
        Token[][] gameBoard = this.getCatGameBoard();
        String symbol = token.toString();

        if (!boardPositionValidity(row, column)) {
            return getAnInvalidPosition();
        }

        if (boxAvailableOnBoard(row, column, token)) {
            gameBoard[row][column] = token;
            if (validateWinnerOnTheBoard(gameBoard, token)) {
                if (symbol.equals("O")) {
                    return getxSignToken();
                } else {
                    return getoSignToken();
                }
            }
            if (tieValidation(getCatGameBoard())) {
                return getGameEndedDraw();
            } else {
                return getFullSetToken();
            }
        } else {
            return getOccupiedBoxSection();
        }
    }

    @Override
    public final String toString() {
        String gameBoard = "+---+---+---+\n";
        for (Token[] row: catGameBoard) {
            for (Token column: row) {
                if (column != null) {
                    String playerSymbol = column.toString();
                    gameBoard += "| " + playerSymbol + " ";
                } else {
                    gameBoard += "|   ";
                }
            }
            gameBoard += "|\n+---+---+---+\n";
        }
        return gameBoard;
    }

}
