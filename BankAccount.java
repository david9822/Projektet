package projekt;

public class BankAccount{
  private Customer customer;
  private int accountNr;
  private double amountOfMoney;
  private static int accountCounter = 0;
/**
* Skapar ett nytt bankkonto åt en innehavare med namn ’holderName’ och
* id ’holderId’. Kontot tilldelas ett unikt kontonummer och innehåller
* inledningsvis 0 kr.
*/
  public BankAccount(String holderName, long holderId){
    this.customer = new Customer(holderName, holderId);
    this.accountNr = accountCounter;
    accountCounter++;
    this.amountOfMoney = 0;
  }
/**
* Skapar ett nytt bankkonto med innehavare ’holder’. Kontot tilldelas
* ett unikt kontonummer och innehåller inledningsvis 0 kr.
*/
  public BankAccount(Customer holder){
    this.customer = holder;
    this.accountNr = accountCounter;
    accountCounter++;
    this.amountOfMoney = 0;
  }
/** Tar reda på kontots innehavare. */
  public Customer getHolder(){
    return customer;
  }
/** Tar reda på det kontonummer som identifierar detta konto. */
  public int getAccountNumber(){
    return accountNr;
  }
/** Tar reda på hur mycket pengar som finns på kontot. */
  public double getAmount(){
    return amountOfMoney;
  }
/** Sätter in beloppet ’amount’ på kontot. */
  public void deposit(double amount){
    if (amount > 0.0){
      amountOfMoney += amount;
    } 
  }
/**
* Tar ut beloppet ’amount’ från kontot. Om kontot saknar täckning
* blir saldot negativt.
*/
  public void withdraw(double amount){
    if (amount > 0.0){
      amountOfMoney -= amount;
    }
  }
/** Returnerar en strängrepresentation av bankkontot. */
  public String toString(){
    return  "konto " +  accountNr + " (" + customer.getName() + ", id " + customer.getIdNr() + ", kundnr " + customer.getCustomerNr() + "): " + amountOfMoney;
  }
}
