public class Guess
{
    private String name;
    private int distance;
    private int steps;

    public Guess(String name, int distance, int steps)
    {   
        this.name = name;
        this.distance = distance;
        this.steps = steps;
    }

    public String getName(){
        return name;
    }
    
    public int getDistance(){
        return distance;
    }
    
    public int getSteps(){
        return steps;
    }
}