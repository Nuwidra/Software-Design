package edu.tec.ic6821.tictactoe;

public final class TicTacToe {

    private TicTacToe() { }

    public static void main(String[] args) {
        Board board = new Board();
        board.applyMove(0, 2, Token.X);
        board.applyMove(1, 0, Token.O);
        board.applyMove(2, 2, Token.X);
        board.applyMove(1, 1, Token.O);
        board.applyMove(1, 2, Token.X);

        System.out.println(board);
    }
}
