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
        System.out.println("What is your name Player one?");
        Scanner input = new Scanner(System.in);
        String name1 = input.nextLine().trim();
        player1 = new Player(name1);
        System.out.println("What is your name Player two?");
        Scanner input2 = new Scanner(System.in);
        String name2 = input2.nextLine().trim();
        player2 = new Player(name2);
        
    }
    

    public void play()
    {
        boolean solved = false;
        int currentPlayer = 1;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to PhraseSolver!");
        System.out.println("Current puzzle: " + board.getSolvedPhrase());

        while (!solved)
        {
            board.setLetterValue();
            System.out.println("\nCurrent puzzle: " + board.getSolvedPhrase());
            System.out.println("The letter value is: " + board.getCurrentLetterValue());
            System.out.print((currentPlayer == 1 ? player1.getName() : player2.getName()) + ", guess a letter or the full phrase: ");
            String guess = input.nextLine().trim();

            if (guess.isEmpty()) {
                System.out.println("No input entered. Try again.");
                continue;
            }

            if (guess.length() == 1)
            {
                int occurrences = board.guessLetter(guess);
                if (occurrences > 0)
                {
                    int points = occurrences * board.getCurrentLetterValue();
                    System.out.println("Correct! The letter appears " + occurrences + " time(s). +" + points + " points.");
                    if (currentPlayer == 1)
                        player1.setTotalScore(player1.getTotalScore() + points);
                    else
                        player2.setTotalScore(player2.getTotalScore() + points);
                }
                else
                {
                    int penalty = board.getCurrentLetterValue();
                    System.out.println("Incorrect. -" + penalty + " points.");
                    if (currentPlayer == 1)
                        player1.setTotalScore(player1.getTotalScore() - penalty);
                    else
                        player2.setTotalScore(player2.getTotalScore() - penalty);
                }
            }
            else
            {
                if (board.isSolved(guess))
                {
                    System.out.println("Correct! You've solved the phrase.");
                    int bonus = board.getCurrentLetterValue();
                    if (currentPlayer == 1)
                        player1.setTotalScore(player1.getTotalScore() + bonus);
                    else
                        player2.setTotalScore(player2.getTotalScore() + bonus);
                    solved = true;
                    break;
                }
                else
                {
                    int penalty = board.getCurrentLetterValue();
                    System.out.println("Incorrect guess. -" + penalty + " points.");
                    if (currentPlayer == 1)
                        player1.setTotalScore(player1.getTotalScore() - penalty);
                    else
                        player2.setTotalScore(player2.getTotalScore() - penalty);
                }
            }

            if (!board.getSolvedPhrase().contains("_"))
            {
                System.out.println("Phrase solved!");
                solved = true;
                break;
            }

            if (currentPlayer == 1)
            {
                System.out.println(player1.getName() + "'s score: " + player1.getTotalScore());
                currentPlayer = 2;
                System.out.println(player2.getName() + "'s turn.");
            }
            else
            {
                System.out.println(player2.getName() + "'s score: " + player2.getTotalScore());
                currentPlayer = 1;
                System.out.println(player1.getName() + "'s turn.");
            }
        }

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