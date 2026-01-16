/*
 * Activity 2.2.2
 *
 * The PhraseSolver class for the PhraseSolverGame
 */
import java.util.Scanner;

public class PhraseSolver
{
    private Board board;
    private Player player1;
    private Player player2;

    public PhraseSolver()
    {
        board = new Board();

        Scanner input = new Scanner(System.in);
        System.out.println("What is your name Player one?");
        String name1 = input.nextLine().trim();

        while (name1.isEmpty()) {
            System.out.println("Name cannot be empty. Enter Player one name:");
            name1 = input.nextLine().trim();
        }
        player1 = new Player(name1);

        Scanner input2 = new Scanner(System.in);
        System.out.println("What is your name Player two?");
        String name2 = input2.nextLine().trim();

        while (name2.isEmpty()) {
            System.out.println("Name cannot be empty. Enter Player two name:");
            name2 = input2.nextLine().trim();
        }
        player2 = new Player(name2);
    }

    public void play()
    {
        boolean solved = false;
        int currentPlayer = 1;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to PhraseSolver!");

        while (!solved)
        {
            board.setLetterValue();

            System.out.println("\n----- GAME BOARD -----");
            System.out.println("Current Player: " + (currentPlayer == 1 ? player1.getName() : player2.getName()));
            System.out.println("Letter Value: " + board.getCurrentLetterValue());
            System.out.println(player1.getName() + " Score: " + player1.getTotalScore());
            System.out.println(player2.getName() + " Score: " + player2.getTotalScore());
            System.out.println("Puzzle: " + board.getSolvedPhrase());
            System.out.println("----------------------");

            System.out.print("Guess a letter or the full phrase: ");
            String guess = input.nextLine().trim();

            if (guess.isEmpty()) {
                System.out.println("No input entered. Try again.");
                continue;
            }

            if (guess.length() == 1)
            {
                char c = guess.charAt(0);
                if (!Character.isLetter(c)) {
                    System.out.println("Please enter a valid letter.");
                    continue;
                }

                int occurrences = board.guessLetter(guess);

                if (occurrences > 0)
                {
                    int points = occurrences * board.getCurrentLetterValue();
                    System.out.println("Correct! The letter appears " + occurrences + " time(s). +" + points + " points.");

                    if (currentPlayer == 1)
                        player1.updateScore(points);
                    else
                        player2.updateScore(points);
                }
                else
                {
                    int penalty = board.getCurrentLetterValue();
                    System.out.println("Incorrect. -" + penalty + " points.");

                    if (currentPlayer == 1)
                        player1.updateScore(-penalty);
                    else
                        player2.updateScore(-penalty);
                }
            }
            else
            {
                if (board.isSolved(guess))
                {
                    System.out.println("Correct! You've solved the phrase.");
                    int bonus = board.getCurrentLetterValue();

                    if (currentPlayer == 1)
                        player1.updateScore(bonus);
                    else
                        player2.updateScore(bonus);

                    solved = true;
                    break;
                }
                else
                {
                    int penalty = board.getCurrentLetterValue();
                    System.out.println("Incorrect guess. -" + penalty + " points.");

                    if (currentPlayer == 1)
                        player1.updateScore(-penalty);
                    else
                        player2.updateScore(-penalty);
                }
            }

            // CHECK IF ALL LETTERS ARE REVEALED
            if (!board.getSolvedPhrase().contains("_"))
            {
                System.out.println("Phrase solved!");
                solved = true;
                break;
            }

            // SWITCH PLAYER
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        // FINAL RESULTS
        System.out.println("\nFinal scores:");
        System.out.println(player1.getName() + ": " + player1.getTotalScore());
        System.out.println(player2.getName() + ": " + player2.getTotalScore());

        if (player1.getTotalScore() > player2.getTotalScore())
            System.out.println(player1.getName() + " wins!");
        else if (player2.getTotalScore() > player1.getTotalScore())
            System.out.println(player2.getName() + " wins!");
        else
            System.out.println("It's a tie!");
    }
}
