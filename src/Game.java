import java.util.Random;
import java.util.Scanner;
public class Game {


	public static void main(String[] args) {
		
	 String[] cities = {"Ankara", "Istanbul", "Izmir", "Usak", "Kayseri", "Antalya", "Eskisehir", "Samsun", "Konya"};

	    
	        Random random = new Random();
	        Scanner scanner = new Scanner(System.in);
	        
	        String selectedCity = cities[random.nextInt(cities.length)];
	        char[] revealedLetters = new char[selectedCity.length()];
	        for (int i = 0; i < revealedLetters.length; i++) {
	            revealedLetters[i] = '_';
	        }
	        
	        int correctGuessCount = 0;
	        int attemptsLeft = 10;
	        long startTime = System.currentTimeMillis();
	        
	        while (correctGuessCount < revealedLetters.length && attemptsLeft > 0) {
	            System.out.print("Guess the word: ");
	            for (char c : revealedLetters) {
	                System.out.print(c + " ");
	            }
	            System.out.println("\nAttempts left: " + attemptsLeft);
	            
	            String guess = scanner.next().toLowerCase();
	            
	            if (guess.length() != 1) {
	                System.out.println("Please enter only one character!");
	                continue;
	            }
	            
	            char guessedLetter = guess.charAt(0);
	            try {
	                if (!Character.isLetter(guessedLetter)) {
	                    throw new IllegalArgumentException("Please enter a letter!");
	                }
	            } catch (IllegalArgumentException e) {
	                System.out.println(e.getMessage());
	                continue;
	            }
	            
	            boolean correctGuess = false;
	            guessedLetter = Character.toLowerCase(guessedLetter);
	            
	            for (int i = 0; i < selectedCity.length(); i++) {
	                if (selectedCity.charAt(i) == guessedLetter && revealedLetters[i] == '_') {
	                    revealedLetters[i] = guessedLetter;
	                    correctGuess = true;
	                    correctGuessCount++;
	                }
	            }
	            
	            if (!correctGuess) {
	                attemptsLeft--;
	                System.out.println("Wrong guess! Attempts left: " + attemptsLeft);
	            }
	        }
	        
	        long endTime = System.currentTimeMillis();
	        long gameTime = (endTime - startTime) / 1000;
	        
	        if (correctGuessCount == revealedLetters.length) {
	            System.out.println("Congratulations, you guessed the word! Word: " + selectedCity);
	        } else {
	            System.out.println("Game over. Correct answer: " + selectedCity);
	        }
	        
	        int score = calculateScore(gameTime);
	        System.out.println("Game Time: " + gameTime + " seconds. Your Total Score: " + score);
	    }
	    
	    public static int calculateScore(long time) {
	        if (time <= 20) {
	            return 100;
	        } else if (time <= 30) {
	            return 90;
	        } else {
	            return 80;
	        }
	    }

	}


