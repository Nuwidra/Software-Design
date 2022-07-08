package edu.tec.ic6821.tictactoe.move.auto;
import edu.tec.ic6821.tictactoe.board.BoardState;
import edu.tec.ic6821.tictactoe.player.Player;
import edu.tec.ic6821.tictactoe.board.Column;
import edu.tec.ic6821.tictactoe.board.Row;
import org.apache.commons.math3.util.Pair;

public interface AutoPlayerStrategy {
    Pair<Row, Column> generateMove(Player autoPlayer, BoardState currentBoard);
}
