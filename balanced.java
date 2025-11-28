import java.util.*;
public class balanced {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int n =  sc.nextInt();
        int []arr = new int[n];
        for(int i = 0; i<n;i++ ){
            arr[i]= sc.nextInt();
        }
        int start = 0;
        int end = arr.length-1;
        int mid = start + ( end-start)/2;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = start;i<mid;i++){
            sum1+=arr[i];
        }
        for (int i = mid+1;i<=end;i++){
            sum2+=arr[i];
        }
        if(sum1 == sum2){
            System.out.println("Balanced");
        }
        else{
            System.out.println("Imbalance");
        }
    }
}

