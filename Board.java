/*
 * Activity 2.2.2
 *
 * A Board class for the PhraseSolverGame
 */
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Board
{
    private String solvedPhrase;
    private String phrase;
    private int currentLetterValue;

    public Board()
    {
        phrase = loadPhrase();
        currentLetterValue = 0;
        buildSolvedPhrase();
    }

    public String getSolvedPhrase()
    {
        return solvedPhrase;
    }

    public int getCurrentLetterValue()
    {
        return currentLetterValue;
    }

    public void setSolvedPhrase(String newSolvedPhrase)
    {
        solvedPhrase = newSolvedPhrase;
    }

    public void setCurrentLetterValue(int newValue)
    {
        currentLetterValue = newValue;
    }

    private void buildSolvedPhrase() {
        solvedPhrase = "";
        if (phrase == null) phrase = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                solvedPhrase += "  ";
            } else {
                solvedPhrase += "_ ";
            }
        }
    }

    /* ---------- provided code, do not modify ---------- */
    public void setLetterValue()
    {
        int randomInt = (int) ((Math.random() * 10) + 1) * 100;
        currentLetterValue = randomInt;
    }

    public boolean isSolved(String guess)
    {
        if (guess == null) return false;
        return phrase.equals(guess.trim().toUpperCase());
    }

    private String loadPhrase()
    {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("phrases.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) lines.add(line.toUpperCase());
            }
            sc.close();
        } catch(Exception e) {
           
        }

        if (lines.size() == 0) {
            return "HELLO WORLD";
        }

        int randomIndex = (int) (Math.random() * lines.size());
        return lines.get(randomIndex);
    }

    /**
     
     * @param guess
     * @return 
     */
    public int guessLetter(String guess)
    {
        if (guess == null || guess.length() == 0) return 0;
        String g = guess.trim().toUpperCase();
        if (g.length() != 1) return 0;

        char letter = g.charAt(0);
        int foundCount = 0;
        StringBuilder newSolved = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++)
        {
            char pch = phrase.charAt(i);
            if (pch == letter)
            {
                newSolved.append(letter).append(' ');
                foundCount++;
            }
            else
            {
                // take current solved character (either underscore or letter) which is at index i*2
                if (i * 2 < solvedPhrase.length())
                    newSolved.append(solvedPhrase.charAt(i * 2)).append(' ');
                else
                    newSolved.append("_ ");
            }
        }

        solvedPhrase = newSolved.toString();
        return foundCount;
    }
}
