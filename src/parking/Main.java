package parking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
       List<Map<String,String>> cars = new ArrayList<>();
        while(true){
            System.out.println("--------------------------");
            System.out.println("Parkinge xosh gelmisiniz");
            System.out.println("Zehmet olmasa avtomobilin nomresini daxil edin");
            System.out.println("--------------------------");
            Scanner scanner = new Scanner(System.in);
            String avtomobilNomre= scanner.nextLine();
            boolean isCarParked = false;
            Park park = new Park(cars,avtomobilNomre);
            for(Map<String,String> car:cars){
                if(car.get("plateNumber").equals(avtomobilNomre)){
                    isCarParked =true;
                    break;
                }
            }
            if(isCarParked){
                park.parkOut();
            }else{
                park.parkIn();
            }
        }
    }
}

