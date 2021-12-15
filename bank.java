package projekt;

import java.util.ArrayList;

public class Bank {
  /** Skapar en ny bank utan konton. */
  private ArrayList<BankAccount> bank;

  public Bank() {
    this.bank = new ArrayList<BankAccount>();
  }

  /**
   * Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare med de
   * givna uppgifterna ska inte en ny Customer skapas, utan istället den
   * befintliga användas. Det nya kontonumret returneras.
   */
  public int addAccount(String holderName, long idNr) {
    BankAccount temp;
    for (BankAccount b : bank) {
      if (b.getHolder().getIdNr() == idNr) {
        temp = new BankAccount(b.getHolder());
        bank.add(temp);
        return temp.getAccountNumber();
      }
    }
    temp = new BankAccount(holderName, idNr);
    bank.add(temp);
    return temp.getAccountNumber();
  }

  /**
   * Returnerar den kontoinnehavaren som har det givna id-numret, eller null om
   * ingen sådan finns.
   */
  public Customer findHolder(long idNr) {
    for (BankAccount b : bank) {
      if (b.getHolder().getIdNr() == idNr) {
        return b.getHolder();
      }
    }
    return null;
  }

  /**
   * Tar bort konto med nummer ’number’ från banken. Returnerar true om kontot
   * fanns (och kunde tas bort), annars false.
   */
  public boolean removeAccount(int number) {
    if (findByNumber(number) == null) {
      return false;
    }
    bank.remove(findByNumber(number));
    return true;
  }

  /**
   * Returnerar en lista innehållande samtliga bankkonton i banken. Listan är
   * sorterad på kontoinnehavarnas namn.
   */
  public ArrayList<BankAccount> getAllAccounts() {
    BankAccount temp = null;
    for (int i = 0; i < bank.size() - 1; i++) {
      for (int j = i + 1; j < bank.size(); j++) {
        if (bank.get(i).getHolder().getName().compareTo(bank.get(j).getHolder().getName()) > 0) {
          temp = bank.get(i);
          bank.set(i, bank.get(j));
          bank.set(j, temp);
        }
      }
    }
    return bank;
  }

  /**
   * Söker upp och returnerar bankkontot med kontonummer ’accountNumber’.
   * Returnerar null om inget sådant konto finns.
   */
  public BankAccount findByNumber(int accountNumber) {
    for (BankAccount b : bank) {
      if (b.getAccountNumber() == accountNumber) {
        return b;
      }
    }
    return null;
  }

  /**
   * Söker upp alla bankkonton som innehas av kunden med id-nummer ’idNr’. Kontona
   * returneras i en lista. Kunderna antas ha unika id-nummer.
   */
  ArrayList<BankAccount> findAccountsForHolder(long idNr) {
    ArrayList<BankAccount> temp = new ArrayList<BankAccount>();
    for (BankAccount b : bank) {
      if (b.getHolder().getIdNr() == idNr) {
        temp.add(b);
      }
    }
    return temp;
  }

  /**
   * Söker upp kunder utifrån en sökning på namn eller del av namn. Alla personer
   * vars namn innehåller strängen ’namePart’ inkluderas i resultatet, som
   * returneras som en lista. Samma person kan förekomma flera gånger i
   * resultatet. Sökningen är "case insensitive", det vill säga gör ingen skillnad
   * på stora och små bokstäver.
   */
  public ArrayList<Customer> findByPartOfName(String namePart) {
    ArrayList<Customer> customer = new ArrayList<Customer>();
    namePart = namePart.toLowerCase();
    for (BankAccount b : bank) {
      if (b.getHolder().getName().toLowerCase().contains(namePart)) {
        if (!(customer.contains(b.getHolder()))) {
          customer.add(b.getHolder());
        }
      }
    }
    return customer;
  }

  public int getSize() {
    return bank.size();
  }
}
