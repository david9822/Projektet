package projekt;

import java.util.Scanner;
import java.util.ArrayList;

public class BankApplication{
  private static Scanner scan;
  private static Bank bank;
  private static boolean continueRunning = true;

  public BankApplication(){

  }

  public static void main(String[] args){
    scan = new Scanner(System.in);
    scan.useDelimiter(System.lineSeparator()); 
    bank = new Bank();
    runApplication();
  }

  public static void runApplication(){
    while(continueRunning){
      printMenu();
      directMenuOption(correctInputFromUser());
    }
  }

  public static void directMenuOption(int n){
    switch(n){
      case 1:
        findAccount();
        break;
      case 2:
        findAccountHolder();
        break;
      case 3: 
        deposit();
        break;
      case 4: 
        withDraw();
        break;
      case 5: 
        wireMoney();
        break;
      case 6:
        createAccount();
        break;
      case 7: 
        removeTheAccount();
        break;
      case 8: 
        printAllAccounts();
        break;
      case 9: 
        continueRunning = false;
        break;
  }
}
  
  public static void removeTheAccount(){
    System.out.print("konto: ");
    int account = scan.nextInt();
    if(bank.removeAccount(account)){
      System.out.print(" " + account);
    }
    else {
      System.out.println("felaktigt kontonummer");
    }
  }

  public static void findAccount(){
    System.out.print("id: ");
    long id = scan.nextLong();
    ArrayList<BankAccount> temp = bank.findAccountsForHolder(id);
    
    if (temp.isEmpty()){
      System.out.println("felaktigt kontonummer");
    }
    else{
      for (BankAccount b : temp){
        System.out.println(b);
      }
    }
  }

  public static void findAccountHolder(){
    System.out.print("namn: ");
    String partOfName = scan.next();
    ArrayList<Customer> temp = bank.findByPartOfName(partOfName);
    if(temp.isEmpty()){
      System.out.println("inget sådant namn finns i banken");
    }
    else{
      for (Customer c : temp){
        System.out.println(c);
      }
    }
  }

  public static void deposit(){
    System.out.print("konto: ");
    int input = scan.nextInt();
    if (bank.findByNumber(input) == null){
      System.out.println("kontot existerar inte");
    }
    else{
      System.out.print("belopp: ");
      double wireMoney = scan.nextDouble();

      if(wireMoney >= 0){
        bank.findByNumber(input).deposit(wireMoney);
        System.out.println(bank.findByNumber(input));
      }
      else{
        System.out.println("negativa belopp inte tillåtna"); 
      }
    }
  }

  public static void printAllAccounts(){
    ArrayList<BankAccount> temp = bank.getAllAccounts();
    for (int i = 0; i < temp.size(); i++){
      System.out.println(temp.get(i));
    }
  }

  public static void withDraw(){
    System.out.print("från konto: ");
    int input = scan.nextInt();
    
    if (bank.findByNumber(input) == null){
      System.out.println("kontot existerar inte"); 
    }
    else{
      System.out.print("belopp: ");
      double amountTaken = scan.nextDouble();

      if(amountTaken < 0){
        System.out.println("negativa belopp inte tillåtna"); 
      }
      else{
        bank.findByNumber(input).withdraw(amountTaken);

        if (bank.findByNumber(input).getAmount() < 0){
          bank.findByNumber(input).deposit(amountTaken);
          System.out.println("uttaget misslyckades, endast " + bank.findByNumber(input).getAmount() + " på kontot!"); 
        }
        else{
        System.out.println(bank.findByNumber(input));
        }
      }
    }
  }
  
  public static void wireMoney(){
    System.out.print("från konto: ");
    int account1 = scan.nextInt();
    if (bank.findByNumber(account1) == null){
      System.out.println("felaktigt konto"); 
    }
    else{
      System.out.print("till konto: ");
      int account2 = scan.nextInt();
      if (bank.findByNumber(account2) == null || account2 == account1){
        System.out.println("felaktigt konto"); 
      }
      else{
        System.out.print("belopp: ");
        double wireMoney = scan.nextDouble();
        if (wireMoney < 0){
          System.out.println("negativa belopp inte tillåtna");
        }
        else{
          bank.findByNumber(account1).withdraw(wireMoney);
          if (bank.findByNumber(account1).getAmount() < 0){
            bank.findByNumber(account1).deposit(wireMoney);
            System.out.println("överföringen misslyckades, endast " + bank.findByNumber(account1).getAmount() + " på kontot!"); 
          }
          else{
          bank.findByNumber(account2).deposit(wireMoney);
          System.out.println(bank.findByNumber(account1));
          System.out.println(bank.findByNumber(account2));
          }
        }
      }
    }
  }

  public static void createAccount(){
    System.out.print("namn: ");
    String nameInput = scan.next();
    System.out.print("id: ");
    long idInput = scan.nextLong();
    int accountNr = bank.addAccount(nameInput, idInput);
    System.out.println("konto skapat: " + accountNr);
  }

  public static void printMenu(){
    System.out.println("----------------------------------------------------------------------------------------");
    System.out.print("1. Hitta konto utifrån innehavare)" + "\n" + "2. Sök kontoinnehavare utifrån (del av) namn" + "\n" + "3. Sätt in" + "\n" + "4. Ta ut" + "\n" + "5. Överföring" + "\n" + "6. Skapa konto" + "\n" + "7. Ta bort konto" + "\n"+ "8. Skriv ut konton" + "\n" + "9. Avsluta" + "\n" + "val: ");
  }

  public static int correctInputFromUser(){
    String input = "";
    int menuOption = 0;
    do{
      input = scan.next();
      try{
        menuOption = Integer.parseInt(input);
      }catch (Exception e){
        System.out.println("Ange ett heltal mellan (1 - 9) ");
        continue;
      }
      if(menuOption < 1 || menuOption > 9){
        System.out.println("Ange ett heltal mellan (1 - 9) ");
      }
    }while (menuOption < 1 || menuOption > 9);

    return menuOption;
  }
