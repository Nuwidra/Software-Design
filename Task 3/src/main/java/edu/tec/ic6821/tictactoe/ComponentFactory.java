package edu.tec.ic6821.tictactoe;
import edu.tec.ic6821.tictactoe.board.Board;
import edu.tec.ic6821.tictactoe.board.Token;
import edu.tec.ic6821.tictactoe.player.Player;
import edu.tec.ic6821.tictactoe.board.CountingBoard;
import edu.tec.ic6821.tictactoe.move.ManualMoveSpec;
import edu.tec.ic6821.tictactoe.player.DefaultPlayer;
import edu.tec.ic6821.tictactoe.controller.GameController;
import edu.tec.ic6821.tictactoe.move.DefaultManualMoveSpec;
import edu.tec.ic6821.tictactoe.move.auto.AutoPlayerStrategy;
import edu.tec.ic6821.tictactoe.controller.DefaultGameController;
import edu.tec.ic6821.tictactoe.move.auto.RandomAutoPlayerStrategy;

public final class ComponentFactory {

    public static GameController gameController(int level, boolean auto1, boolean auto2) {
        if (level == 1) {
            return new DefaultGameController(manualMoveSpec(),
                    board(),
                    autoPlayerStrategy(level),
                    player(1, auto1), player(2, auto2));
        } else {
            final String factoryVerifiedIllegalLevel = "An illegal level was verified in the factory";
            throw new IllegalArgumentException(factoryVerifiedIllegalLevel);
        }
    }

    private static ManualMoveSpec manualMoveSpec() {
        return new DefaultManualMoveSpec();
    }

    private static Board board() {
        return new CountingBoard();
    }

    private static AutoPlayerStrategy autoPlayerStrategy(int level) {
        return new RandomAutoPlayerStrategy();
    }

    private static Player player(int order, boolean isAutoPlayer) {
        final Token token = order == Token.X.ordinal() ? Token.X : Token.O;
        final String playerLabel = token.equals(Token.X) ? token.toString() : Token.O.toString();
        return new DefaultPlayer(token, isAutoPlayer, playerLabel);
    }

    private ComponentFactory() {
    }

}
