import java.util.Arrays;

public class SortedMatrix {
    public static void main(Pattern[] args) {
        int[][] arr = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        };
        System.out.println(Arrays.toString(search(arr,9)));
    }
    static int[] binarySearch(int[][] matrix , int row , int cStart,int cEnd, int target){
        while (cStart <= cEnd) {
            int mid = cStart + (cEnd - cStart)/2;
            if (target == matrix[row][mid]) {
                return new int[]{row,mid};
            }
            if (target > matrix[row][mid]) {
                cStart = mid + 1;
            } else {
                cEnd = mid-1;
            }
        }
        return new int[]{-1,-1};
    }
     static int[]search(int [][] matrix,int target){
        int row = matrix.length;
        int col = matrix[0].length;

//if only one row
         if (row == 1) {
             return binarySearch(matrix,0,0,col-1,target);
         }
         int rStart = 0;
         int rEnd = row-1;
         int cMid = col/2;
         //loop till only two row remaining
         while(rStart<rEnd-1){
             int mid = rStart+(rEnd-rStart)/2;

             if(matrix[mid][cMid]==target){
                 return new int[]{mid,cMid};
             }
             if (matrix[mid][cMid]<target){
                 rStart=mid;
             }else{
                 rEnd=mid;
             }//from here after loop ends we have 2 rows remaining
         }

         //now check whether the element is in col of 2 row

         if(matrix[rStart][cMid]==target){
             return new int[]{rStart,cMid};
         }
         if (matrix[rStart+1][cMid]==target) {
             return new int[]{rStart+1,cMid};
         }

         //search in 1st half
         if (target<= matrix[rStart][cMid-1]){
             return binarySearch(matrix,rStart,0,cMid-1,target);
         }
         //search in 2nd half
         if (target<= matrix[rStart][cMid+1] && target <= matrix[rStart][col-1]){
             return binarySearch(matrix,rStart,cMid+1,col-1,target);
         }
         //search in 3rd half
         if (target<= matrix[rStart+1][cMid-1]){
             return binarySearch(matrix,rStart+1,0,cMid-1,target);
         }
         //search in 4th half
         else{
             return binarySearch(matrix,rStart+1,cMid+1,col-1,target);
         }
     }
}
