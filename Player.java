/*
 * Simple Player class for PhraseSolverGame
 */
public class Player
{
    private String name;
    private int totalScore;

    public Player(String name)
    {
        this.name = name;
        this.totalScore = 0;
    }

    public String getName()
    {
        return name;
    }

    public int getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(int totalScore)
    {
        this.totalScore = totalScore;
    }
}