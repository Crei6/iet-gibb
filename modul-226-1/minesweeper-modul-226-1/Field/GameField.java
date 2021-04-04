package ch.sbb.cca.java.minesweeper.Field;

import java.util.Random;

public class GameField {

    private Field[][] fields;
    private final int size;
    private final int bombs;

    private static final Random random = new Random();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public GameField(int size, int bombs) {
        this.fields = new Field[size][size];
        this.size = size;
        this.bombs = bombs;
        generateFields();
    }

    public int getSize() { return size; }

    public Field[][] getFields() { return fields; }

    private void generateFields() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    public void setBombs(int x, int y) {
        int remainingBombs = bombs;
        while (remainingBombs > 0) {
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields.length; j++) {
                    if (random.nextInt(size * size) == 1 && fields[i][j].getField() != 'O' &&
                            i != x && j != y) {
                        fields[i][j].setField('O');
                        remainingBombs--;
                    }
                    if (remainingBombs <= 0) { return; }
                }
            }
        }
    }

    public void printField() {
        printBorder();
        System.out.println(buildFieldString());
        printBorder();
        System.out.println();
    }

    private void printBorder() {
        for (int i = 0; i < size * 3; i++) {
            System.out.print("-");
        }
    }

    private String buildFieldString() {
        StringBuilder field = new StringBuilder();
        field.append("\n");
        for (int i = 0; i < fields.length; i++) {
            field.append(" ");
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j].isMarked()) {
                    field.append(ANSI_RED).append("!").append(ANSI_RESET).append("  ");
                } else if (fields[i][j].isUncovered()) {
                    if (fields[i][j].getField() == 'O') {
                        field.append(ANSI_RED).append(fields[i][j].getField()).append(ANSI_RESET).append("  ");
                    } else { field.append(fields[i][j].getField()).append("  "); }
                } else { field.append(ANSI_GREEN).append("?").append(ANSI_RESET).append("  "); }
            }
            if (i < fields.length - 1) { field.append("\n"); }
        }
        return field.toString();
    }

    public void countBombsAroundFields() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j].getField() != 'O') {
                    countBombsAroundField(i, j);
                }
            }
        }
    }

    private void countBombsAroundField(int i, int j) {
        int bombsAround = 0;
        if (i > 0 && fields[i - 1][j].getField() == 'O') { bombsAround++; }
        if (i > 0 && j < size - 1 && fields[i - 1][j + 1].getField() == 'O') { bombsAround++; }
        if (i > 0 && j > 0 && fields[i - 1][j - 1].getField() == 'O') { bombsAround++; }
        if (i < size - 1 && fields[i + 1][j].getField() == 'O') { bombsAround++; }
        if (i < size - 1 && j < size - 1 && fields[i + 1][j + 1].getField() == 'O') { bombsAround++; }
        if (i < size - 1 && j > 0 && fields[i + 1][j - 1].getField() == 'O') { bombsAround++; }
        if (j > 0 && fields[i][j - 1].getField() == 'O') { bombsAround++; }
        if (j < size - 1 && fields[i][j + 1].getField() == 'O') { bombsAround++; }
        if (bombsAround == 0) { fields[i][j].setField(' '); }
        else { fields[i][j].setField((char)(bombsAround + 48)); }
    }

    public void uncoverField(int i, int j) { fields[i][j].setUncovered(true); }

    public void uncoverEmptyFields(int i, int j) {
        if (fields[i][j].isDiscovered()) { return; }
        else { fields[i][j].setDiscovered(true); }
        if (i > 0 && fields[i - 1][j].getField() != 'O') {
            fields[i - 1][j].setUncovered(true);
            if (fields[i - 1][j].getField() == ' ') { uncoverEmptyFields(i - 1, j); }
        }
        if (i > 0 && j < size - 1 && fields[i - 1][j + 1].getField() != 'O') {
            fields[i - 1][j + 1].setUncovered(true);
            if (fields[i - 1][j + 1].getField() == ' ') { uncoverEmptyFields(i - 1, j + 1); }
        }
        if (i > 0 && j > 0 && fields[i - 1][j - 1].getField() != 'O') {
            fields[i - 1][j - 1].setUncovered(true);
            if (fields[i - 1][j - 1].getField() == ' ') { uncoverEmptyFields(i - 1, j - 1); }
        }
        if (i < size - 1 && fields[i + 1][j].getField() != 'O') {
            fields[i + 1][j].setUncovered(true);
            if (fields[i + 1][j].getField() == ' ') { uncoverEmptyFields(i + 1, j); }
        }
        if (i < size - 1 && j < size - 1 && fields[i + 1][j + 1].getField() != 'O') {
            fields[i + 1][j + 1].setUncovered(true);
            if (fields[i + 1][j + 1].getField() == ' ') { uncoverEmptyFields(i + 1, j + 1); }
        }
        if (i < size - 1 && j > 0 && fields[i + 1][j - 1].getField() != 'O') {
            fields[i + 1][j - 1].setUncovered(true);
            if (fields[i + 1][j - 1].getField() == ' ') { uncoverEmptyFields(i + 1, j - 1); }
        }
        if (j > 0 && fields[i][j - 1].getField() != 'O') {
            fields[i][j - 1].setUncovered(true);
            if (fields[i][j - 1].getField() == ' ') { uncoverEmptyFields(i, j - 1); }
        }
        if (j < size - 1 && fields[i][j + 1].getField() != 'O') {
            fields[i][j + 1].setUncovered(true);
            if (fields[i][j + 1].getField() == ' ') { uncoverEmptyFields(i, j + 1); }
        }
    }

    public void unveilField() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j].setMarked(false);
                fields[i][j].setUncovered(true);
            }
        }
    }
}
