public class Pattern {
    public static void main(String[] args) {
        pattern(5);
    }
    static void pattern(int n){
        for (int i = 0; i < (2 * n); i++) {

            int totalColsInRow = i > n ? (2 * n) - i : i;

            int noOfSpaces = n - totalColsInRow;
            for (int s = 0; s < noOfSpaces; s++) {
                System.out.print(" ");
            }

            for (int j = 0; j < totalColsInRow; j++) {
                System.out.print("* ");
            }

            System.out.println(); // Move to next line after each row
        }
    }
}
