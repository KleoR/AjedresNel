package Model.Enum;

public enum Column {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private int index;

    Column(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Column columnFromIndex(int index){
        for (Column c : values()) if (c.index == index) return c;
        throw new IllegalArgumentException("Indíce no valido.");
    }
}
