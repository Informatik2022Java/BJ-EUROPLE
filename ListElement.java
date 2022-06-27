public abstract class ListElement
{
  public abstract ListElement add(DataElement data);
  public abstract Object get (int idx);
  public abstract int size();
  public abstract void set(DataElement data, int idx);
  public abstract boolean contains(DataElement data);
  public abstract void print();
  
}
