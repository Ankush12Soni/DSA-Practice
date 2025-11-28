import java.util.*;

public class TicketCost {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int noTicket = sc.nextInt();

        if (noTicket < 5 || noTicket > 40) {
            System.out.println("Minimum of 5 and Maximum of 40 Tickets");
            return;
        }
        if (!sc.hasNext()) return;
        char refresh = sc.next().toLowerCase().charAt(0);
        if (!sc.hasNext()) return;
        char coupon = sc.next().toLowerCase().charAt(0);
        if (!sc.hasNext()) return;
        char circle = sc.next().toLowerCase().charAt(0);

        double basePerTicket;
        if (circle == 'k') basePerTicket = 75.0;
        else if (circle == 'q') basePerTicket = 150.0;
        else {
            System.out.println("Invalid Input");
            return;
        }

        double cost = basePerTicket * noTicket;

        // Bulk discount >20 tickets
        if (noTicket > 20) {
            cost = cost * 0.90; // 10% off
        }
        // Additional coupon discount
        if (coupon == 'y') {
            cost = cost * 0.98; // 2% off
        }
        // Add refreshment cost
        if (refresh == 'y') {
            cost += 50.0 * noTicket;
        }

        System.out.printf("Ticket cost: %.2f",cost);
    }
}
