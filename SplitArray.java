public class SplitArray {
    
    public int splitArray(int[] nums, int m) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            start = Math.max(start, nums[i]);
            end += nums[i];
        }

        // binary search
        while (start < end) {

            int mid = start + (end - start) / 2;

            // calculate how many pieces we can divide this in with this max sum
            int sum = 0;
            int pieces = 1;
            for(int num : nums) {
                if (sum + num > mid) {
                    // we cannot add this in this subarray, make new one
                    // we add this num in new subarray, then sum = num
                    sum = num;
                    pieces++;
                } else {
                    sum += num;
                }
            }

            if (pieces > m) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }
        return end; // here start == end
    }
}