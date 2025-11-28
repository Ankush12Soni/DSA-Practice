import java.util.*;
public class Student {
    int rollNo;
    String name;
    int []marks;

    public Student(int rollNo,String name,int[]marks){
        this.rollNo = rollNo;
        this.name= name;
        this.marks= marks;
    }

    double calculateAverage(){
        int sum = 0;
        for(int n: marks){
            sum+=n;
        }
        return sum/5.0;
    }

    char calculateGrade(double avg){
        if(avg>=90){
            return 'A';
        }
        else if(avg>=75){
            return 'B';
        }
        else if(avg>=60){
            return 'C';
        }
        else{
            return'D';
        }
    }

    void displayDetails(){
        double avg = calculateAverage();
        System.out.println("Roll no : " +rollNo);
        System.out.println("Name : " +name);
        System.out.println("Average : " +avg);
        System.out.println("Grade : "+calculateGrade(avg));
    }
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int Rollno = sc.nextInt();;
        String name = sc.next();
        int [] marks = new int[5];
        for(int i = 0; i<5;i++){
            marks[i]= sc.nextInt();
        }
        Student s =  new Student(Rollno,name,marks);
        s.displayDetails();

    }
}
