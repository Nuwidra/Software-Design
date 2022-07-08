package edu.tec.ic6821.tictactoe;

public interface Board {
    MoveStatus applyMove(Row row, Column col, Token token);
}
