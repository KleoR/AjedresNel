package Model.Enum;

public enum Row {
    uno(0),
    dos(1),
    tres(2),
    cuatro(3),
    cinco(4),
    seis(5),
    siete(6),
    ocho(7);

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
