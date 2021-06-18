package orderable;

import java.util.Date;

public class OrderableProfile implements Orderable {
  public int key; // key that defines position within sorted order
  // dummy-data so that orderable.OrderableProfile carries more information than just the sorting-key
  // and is thus closer to a real domain class
  public String firstname;
  public String lastname;
  public String address;
  public Date created;

  public OrderableProfile(int key, String firstname, String lastname, String address, Date created) {
    if (key < 1) {
      throw new IllegalArgumentException("key muss positiver int sein");
    }
    this.key = key;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.created = created;
  }

  public boolean equal(Orderable o) {
    return this.key == ((OrderableProfile) o).key;
  }

  public boolean greater(Orderable o) {
    return this.key > ((OrderableProfile) o).key;
  }

  public boolean greaterEqual(Orderable o) {
    return this.key >= ((OrderableProfile) o).key;
  }

  public boolean less(Orderable o) {
    return this.key < ((OrderableProfile) o).key;
  }

  public boolean lessEqual(Orderable o) {
    return this.key <= ((OrderableProfile) o).key;
  }

  public Orderable minKey() {
    this.key = 1;
    return this;
  }

  // Gibt die Ziffer für eine bestimmte Stelle in key zurück.
  // Die gewünschte Stelle wird mit position spezifiziert, wobei die Einerstelle position = 0 entspricht
  public int digit(int position) {
    return (key / (int) Math.pow(10, position)) % 10;
  }

  public String toString() {
    return "key: " + key + "\nfirstname: " + firstname + "\nlastname: " + lastname
        + "\naddress: " + address + "\ncreated: " + created.toString();
  }
}
