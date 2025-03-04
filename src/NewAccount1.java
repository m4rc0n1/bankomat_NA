
import java.util.Scanner;

public class NewAccount1 {
    private String name;
    private String surname;
    private int pin;
    private ValyutaType valyuta;
    private double balance;

    // Register method
    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa adinizi yazin");
        this.name = scanner.nextLine();

        System.out.println("Zehmet olmasa soyadinizi yazin");
        this.surname = scanner.nextLine();

        while (true) {
            System.out.println("Zehmet olmasa pin yazin");
            int pin = scanner.nextInt();
            if (pin < 1000 || pin > 9999) {
                System.out.println("Girdiyiniz pin 4 reqemli deyil");
            } else {
                this.pin = pin;
                break;
            }
        }

        scanner.nextLine(); // Clear buffer after nextInt

        while (true) {
            System.out.println("Zehmet olmasa valyuta yazin: AZN, USD, EUR");
            String valyuta = scanner.nextLine();
            try {
                this.valyuta = ValyutaType.fromString(valyuta);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Duzgun valyuta secin: AZN, USD, EUR");
            }
        }

        this.balance = 0.0; // Initial balance is 0.0
        System.out.println(name + " " + surname + " adli hesab yaradildi, cari balans " + balance + " " + valyuta.getValyutaAd());
    }


    public void depositMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa meblegi daxil edin: ");
        double depositAmount = scanner.nextDouble();

        if (depositAmount > 0) {
            balance += depositAmount;
            System.out.println("Depozit uğurla həyata keçirildi.");
        } else {
            System.out.println("Depozit üçün müsbət məbləğ daxil edin.");
        }

        System.out.println("Cari balans: " + balance);
    }


    public void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa cixarmaq istediyiz meblegi daxil edin: ");
        double withdrawAmount = scanner.nextDouble();

        if (withdrawAmount > 0 && withdrawAmount <= balance) {
            balance -= withdrawAmount;
            System.out.println("Çıxarış uğurla həyata keçirildi.");
        } else if (withdrawAmount <= 0) {
            System.out.println("Çıxarış üçün düzgün məbləğ daxil edin.");
        } else {
            System.out.println("Balansda kifayət qədər vəsait yoxdur.");
        }

        System.out.println("Cari balans: " + balance);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Bankomata xosh gelmisiz ***");
        NewAccount newAccount = new NewAccount();

        while (true) {
            int secim = 0;
            System.out.println("Zehmet olmasa secim edin (1,2,3,4,5,6):"
                    + "\n1. Qeydiyyat"
                    + "\n2. Deposit money"
                    + "\n3. Withdraw money"
                    + "\n4. Balansi goster"
                    + "\n5. Pin deyis"
                    + "\n6. Hesabi sil");
            if (scanner.hasNextInt()) {
                secim = scanner.nextInt();
                switch (secim) {
                    case 1: {
                        newAccount.register();
                        break;
                    }
                    case 2: {
                        newAccount.depositMoney();
                        break;
                    }
                    case 3: {
                        newAccount.withdrowMoney();
                        break;
                    }

                    case 0:
                        System.out.println("Proqramdan cixir...");
                        break;

                    default:
                        System.out.println("Yanlis secim. Zəhmət olmasa düzgün bir seçim edin.");
                }

            }
            while (secim != 0) ; // Exit when user chooses 0

            scanner.close();
        }
    }
}

