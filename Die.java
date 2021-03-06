import java.util.*;

public class Die
{
    private int sides;
    private Random rng = new Random();
    
    public Die(int sides)
    {
        this.sides = sides;
    }
    
    public int roll()
    {
        return (int)(rng.nextDouble()*sides) + 1;
    }
    
    public int getSides()
    {
        return sides;
    }
    
}
