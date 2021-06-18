package orderable;

public interface Orderable {

  public boolean equal(Orderable o);
  // gibt aus, ob das ausführende Objekt größer ist als das übergebene Objekt
  public boolean greater(Orderable o);
  // gibt aus, ob das ausführende Objekt größer als oder gleich dem übergebenen Objekt ist
  public boolean greaterEqual(Orderable o);
  // gibt aus, ob das ausführende Objekt kleiner ist als das übergeben Objekt
  public boolean less(Orderable o);
  // gibt aus, ob das ausführende Objekt kleiner als oder gleich dem übergebenen Objekt ist
  public boolean lessEqual(Orderable o);
  // gibt ein Objekt zurück dessen Schlüssel-Attribut den kleinsten gültigen Wert hat
  public Orderable minKey();
}
