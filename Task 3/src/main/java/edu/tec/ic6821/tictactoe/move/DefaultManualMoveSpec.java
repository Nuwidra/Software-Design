package edu.tec.ic6821.tictactoe.move;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;
import edu.tec.ic6821.tictactoe.board.Row;
import org.apache.commons.math3.util.Pair;
import edu.tec.ic6821.tictactoe.board.Column;

public final class DefaultManualMoveSpec implements ManualMoveSpec {

    private static final int MOVEMENT_LIST = 2;

    private static final Map<String, Row> DEFINITION_ROWS = new HashMap<>() {{
        put("superior", Row.TOP);
        put("medio", Row.MIDDLE);
        put("inferior", Row.BOTTOM);
    }};

    private static final Map<String, Column> DEFINITION_COLUMNS = new HashMap<>() {{
        put("izquierda", Column.LEFT);
        put("centro", Column.CENTER);
        put("derecha", Column.RIGHT);
    }};

    private static final List<String> POSITION_ROW = new ArrayList<>() {{
    add("superior");
    add("medio");
    add("inferior");
    }};

    private static final List<String> POSITION_COLUMN = new ArrayList<>() {{
        add("izquierda");
        add("centro");
        add("derecha");
    }};

    @Override
    public Optional<Pair<Row, Column>> parse(String move) {

        if (move != null) {

            final List<String> motionChains = List.of(move.split(" "));

            if (motionChains.size() == MOVEMENT_LIST) {
                final String row = motionChains.get(0);
                final String column = motionChains.get(1);

                if (POSITION_ROW.contains(row)) {

                    if (POSITION_COLUMN.contains(column)) {

                        return Optional.of(new Pair<>(DEFINITION_ROWS.get(row),
                                DEFINITION_COLUMNS.get(column)));
                    }
                    return Optional.of(new Pair<>(DEFINITION_ROWS.get(row),
                            DEFINITION_COLUMNS.get(column)));
                }
            }
        }
        return Optional.empty();
    }
}
