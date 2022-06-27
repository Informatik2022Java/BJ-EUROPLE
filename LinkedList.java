public class LinkedList
{
 private ListElement first;
 
 public LinkedList()
 {
     first=new End();
 }
 public LinkedList(Object[] arr)
 {
     first=new End();
     for(int i=0;i<arr.length;i++)
     {
         add(arr[i]);
     }
 }
 public void add(Object data)
 {
     first=first.add(new DataElement(data));
 }
 public Object get(int idx)
 {
     return first.get(idx);
 }
 public int size()
 {
     return first.size();
 }
 public void set(int idx,Object data)
 {
     first.set(new DataElement(data),idx);
 }
 public boolean contains(Object data)
 {
     return first.contains(new DataElement(data));
 }
 public void print (){
     first.print();
 }
 
}
