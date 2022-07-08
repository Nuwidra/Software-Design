package edu.tec.ic6821.tictactoe;
import org.apache.commons.math3.util.Pair;
import java.util.HashMap;
import java.util.Map;

public final class CountingBoard implements Board {

    private static final int COUNTER_FOR_WINNER_X = 3;
    private static final int COUNTER_FOR_WINNER_O = -3;
    private static final int TOTAL_NUMBER_OF_CELLS = 9;

    private int counterPossibleDiagonal = 0;
    private int antiCounterPossibleDiagonal = 0;

    private final Map<Pair<Row, Column>, Token> localizedTokens =  new HashMap<>();

    private final Map<Row, Integer> counterEachRow = new HashMap<>() {{
        put(Row.TOP, 0);
        put(Row.MIDDLE, 0);
        put(Row.BOTTOM, 0);
    }};

    private final Map<Column, Integer> counterEachColumn = new HashMap<>() {{
        put(Column.LEFT, 0);
        put(Column.CENTER, 0);
        put(Column.RIGHT, 0);
    }};

    @Override
    public MoveStatus applyMove(Row row, Column col, Token token) {

        final Pair<Row, Column> possibleTokensBox = new Pair<>(row, col);

        if ((localizedTokens.getOrDefault(possibleTokensBox, Token.O).equals(Token.X)
            && token.equals(Token.X))) {
            return MoveStatus.BOX_OCCUPIED;

        } else if (((localizedTokens.getOrDefault(possibleTokensBox, Token.X).equals(Token.O)
            && token.equals(Token.O)))) {
            return MoveStatus.BOX_OCCUPIED;

        } else {
            this.localizedTokens.put(possibleTokensBox, token);
            countersModification(row, col, token);
        }

        return gameChecker(row, col);
    }

    private MoveStatus gameChecker(Row row, Column column) {

        if ((this.counterEachRow.get(row) == COUNTER_FOR_WINNER_X)) {
            return MoveStatus.X_WINS;

        } else if ((this.counterEachColumn.get(column) == COUNTER_FOR_WINNER_X)) {
            return MoveStatus.X_WINS;

        } else if ((this.counterPossibleDiagonal == COUNTER_FOR_WINNER_X)) {
            return MoveStatus.X_WINS;

        } else if ((this.antiCounterPossibleDiagonal == COUNTER_FOR_WINNER_X)) {
            return MoveStatus.X_WINS;

        } else if ((this.counterEachRow.get(row) == COUNTER_FOR_WINNER_O)) {
            return MoveStatus.O_WINS;

        } else if ((this.counterEachColumn.get(column) == COUNTER_FOR_WINNER_O)) {
            return MoveStatus.O_WINS;

        } else if ((this.counterPossibleDiagonal == COUNTER_FOR_WINNER_O)) {
            return MoveStatus.O_WINS;

        } else if ((this.antiCounterPossibleDiagonal == COUNTER_FOR_WINNER_O)) {
            return MoveStatus.O_WINS;

        } else if (localizedTokens.size() == TOTAL_NUMBER_OF_CELLS) {
            return MoveStatus.GAME_TIED;

        } else {
            return MoveStatus.TOKEN_SET;
        }
    }

    private void countersModification(Row row, Column col, Token token) {
        final int modifierToken = token == Token.X ? 1 : -1;
        this.counterEachRow.compute(row, (key, counterValueRow) -> counterValueRow + modifierToken);
        this.counterEachColumn.compute(col, (key, counterValueColumn) -> counterValueColumn + modifierToken);

        if (row.equals(Row.MIDDLE) && col.equals(Column.CENTER)) {
            this.counterPossibleDiagonal = this.counterPossibleDiagonal + modifierToken;
            if (row.equals(Row.MIDDLE) && col.equals(Column.CENTER)) {
                this.antiCounterPossibleDiagonal = this.antiCounterPossibleDiagonal + modifierToken;
            }

        } else if ((row.equals(Row.TOP) && col.equals(Column.LEFT))) {
            this.antiCounterPossibleDiagonal = this.antiCounterPossibleDiagonal + modifierToken;
            if ((row.equals(Row.BOTTOM) && col.equals(Column.RIGHT))) {
                this.antiCounterPossibleDiagonal = this.antiCounterPossibleDiagonal + modifierToken;
            } else {
                this.antiCounterPossibleDiagonal = this.antiCounterPossibleDiagonal + modifierToken;
            }

        } else if (row.equals(Row.BOTTOM) && col.equals(Column.LEFT)
            || row.equals(Row.TOP) && col.equals(Column.RIGHT)) {
            this.counterPossibleDiagonal = this.counterPossibleDiagonal + modifierToken;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("+---+---+---+\n");
        for (Row row : Row.values()) {
            for (Column col : Column.values()) {
                Pair<Row, Column> boxStorage = new Pair<>(row, col);
                Token currentPlacePrinting = this.localizedTokens.get(boxStorage);
                builder.append("| ")
                    .append(currentPlacePrinting != null ? currentPlacePrinting.toString() : " ")
                    .append(" ");
            }
            builder.append("|\n")
                .append("+---+---+---+\n");
        }
        return builder.toString();
    }
}
