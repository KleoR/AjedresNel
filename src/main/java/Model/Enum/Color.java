package Model.Enum;

public enum Color {
    White,
    Black;

    public static Color colorFromIndexes(Column c, Row r) {
        if (c.getIndex() % 2 == 0) {
            if (r.getIndex() % 2 == 0) return Black;
            else return White;
        } else {
            if (r.getIndex() % 2 == 0) return White;
            else return Black;
        }
    }
}
