
public class MainCountries
{
    private Countries countries;
    
    public MainCountries()
    {
        String[][] data = CsvReader.readFile("counties.csv", new Vector2(0,0), new Vector2(4,8));
        countries = new Countries(Countries.getCountriesFromData(data), Countries.getEdgesFromData(data));
        
        countries.printMatrix();
        
        System.out.println();
        System.out.println();
        
        System.out.println(countries.dist2coords(new Vector2(52.3f, 21), new Vector2(52.6f, 13.4f)));;
    }
}
