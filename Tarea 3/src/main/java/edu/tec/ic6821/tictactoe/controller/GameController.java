package edu.tec.ic6821.tictactoe.controller;
import edu.tec.ic6821.tictactoe.board.MoveStatus;
import edu.tec.ic6821.tictactoe.player.Player;
import edu.tec.ic6821.tictactoe.board.BoardState;

public interface GameController {
    MoveStatus doAutoMove(Player automaticPlayer);
    MoveStatus doManualMove(Player manualPlayer, String moveSpec);
    BoardState getBoardState();
    Player nextPlayer();
}
