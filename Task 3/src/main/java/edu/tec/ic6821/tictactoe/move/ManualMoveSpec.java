package edu.tec.ic6821.tictactoe.move;
import java.util.Optional;
import edu.tec.ic6821.tictactoe.board.Row;
import org.apache.commons.math3.util.Pair;
import edu.tec.ic6821.tictactoe.board.Column;

public interface ManualMoveSpec {
    Optional<Pair<Row, Column>> parse(String move);
}
