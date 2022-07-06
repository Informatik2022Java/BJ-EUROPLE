
public class MainLinkedList
{
    private LinkedList list;
    public MainLinkedList()
    {
      list=new LinkedList();
      list.add(0);
      list.add(1);
      list.add(2);
      list.add(3);
      
      System.out.println(list.get(1));
      list.set(1,"hi");
      System.out.println(list.get(1));
    
    }
}
