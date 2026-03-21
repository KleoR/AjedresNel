package Model.Enum;

public enum Color {
    WHITE,
    BLACK;

    public static Color colorFromIndexes(Column c, Row r) {
        if (c.getIndex() % 2 == 0) {
            if (r.getIndex() % 2 == 0) return BLACK;
            else return WHITE;
        } else {
            if (r.getIndex() % 2 == 0) return WHITE;
            else return BLACK;
        }
    }
}
