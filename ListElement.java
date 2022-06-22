public abstract class ListElement
{
  public abstract ListElement add(Dataelement data);
  public abstract Object get (int idx);
  public abstract int size();
  public abstract void set(Dataelement data, int idx);
  public abstract boolean contains(Dataelement data);
  public abstract void print();
  
}
