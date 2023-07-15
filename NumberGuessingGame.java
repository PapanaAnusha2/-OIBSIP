import java.util.Random;

public class NumberGuessingGame {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        int randomNumber = generateRandomNumber();
        int attempts = 0;
        boolean won = false;

        while (!won && attempts < MAX_ATTEMPTS) {
            int guess = getGuessFromUser();

            if (guess == randomNumber) {
                won = true;
            } else if (guess < randomNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }

            attempts++;
        }

        if (won) {
            System.out.println("You won! Congratulations!");
        } else {
            System.out.println("You lost! The number was " + randomNumber);
        }
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }

    private static int getGuessFromUser() {
        System.out.println("Please enter your guess: ");
        int guess = Integer.parseInt(System.console().readLine());
        return guess;
    }
}