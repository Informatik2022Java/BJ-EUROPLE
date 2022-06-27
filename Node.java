public class Node extends ListElement
{
    private ListElement next;
    private DataElement data;
    
    public Node(ListElement next, DataElement data)
    {
        this.next = next;
        this.data = data;
    }
    
    public ListElement add(DataElement data){
        next = next.add(data);
        return this;
    }
    
    public Object get(int idx){
        if(idx <= 0){
            return data.getData();
        }
        return next.get(idx - 1);
    }
    
    public int size(){
        return next.size() + 1;
    }
    
    public void set(DataElement data, int idx){
        if(idx <= 0){
            this.data = data;
            return;
        }
        next.set(data, idx - 1);
    }
    
    public boolean contains(DataElement data){
        //System.out.println(this.data.getData() + " " + data.getData() + " " + (data.getData() instanceof String));
        if(this.data.getData() == data.getData() || (data.getData() instanceof String && this.data.getData().toString().equals(data.getData()))){
            return true;
        }
        return next.contains(data);
    }
    
    public void print(){
        System.out.print(data.getData());
        next.print();
    }
}