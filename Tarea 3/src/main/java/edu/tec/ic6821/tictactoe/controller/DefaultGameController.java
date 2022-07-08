package edu.tec.ic6821.tictactoe.controller;
import java.util.Optional;
import org.apache.commons.math3.util.Pair;
import edu.tec.ic6821.tictactoe.board.Row;
import edu.tec.ic6821.tictactoe.board.Board;
import edu.tec.ic6821.tictactoe.board.Column;
import edu.tec.ic6821.tictactoe.player.Player;
import edu.tec.ic6821.tictactoe.board.MoveStatus;
import edu.tec.ic6821.tictactoe.board.BoardState;
import edu.tec.ic6821.tictactoe.move.ManualMoveSpec;
import edu.tec.ic6821.tictactoe.move.auto.AutoPlayerStrategy;

public final class DefaultGameController implements GameController {

    private final ManualMoveSpec manualMoveSpec;
    private final Board board;
    private final AutoPlayerStrategy autoPlayerStrategy;
    private final Player player1;
    private final Player player2;
    private boolean currentPlayer1;

    public DefaultGameController(final ManualMoveSpec manualMoveSpec,
                                 final Board board,
                                 final AutoPlayerStrategy autoPlayerStrategy,
                                 final Player player1,
                                 final Player player2) {
        this.manualMoveSpec = manualMoveSpec;
        this.board = board;
        this.autoPlayerStrategy = autoPlayerStrategy;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public MoveStatus doAutoMove(Player automaticPlayer) {

        final Pair<Row, Column> move = autoPlayerStrategy
                .generateMove(automaticPlayer,
                        board.getCurrentBoardState());
        return board.applyMove(move, automaticPlayer.getToken());
    }

    @Override
    public MoveStatus doManualMove(Player manualPlayer, String moveSpec) {
        final Optional<Pair<Row, Column>> movementPossible = manualMoveSpec.parse(moveSpec);

        if (movementPossible.isEmpty()) {
            return MoveStatus.INVALID_POSITION;
        } else {
            return board.applyMove(movementPossible.get(), manualPlayer.getToken());
        }
    }

    @Override
    public BoardState getBoardState() {
        return board.getCurrentBoardState();
    }

    @Override
    public Player nextPlayer() {
        currentPlayer1 = !currentPlayer1;
        return currentPlayer1 ? player1 : player2;
    }
}
