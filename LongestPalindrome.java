import java.util.*;

public class LongestPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        String longest = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = str.substring(i, j);
                String rev = new StringBuilder(sub).reverse().toString();

                if (sub.equals(rev) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        System.out.println("Longest Palindromic Substring: " + longest);
        sc.close();
    }
}
