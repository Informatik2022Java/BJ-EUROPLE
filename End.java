public class End extends ListElement
{
    public ListElement add(DataElement data){
        return new Node(this, data);
    }
    
    public Object get(int idx){
        System.out.println("out of bounds");
        return null;
    }
    
    public int size(){
        return 0;
    }
    
    public void set(DataElement data, int idx){
        System.out.println("out of bounds");
        return;
    }
    
    public boolean contains(DataElement data){
        return false;
    }
    
    public void print(){
        System.out.println();
    }
}