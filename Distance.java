import java.util.*;

public class Distance {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        System.out.println("Enter Liter of fuel to to filed");
        double lt = sc.nextDouble();
        if(lt<0) System.out.println("Invalid input");

        System.out.println("Enter distance to be travelled");
        double dis =  sc.nextDouble();
        if(dis <0) System.out.println("Invalid input");

        double hundred =  ((lt/dis)*100);
        System.out.printf("Ltrs per 100 km %.2f " , hundred);

        double miles = (dis*0.6214);
        double gallons =(lt*0.2642);
        double mg = miles/gallons;
        System.out.println();
        System.out.printf("Miles/gallons %.2f" ,mg);

    }
}
