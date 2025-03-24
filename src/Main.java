import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Bankomata xosh gelmisiz ***");
        NewAccount newAccount = new NewAccount();
        while(true){
           int secim =0;
           System.out.println("Zehmet olmasa secim edin (1,2,3,4,5,6):"
                   + "\n1. Qeydiyyat"
                   + "\n2. Deposit money"
                   + "\n3. Withdraw money"
                   + "\n4. Balansi goster"
                   + "\n5. Pin deyis"
                   + "\n6. Hesabi sil");
           if(scanner.hasNextInt()){
               secim = scanner.nextInt();
               switch(secim){
                   case 1: {
                       newAccount.register();
                       break;
                   }
                   case 2: {
                       newAccount.depositMoney();
                       break;
                   }
                   case 3: {
                       newAccount.withdrawMoney();
                       break;
                   }
                   case 4:{
                       newAccount.viewAccountBalance();
                       break;
                   }
                   case 5:{
                       newAccount.changePin();
                       break;
                   }
                   case 6:{
                       newAccount.deleteAccount();
                       break;
                   }
                   default:
                       System.out.println("Girdiyiniz secim duzgun deyil");
                       break;
               }
           }
           else{
               System.out.println("Yanlis sorgu");
               scanner.nextDouble();

              newAccount.waitResponse(1);
           }
       }

    }
}
