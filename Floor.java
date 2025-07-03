public class Floor {
    public static void main(Pattern[] args) {
        int[] arr = {1, 4, 5, 6, 10, 12, 25, 26, 30, 45};
        int target = 0;
        int ans = floorSearch(arr, target);
        System.out.println(ans);
    }

    static int floorSearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return end;
    }
}