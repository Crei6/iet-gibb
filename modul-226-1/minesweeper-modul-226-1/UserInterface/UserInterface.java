package ch.sbb.cca.java.minesweeper.UserInterface;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    public void introduceUser() {
        System.out.println("Welcome to Minesweeper! \n" +
                "The aim is to uncover the whole field without touching any bomb!");
    }

    public int getDifficulty() {
        int difficulty = 0;
        System.out.println("Please select a difficulty! \n--------------------------- \n" +
                " 1 - Easy \n 2 - Medium \n 3 - Hard \n---------------------------");
        do {
            try {
                difficulty = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ignored) { }
        } while (difficulty < 1 || difficulty > 3);
        return difficulty;
    }

    public String[] getUserInput(int size) {
        String[] userInputArray;
        String userInput;
        System.out.println("Please enter which field you wanna mark or uncover\n" +
                "Example: (M 2 3) 'M' for marking / 'U' for uncover :");
        do {
            userInput = scanner.nextLine();
            userInputArray = userInput.split(" ");
        } while (!isUserInputValid(userInputArray, size));
        return userInputArray;
    }

    private boolean isUserInputValid(String[] input, int size) {
        if (input.length != 3) { return false; }
        if (!input[0].equals("U") && !input[0].equals("M")) { return false; }
        try {
            Integer.valueOf(input[1]);
            Integer.valueOf(input[2]);
        } catch (NumberFormatException e) { return false; }
        if (Integer.parseInt(input[1]) < 0 || Integer.parseInt(input[1]) >= size) { return false; }
        return Integer.parseInt(input[2]) >= 0 && Integer.parseInt(input[2]) < size;
    }

    public void tellUserThatHeHasLost() {
        System.out.println("Oh no you found a bomb \n--- GAME OVER! ---");
    }

    public void tellUserThatHeHasWon() {
        System.out.println("Congratulations \n--- YOU HAVE WON THE GAME! ---");
    }

}