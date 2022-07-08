package edu.tec.ic6821.tictactoe.board;
import org.apache.commons.math3.util.Pair;
import java.util.Optional;

public interface BoardState {
    Optional<Token> getPosition(Pair<Row, Column> move);
    boolean isFull();
    String getText();
}
