
public class Country extends Vertex
{
    private String name;
    private String id;

    public Country(String name, String id)
    {
        this.name = name;
        this.id = id;
    }

    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
}
