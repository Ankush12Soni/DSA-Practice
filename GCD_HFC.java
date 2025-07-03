import java.sql.SQLOutput;

public class GCD_HFC {
    public static void main(String[] args) {
        System.out.println(gcd(2,4));
        System.out.println(lcm(4,9));
    }
    static int gcd (int a,int b){
        if (a==0)return b;
        return gcd(b%a,a);
    }
    static int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }
}
