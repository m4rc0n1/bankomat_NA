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
}
