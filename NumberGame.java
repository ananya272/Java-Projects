import java.util.Random;
import java.util.Scanner;
public class NumberGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        int score = 0;
        int rounds = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1;
            int attempts = 10;
            rounds++;
            
            System.out.println("Round " + rounds + ":");
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have " + attempts + " attempts to guess the correct number.");
            
            boolean roundWon = false;
            
            while (attempts > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                
                if (guess < randomNumber) {
                    System.out.println("Too low!");
                } else if (guess > randomNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    score++;
                    roundWon = true;
                    break;
                }
                
                attempts--;
                System.out.println("You have " + attempts + " attempts left.");
            }
            
            if (!roundWon) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + randomNumber + ".");
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            sc.nextLine();
            String response = sc.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");
        }
        
        System.out.println("Game over!");
        System.out.println("Rounds played: " + rounds);
        System.out.println("Rounds won: " + score);
        
        sc.close();
    }
}
