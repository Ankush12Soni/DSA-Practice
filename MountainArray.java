public class MountainArray {
    public static void main(Pattern[] args) {

    }
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length;

        while (start<end){
            int mid = start + (end-start)/2;
            if(arr[mid]>arr[mid+1]){ //means we atre in decending part as midddle element is greater than rigth side hence it could be the possible ans but we check left side also
                end = mid;
            }
            else{ // right array may have more greater no than left we are in ascending part
                start=mid+1;
            }
        }
        return start;//also we can return end as both will point at same no at last
    }
}
