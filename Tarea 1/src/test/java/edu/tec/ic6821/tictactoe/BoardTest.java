package edu.tec.ic6821.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private static final int OVERFLOW = 4;
    private static final int UNDERFLOW = -1;

    @Test
    public void applyMoveWithRowUnderflow() {
        // given
        Board board = new Board();

        // when
        MoveStatus status = board.applyMove(UNDERFLOW, 0, Token.X);

        // then
        assertEquals(MoveStatus.INVALID_POSITION, status);
    }

    @Test
    public void applyMoveWithRowOverflow() {
        // given
        Board board = new Board();

        // when
        MoveStatus status = board.applyMove(OVERFLOW, 0, Token.X);

        // then
        assertEquals(MoveStatus.INVALID_POSITION, status);
    }

    @Test
    public void applyMoveWithColumnUnderflow()  {
        // given
        Board board = new Board();

        // when
        MoveStatus status = board.applyMove(0, UNDERFLOW, Token.X);

        // then
        assertEquals(MoveStatus.INVALID_POSITION, status);
    }

    @Test
    public void applyMoveWithColumnOverflow()  {
        // given
        Board board = new Board();

        // when
        MoveStatus status = board.applyMove(0, OVERFLOW, Token.X);

        // then
        assertEquals(MoveStatus.INVALID_POSITION, status);
    }

    @Test
    public void applyMoveWithValidPosition() {
        // given
        Board board = new Board();

        // when
        MoveStatus status = board.applyMove(0, 1, Token.X);

        // then
        assertEquals(MoveStatus.TOKEN_SET, status);
    }

    @Test
    public void applyMoveOnOccupiedBox() {
        // given
        Board board = new Board();
        board.applyMove(0, 0, Token.X);

        // when
        MoveStatus status = board.applyMove(0, 0, Token.X);

        // then
        assertEquals(MoveStatus.BOX_OCCUPIED, status);
    }

    @Test
    public void applyMoveTokenWinsHorizontal() {
        // given
        Board board = new Board();
        board.applyMove(0, 0, Token.X);
        board.applyMove(0, 1, Token.X);

        // when
        MoveStatus status = board.applyMove(0, 2, Token.X);

        // then
        assertEquals(MoveStatus.X_WINS, status);
    }

    @Test
    public void applyMoveTokenWinsVertical() {
        // given
        Board board = new Board();
        board.applyMove(0, 1, Token.O);
        board.applyMove(1, 1, Token.O);

        // when
        MoveStatus status = board.applyMove(2, 1, Token.O);

        // then
        assertEquals(MoveStatus.O_WINS, status);
    }

    @Test
    public void applyMoveTokenWinsOnFirstDiagonal() {
        // given
        Board board = new Board();
        board.applyMove(0, 0, Token.X);
        board.applyMove(1, 1, Token.X);

        // when
        MoveStatus status = board.applyMove(2, 2, Token.X);

        // then
        assertEquals(MoveStatus.X_WINS, status);
    }

    @Test
    public void applyMoveTokenWinsOnSecondDiagonal() {
        // given
        Board board = new Board();
        board.applyMove(0, 2, Token.O);
        board.applyMove(1, 1, Token.O);

        // when
        MoveStatus status = board.applyMove(2, 0, Token.O);

        // then
        assertEquals(MoveStatus.O_WINS, status);
    }

    @Test
    public void applyMoveGameTied() {
        // given
        Board board = new Board();
        board.applyMove(0, 0, Token.X);
        board.applyMove(2, 1, Token.X);
        board.applyMove(2, 2, Token.X);
        board.applyMove(0, 2, Token.X);
        board.applyMove(1, 0, Token.X);
        board.applyMove(0, 1, Token.O);
        board.applyMove(1, 1, Token.O);
        board.applyMove(1, 2, Token.O);

        // when
        MoveStatus status = board.applyMove(2, 0, Token.O);

        // then
        assertEquals(MoveStatus.GAME_TIED, status);
    }

    @Test
    public void emptyBoardToString() {
        // given
        Board board = new Board();
        String emptyBoardText =
              "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n";

        // when
        String boardText = board.toString();

        // then
        assertEquals(emptyBoardText, boardText);
    }

    @Test
    public void randomBoardToString() {
        // given
        Board board = new Board();
        board.applyMove(0, 0, Token.X);
        board.applyMove(1, 1, Token.X);
        board.applyMove(2, 2, Token.X);
        board.applyMove(1, 2, Token.O);
        board.applyMove(2, 1, Token.O);

        String randomBoardText =
              "+---+---+---+\n"
            + "| X |   |   |\n"
            + "+---+---+---+\n"
            + "|   | X | O |\n"
            + "+---+---+---+\n"
            + "|   | O | X |\n"
            + "+---+---+---+\n";

        // when
        String boardText = board.toString();

        // then
        assertEquals(randomBoardText, boardText);
    }

    @Test
    public void fullBoardToString() {
        // given
        Board board = new Board();
        board.applyMove(0, 0, Token.X);
        board.applyMove(1, 1, Token.X);
        board.applyMove(2, 2, Token.X);
        board.applyMove(0, 2, Token.X);
        board.applyMove(1, 0, Token.X);
        board.applyMove(1, 2, Token.O);
        board.applyMove(2, 1, Token.O);
        board.applyMove(0, 1, Token.O);
        board.applyMove(2, 0, Token.O);

        String fullBoardText =
              "+---+---+---+\n"
            + "| X | O | X |\n"
            + "+---+---+---+\n"
            + "| X | X | O |\n"
            + "+---+---+---+\n"
            + "| O | O | X |\n"
            + "+---+---+---+\n";

        // when
        String boardText = board.toString();

        // then
        assertEquals(fullBoardText, boardText);
    }
}
