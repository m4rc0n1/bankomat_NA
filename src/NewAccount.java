import java.util.Scanner;

public class NewAccount {
    private String name;
    private String surname;
    private int pin;
    private ValyutaType valyuta;
    private double balance;

    public void register(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa adinizi yazin");
        String name = scanner.nextLine();
        this.name = name;
        System.out.println("Zehmet olmasa soyadinizi yazin");
        String surname = scanner.nextLine();
        this.surname = surname;
        while(true){
            System.out.println("Zehmet olmasa pin yazin");
            int pin = scanner.nextInt();
            if(pin <1000 || pin >9999) System.out.println("Girdiyiniz pin 4 reqemli deyil");
            else {
                this.pin = pin;
                break;
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
        System.out.println(name +  " " + surname + " " + " adli hesab yaradildi, cari balans " + balance + " " + valyuta.getValyutaAd());
    }
    public double depositMoney(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Zehmet olmasa meblegi daxil edin: ");
            double balance = scanner.nextDouble();
            this.balance = scanner.nextDouble();
            if (balance > 0) {
                balance += balance;
                System.out.println("Depozit uğurla həyata keçirildi.");
            } else {
                System.out.println("Depozit üçün müsbət məbləğ daxil edin.");
//                this.balance=balance;
//                break;
            }
            System.out.println("Cari balans: " + balance);
        }
    }

    public double getBalance() {
        return balance;
    }

    public double withdrowMoney() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Zehmet olmasa cixarmaq istediyiz meblegi daxil edin: ");
            double balance = scanner.nextDouble();
            this.balance = 0.0;
            if (balance > 0 && balance <= balance) {
                balance -= balance;
                System.out.println("Çıxarış uğurla həyata keçirildi.");
//            } else if (balance <= 0) {
//                System.out.println("Çıxarış üçün duzgun məbləğ daxil edin.");
            } else {
                System.out.println("Balansda kifayət qədər vəsait yoxdur.");
            }
            System.out.println("Cari balans: " + balance);
        }
    }
}
