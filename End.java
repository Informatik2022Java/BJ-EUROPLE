public class End extends ListElement
{
    public ListElement add(Dataelement data)
    {
        return new Node(this, data);
    }
    public Object get(int idx)
    {  
        System.out.println("out of bounds");
    }
    public int size()
    {
        return 0;
    }
    public void set(Dataelement data,int idx )
    {
        System.out.println("out of bounds");
    }
    public boolean contains(Dataelement data)
    {
        return false;
    }
    public void print(){
        System.out.println();
    }
}
