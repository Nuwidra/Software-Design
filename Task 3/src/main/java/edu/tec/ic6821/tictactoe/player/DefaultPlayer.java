package edu.tec.ic6821.tictactoe.player;
import edu.tec.ic6821.tictactoe.board.Token;

public final class DefaultPlayer implements Player {

    private final Token token;
    private final boolean auto;
    private final String label;


    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean isAuto() {
        return this.auto;
    }

    @Override
    public Token getToken() {
        return this.token;
    }

    public DefaultPlayer(final Token token, final boolean auto, final String label) {

        this.token = token;
        this.auto = auto;
        this.label = label;

        if (token == null) {
            final String invalidityMessage  = "No parameter is null ";
            throw new NullPointerException(invalidityMessage);
        }

        if (label == null) {
            final String invalidityMessage = "No parameter is null ";
            throw new NullPointerException(invalidityMessage);
        }
    }
}
