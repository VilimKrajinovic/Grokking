package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBaskets {
    public static int findLength(char[] arr) {
        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> frequency = new HashMap<>();

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            char right = arr[windowEnd];
            frequency.put(right, frequency.getOrDefault(right, 0)+1);
            while(frequency.size() > 2){
                char left = arr[windowStart];
                frequency.put(left, frequency.get(left) - 1);
                if(frequency.get(left) == 0){
                    frequency.remove(left);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " + findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " + findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }
}