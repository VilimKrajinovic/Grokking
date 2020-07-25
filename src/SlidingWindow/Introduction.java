package slidingwindow;

import java.util.Arrays;

public class Introduction {

    public static double[] findAveragesSlidingWindow(int k, int[] arr) {
        double[] result = new double[arr.length - k + 1];
        int windowStart = 0;
        double windowSum = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];

            if(windowEnd >= k - 1){
                result[windowStart] = windowSum / k;
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // double[] badWay = findAveragesBruteforce(5, new int[] { 1, 3, 2, 6, -1, 4, 1,
        // 8, 2 });
        // System.out.println("Averages of subarrays of size K: " +
        // Arrays.toString(badWay));

        double[] result = findAveragesSlidingWindow(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }

}