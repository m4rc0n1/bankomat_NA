import java.util.Scanner;

public class NewAccount {
    private String name;
    private String surname;
    private int pin;
    private ValyutaType valyuta;
    private double balance;
    private boolean isAccountCreated = false;
    private String cariBalanceMessage() {
       return isAccountCreated ? "Cari balans: " + balance + " " + valyuta.getValyutaAd() : "";
    }

    public void register(){
        if(isAccountCreated){
            System.out.println("Istifadeci artiq yaradilib. Istifadeci adi: " + name);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa adinizi yazin");
        String name = scanner.nextLine();
        this.name = name;
        System.out.println("Zehmet olmasa soyadinizi yazin");
        String surname = scanner.nextLine();
        this.surname = surname;
        while(true){
            System.out.println("Zehmet olmasa pin yazin");
            if(scanner.hasNextInt()){
                int pin = scanner.nextInt();
                if(pin <1000 || pin >9999) {
                    System.out.println("Girdiyiniz pin 4 reqemli deyil");
                    scanner.next();
                }
                else {
                    this.pin = pin;
                    break;
                }
            }
            else{
                System.out.println("Zehmet olmazsa reqem daxil edin.");
                scanner.next();
            }
        }
        scanner.nextLine();
        while(true){
           System.out.println("Zehmet olmasa valyuta yazin: AZN, USD, EUR");
           String valyuta = scanner.nextLine();
           try{
               this.valyuta = ValyutaType.fromString(valyuta);
               break;
           }catch(IllegalArgumentException e){
               System.out.println(e.getMessage() + " Duzgun valyuta secin: AZN, USD, EUR" );
           }
       }
        this.balance = 0.0;
        System.out.println(name +  " " + surname + " " + " adli hesab yaradildi, cari balans "
                + balance + " " + valyuta.getValyutaAd());
        isAccountCreated = true;
    }
    public void depositMoney(){
        if(!isAccountCreated ){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa meblegi daxil edin: ");
        while (true){
           if(scanner.hasNextDouble()){
               double balance = scanner.nextDouble();
               if (balance >= 0) {
                   this.balance += balance;
                   System.out.println("Depozit uğurla həyata keçirildi.");
                   System.out.println(cariBalanceMessage());
                   break;
               } else {
                   System.out.println("Depozit üçün müsbət məbləğ daxil edin.");
               }
           }
           else {
               System.out.println("Zehmet olmazsa yalniz reqem daxil edin.");
               scanner.next();
           }
        }
    }
    public void viewAccountBalance() {
        if(!isAccountCreated){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            return;
        }
        System.out.println(cariBalanceMessage());
    }

    public void withdrawMoney() {
        if(!isAccountCreated){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa cixarmaq istediyiz meblegi daxil edin: ");
        while (true) {
            if(scanner.hasNextDouble()){
                double money = scanner.nextDouble();
                if(money > balance){
                    System.out.println("Balansda kifayet qeder pul yoxdur. " + cariBalanceMessage() + "  Mebleg daxil edin.");
                }
                else {
                    balance-=money;
                    System.out.println(money + " " + valyuta.getValyutaAd() + " ugurla cixarildi " + cariBalanceMessage());
                    break;
                }
            }
            else {
                System.out.println("Zehmet olmazsa yalniz reqem daxil edin.");
                scanner.next();
            }
        }
    }
}
