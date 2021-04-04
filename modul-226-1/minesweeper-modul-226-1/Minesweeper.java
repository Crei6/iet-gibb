package ch.sbb.cca.java.minesweeper;

import ch.sbb.cca.java.minesweeper.Field.GameField;
import ch.sbb.cca.java.minesweeper.UserInterface.UserInterface;

public class Minesweeper {

    private final UserInterface userInterface = new UserInterface();
    private GameField gameField;
    private boolean isFirstRound;

    public void startGame() {
        userInterface.introduceUser();
        createFieldByDifficulty();
        play();
    }

    private void createFieldByDifficulty() {
        switch (userInterface.getDifficulty()) {
            case 1 -> gameField = new GameField(8, 10);
            case 2 -> gameField = new GameField(16, 40);
            case 3 -> gameField = new GameField(30, 150);
        }
        gameField.printField();
    }

    private void play() {
        isFirstRound = true;
        int x;
        int y;
        while (!isGameFinished()) {
            String[] userInput = userInterface.getUserInput(gameField.getSize());
            x = Integer.parseInt(userInput[1]);
            y = Integer.parseInt(userInput[2]);
            if (isFirstRound) { generateBombs(x, y); }
            switch (userInput[0]) {
                case "M" -> markField(x, y);
                case "U" -> uncoverField(x, y);
            }
            gameField.printField();
            if (userInput[0].equals("U") && checkIfBombGotUncovered(x, y)) { break; }
        }
        if (isGameFinished()) {
            userInterface.tellUserThatHeHasWon();
        } else {
            gameField.unveilField();
            gameField.printField();
        }
    }

    private boolean checkIfBombGotUncovered(int x, int y) {
        if (gameField.getFields()[x][y].getField() == 'O') {
            userInterface.tellUserThatHeHasLost();
            return true;
        } else { return false; }
    }

    private void uncoverField(int x, int y) {
        gameField.getFields()[x][y].setMarked(false);
        gameField.uncoverField(x, y);
        if (gameField.getFields()[x][y].getField() == ' ') { gameField.uncoverEmptyFields(x, y); }
    }

    private void markField(int x, int y) {
        gameField.getFields()[x][y].setMarked(!gameField.getFields()[x][y].isMarked());
    }

    private void generateBombs(int x, int y) {
        gameField.setBombs(x, y);
        gameField.countBombsAroundFields();
        isFirstRound = false;
    }

    private boolean isGameFinished() {
        for (int i = 0; i < gameField.getFields().length; i++) {
            for (int j = 0; j < gameField.getFields().length; j++) {
                if (!gameField.getFields()[i][j].isUncovered() && gameField.getFields()[i][j].getField() != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}
