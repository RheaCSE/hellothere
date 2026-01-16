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

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void updateScore(int amount)
    {
        this.totalScore += amount;
    }
}
