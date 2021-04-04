package ch.sbb.cca.java.minesweeper.Field;

public class Field {

    private char field;
    private boolean isUncovered;
    private boolean isMarked;
    private boolean isDiscovered;

    public Field() {
        this.field = ' ';
    }

    public char getField() { return field; }

    public void setField(char field) { this.field = field; }

    public boolean isUncovered() { return isUncovered; }

    public void setUncovered(boolean uncovered) { isUncovered = uncovered; }

    public boolean isMarked() { return isMarked; }

    public void setMarked(boolean marked) { isMarked = marked; }

    public boolean isDiscovered() { return isDiscovered; }

    public void setDiscovered(boolean discovered) { isDiscovered = discovered; }
}
