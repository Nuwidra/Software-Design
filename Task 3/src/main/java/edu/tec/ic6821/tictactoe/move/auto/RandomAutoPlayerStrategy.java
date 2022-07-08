package edu.tec.ic6821.tictactoe.move.auto;
import edu.tec.ic6821.tictactoe.board.BoardState;
import edu.tec.ic6821.tictactoe.player.Player;
import edu.tec.ic6821.tictactoe.board.Column;
import edu.tec.ic6821.tictactoe.board.Row;
import org.apache.commons.math3.util.Pair;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public final class RandomAutoPlayerStrategy implements AutoPlayerStrategy {

    @Override
    public Pair<Row, Column> generateMove(Player autoPlayer, BoardState boardState) {

        if ((boardState.isFull())) {
            final String illegalMessage = "Illegal Argument Exception";
            throw new IllegalArgumentException(illegalMessage);
        }

        if ((!autoPlayer.isAuto())) {
            final String illegalMessage = "Illegal Argument Exception";
            throw new IllegalArgumentException(illegalMessage);
        }

        final Random randomGenerator = new Random();
        final List<Pair<Row, Column>> vissitedMoves = new ArrayList<>();

        while (true) {

            final int columnIndex = randomGenerator.nextInt(2);
            final int rowIndex = randomGenerator.nextInt(2);

            final Pair<Row, Column> possibleMove = new Pair<>(Row.values()[rowIndex], Column.values()[columnIndex]);
            if (!vissitedMoves.contains(possibleMove)) {

                if (boardState.getPosition(possibleMove).isEmpty()) {
                    return possibleMove;
                } else {
                    vissitedMoves.add(possibleMove);
                }
            }
        }
    }

}
