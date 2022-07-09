//simple testing class
public class MainCsvReader
{
    private CsvReader reader;

    public MainCsvReader()
    {
        reader.readFile("counties.csv", new Vector2(1,2), new Vector2(5,7));
    }
    
    public String[][] readFile(){
        return reader.readFile("counties.csv", new Vector2(0,0), new Vector2(4,8));
    }
}