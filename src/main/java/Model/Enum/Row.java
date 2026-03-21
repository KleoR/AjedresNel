package Model.Enum;

public enum Row {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7);

    private final int index;

    Row(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Row rowFromIndex(int index) {
        for (Row r : values()) if (r.index == index) return r;
        throw new IllegalArgumentException("Indíce no valido.");
    }
}
