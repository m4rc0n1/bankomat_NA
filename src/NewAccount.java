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
public void waitResponse(int seconds){

        try {
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException e){
         throw new RuntimeException(e);
        }
}
    public void register(){
        if(isAccountCreated){
            System.out.println("Istifadeci artiq yaradilib. Istifadeci adi: " + name);
            waitResponse(1);
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
        waitResponse(1);
    }
    public void depositMoney(){
        if(!isAccountCreated ){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            waitResponse(1);
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
        waitResponse(1);
    }
    public void viewAccountBalance() {
        if(!isAccountCreated){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            waitResponse(1);
            return;
        }
        System.out.println(cariBalanceMessage());
    }
    public void withdrawMoney() {
        if(!isAccountCreated){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            waitResponse(1);
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
        waitResponse(1);
    }
    public void changePin() {
        if(!isAccountCreated){
            System.out.println("Zehmet olmasa qeydiyyatdan kecin");
            waitResponse(1);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa pin codu deyismek ucun Ad  daxil edin : ");
        while (true) {
            String name = scanner.nextLine();
            if(name.equals(this.name) ){
                while (true){
                    if(scanner.hasNextInt()){
                        int yenipin = scanner.nextInt();
                        scanner.nextLine();
                        if(yenipin==this.pin){
                            System.out.println("Yeni pin kohne pin ola bilmez");
                            return;
                        }
                        if(yenipin <1000 || yenipin >9999) {
                            System.out.println("Girdiyiniz pin 4 reqemli deyil");
//                    scanner.nextInt();
                        }
                        else {
                            this.pin = yenipin;
                            System.out.println("Yeni pin yaradildi");
                            break;
                        }
                    }
                    else{
                        System.out.println("Zehmet olmazsa reqem daxil edin.");
                        scanner.next();
                    }
                }
                break;
            }
            else {
                System.out.println("Zehmet olmasa adi duzgun qeyd edin" );
            }

        }
        waitResponse(1);
    }
    public void deleteAccount() {
        if (!isAccountCreated) {
            System.out.println("Zehmet olmasa qeydiyyatdan keçin.");
            waitResponse(1);
            return;
        }
        if (this.balance > 0) {
            System.out.println("Hesabda pul olduğu üçün hesabi silmek mümkün deyil.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa hesabınızı silmək üçün PIN kodunu daxil edin: ");
        int pin = scanner.nextInt();

        if (pin == this.pin) {
            // Hesabın silinməsi
            System.out.println("Hesab silindi.");
            isAccountCreated = false;
            System.out.println("Yeni qeydiyyat üçün daxil olmalısınız.");
        } else {
            System.out.println("PIN kodu yanlışdır. Yenidən cəhd edin.");
        }
    }


}
