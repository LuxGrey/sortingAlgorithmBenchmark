package orderable;

// replicated from Ottman, Widmayer, 2017, page 159
public interface Orderable {

  public boolean equal(Orderable o);

  public boolean greater(Orderable o);

  public boolean greaterEqual(Orderable o);

  public boolean less(Orderable o);

  public boolean lessEqual(Orderable o);

  public Orderable minKey();
}
