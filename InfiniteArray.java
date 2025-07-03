public class InfiniteArray {
    public static void main(Pattern[] args) {
       int[] arr ={1,3,4,5,6,8,9,10,13,14,20,21,25,27,30} ;
       int target = 14;
        System.out.println(ans(arr,target));
    }
static int ans ( int[] arr , int target){

        //lets start solving using chunks initially assume size of array as 2
    int start = 0;
    int end = 1;

    while(target>arr[end]){
        int newStart = end+1;
        end = end + (end-start+1)*2;
        start = newStart;
    }
    return binarySearchForInfiniteArray(arr,target, start,end);
}
static int binarySearchForInfiniteArray(int[] arr, int target, int start, int end) {
    while (start <= end) {
        int mid = start + (end - start)/2;

        if (target < arr[mid]) {
            end = mid - 1;
        } else if (target > arr[mid]) {
            start = mid + 1;
        }
        else{
            return mid;
        }
    }
    return -1;
}
}
