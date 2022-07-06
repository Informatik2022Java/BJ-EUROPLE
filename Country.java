public class Country extends Vertex
{
    private String name;
    private String id;
    private Vector2 pos; //position of capital
    private LinkedList borders;

    public Country(String name, String id, Vector2 pos, LinkedList borders)
    {
        this.name = name;
        this.id = id;
        this.pos = pos;
        this.borders = borders;
    }

    public Country(String name, String id)
    {
        this.name = name;
        this.id = id;
        this.pos = new Vector2(0,0);
    }

    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

    public Vector2 getPos() {
        return pos;
    }

    public LinkedList getBorders() {
        return borders;
    }
}
