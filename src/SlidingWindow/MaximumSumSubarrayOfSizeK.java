package slidingwindow;

public class MaximumSumSubarrayOfSizeK {

    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0;
        int windowStart = 0;
        int windowSum = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];

            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 1, 5, 1, 3, 2 };
        int result = findMaxSumSubArray(3, array);
        System.out.println(result);
    }
}