public class Reverse_integer_32bit {
    public static void main(String[] args) {
        Reverse_integer_32bit revObj = new Reverse_integer_32bit();

        int[] testInputs = {123, -123, 120, 1534236469};

        for (int x : testInputs) {
            int result = revObj.reverse(x);
            System.out.println("Input: " + x + " → Output: " + result);
        }
    }
    public int reverse(int x) {
        int rev = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Check for overflow/underflow
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) return 0;

            rev = rev * 10 + digit;
        }

        return rev;
    }
}

