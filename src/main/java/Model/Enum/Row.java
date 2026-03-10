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

    private int index;
    private int position;

    Row(int index) {
        this.index = index;
        this.position = index + 1;
    }

    public int getIndex() {
        return index;
    }

    public int getPosition() {
        return position;
    }

    public static Row rowFromIndex(int index){
        for(Row r : values()) if(r.index == index) return r;
        throw new IllegalArgumentException("Indíce no valido.");
    }

}
