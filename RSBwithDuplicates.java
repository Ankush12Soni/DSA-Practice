public class RSBwithDuplicates {
    public static void main(Pattern[] args) {
        int[] arr = {9,9,1,2,2,4,8};
        System.out.println(findPivotwithDuplicate(arr));
    }
    static int findPivotwithDuplicate(int[] arr){
        int start = 0;
        int end = arr.length-1;
        while(start<=end){
            int mid = start +(end-start)/2;
            if(mid<end && arr [mid] > arr [mid+1]){
                return mid;
            }
            if (mid>start&& arr[mid]< arr[mid-1]){
                return mid -1;
            }
           if(arr[mid]== arr[start]&&arr[mid]==arr[end]){
               if(arr[start]>arr[start+1]){
                   return start;
               }start++;
               if(arr[end]<arr[end-1]){
                   return end-1;
               }end--;
           }
           else if(arr[start]<arr[mid]||arr[start]==arr[mid]&&arr[mid]>arr[end]){
               start = mid+1;
           }else{
               end= mid-1;
           }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int pivot = findPivotwithDuplicate(nums);
        if(pivot == -1) {
            return binarySearch(nums,target,0, nums.length-1);
        }
        if(nums[pivot]==target){
            return pivot;}
        if(target>= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }
        return binarySearch(nums, target,pivot+1, nums.length-1);
    }
    static int binarySearch(int[] arr, int target,int start , int end) {
        while (start <= end) {
            int mid = start + (end - start);
            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

