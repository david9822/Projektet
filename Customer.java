package projekt;

public class Customer{
  private String name;
  private long idNr;
  private int customerNr;
  private static int customerCounter = 0;
/**
* Skapar en kund (kontoinnehavare) med namnet ’name’ och id-nummer ’idNr’.
* Kunden tilldelas också ett unikt kundnummer.
*/
  public Customer(String name, long idNr){
    this.name = name;
    this.idNr = idNr;
    this.customerNr = customerCounter;
    customerCounter++;
  }
/** Tar reda på kundens namn. */
  public String getName(){
    return name;
  }
/** Tar reda på kundens personnummer. */
  public long getIdNr(){
    return idNr;
  }
/** Tar reda på kundens kundnummer. */
  public int getCustomerNr(){
    return customerNr;
  }
/** Returnerar en strängbeskrivning av kunden. */
  public String toString(){
    return name + ", id " + idNr + ", kundnr " + customerNr;
  }
}
