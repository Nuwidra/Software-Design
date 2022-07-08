package edu.tec.ic6821.tictactoe.player;
import edu.tec.ic6821.tictactoe.board.Token;

public interface Player {
    String getLabel();
    boolean isAuto();
    Token getToken();
}
