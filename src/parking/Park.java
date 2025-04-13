package parking;

import org.ietf.jgss.GSSName;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Park {
    String avtomobilNomre;
    List<Map<String,String>> cars;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Park(List<Map<String,String>> cars,String avtomobilNomre){
        this.avtomobilNomre = avtomobilNomre;
        this.cars=cars;
    }

    public void parkIn(){
        Map<String,String> car = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();

        String formattedDate = currentTime.format(formatter);
        car.put("id", "1");
        car.put("plateNumber", avtomobilNomre);
        car.put("enteredDate",formattedDate);
        cars.add(car);
        System.out.println("--------------------------");
        System.out.println(avtomobilNomre + " nomreli avtomobil "+ car.get("enteredDate") +" tarixde park olundu. Her saniye ucun 0.01 AZN odeyeceksiniz");
        System.out.println("--------------------------");
    }

    public void parkOut(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zehmet olmasa odenish edin");
        Map<String,String> parkedCar= null;
        for(Map<String,String> car:cars){
            if(car.get("plateNumber").equals(avtomobilNomre)){
                parkedCar=car;
                break;
            }
        }
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime enteredDate = LocalDateTime.parse(parkedCar.get("enteredDate"),formatter);
        Duration duration = Duration.between(enteredDate,currentTime);
        long seconds = duration.getSeconds();
        double odenilecekMebleg= seconds*0.01;
        System.out.println("Odenilmeli mebleg: " + odenilecekMebleg + " AZN");
        payment(odenilecekMebleg);

        double elaveOdenis =0.0;

        while(true){
            System.out.println("Zehmet olmasa cixish ucun 'q' simvolunu daxil edin");
            long baslamaVaxti = System.currentTimeMillis();
            boolean istifadeciQYazdi = false;
            String simvol = scanner.nextLine();
            while((System.currentTimeMillis() - baslamaVaxti)/1000<5){
                if(simvol.equals("q")){
                    istifadeciQYazdi = true;
                    System.out.println("Avtomobil ugurla parkdan cixdi,elave odenilmeli mebleg: " + elaveOdenis + " AZN");
                    System.out.println(parkedCar.get("plateNumber") + " nomreli avtomobil sistemden silindi");
                    cars.remove(parkedCar);
                    return;
                }
            }
            if(!istifadeciQYazdi){
                long odenisVaxt = (System.currentTimeMillis() -baslamaVaxti)/1000;
                elaveOdenis = odenisVaxt*0.01;
                System.out.println("Vaxtinda cixmadiniz, her saniye ucun 0.01 AZN odeyeceksiniz: " + (elaveOdenis-0.05) + " AZN");
                payment(elaveOdenis-0.05);
                System.out.println(parkedCar.get("plateNumber") + " nomreli avtomobil sistemden silindi");
                cars.remove(parkedCar);
                break;
            }
        }
    }

    public void payment(double odenilecekMebleg){
        Scanner scanner = new Scanner(System.in);
        double toplamOdenilenMebleg = 0.0;
        while(toplamOdenilenMebleg<odenilecekMebleg){
            System.out.println("Zehmet olmasa odenilecek meblegi daxil edin");
            if(scanner.hasNextDouble()){
                double odenilenMebleg = scanner.nextDouble();
                toplamOdenilenMebleg+=odenilenMebleg;
                if(toplamOdenilenMebleg<odenilecekMebleg){
                    System.out.println("Mebleg tam odenmedi, yerde qalan mebleg: " + (odenilecekMebleg-toplamOdenilenMebleg) +" AZN");
                }
                else{
                    System.out.println("Odenish ugurla bitdi. Qaliq: " + (toplamOdenilenMebleg-odenilecekMebleg) + " AZN");
                    break;
                }
            }else{
                System.out.println("Yanlish giris, zehmet olmasa duzgun mebleg daxil edin");
                scanner.next();
            }
        }
    }
}
