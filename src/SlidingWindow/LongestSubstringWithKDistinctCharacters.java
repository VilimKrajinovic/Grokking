package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithKDistinctCharacters {

    public static int findLengthStreams(String str, int k) {
        int maxLength = Integer.MIN_VALUE;
        StringBuilder tmp = new StringBuilder();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            tmp.append(str.charAt(windowEnd));

            while (distinctCharacters(tmp.toString()) > k) {
                tmp.deleteCharAt(0);
            }
            maxLength = Math.max(maxLength, tmp.toString().length());
        }
        return maxLength;
    }

    public static int findLengthNoStreams(String str, int k) {
        int maxLength = 0;
        int windowStart = 0;
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            frequencyMap.put(rightChar, frequencyMap.getOrDefault(rightChar, 0) + 1);

            while (frequencyMap.size() > k) {
                char leftChar = str.charAt(windowStart);
                frequencyMap.put(leftChar, frequencyMap.get(leftChar) - 1);
                if (frequencyMap.get(leftChar) == 0) {
                    frequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static int distinctCharacters(String str) {
        Set<Character> unique = new HashSet<>();
        str.chars().mapToObj(c -> (char) c).forEach(unique::add);
        return unique.size();
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + findLengthStreams("araaci", 2));
        System.out.println("Length of the longest substring: " + findLengthStreams("araaci", 1));
        System.out.println("Length of the longest substring: " + findLengthStreams("cbbebi", 3));
        System.out.println("Length of the longest substring: " + findLengthStreams("eceba", 2));
        System.out.println("Length of the longest substring: " + findLengthStreams("a", 1));

        System.out.println("Length of the longest substring: " + findLengthNoStreams("araaci", 2));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("araaci", 1));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("cbbebi", 3));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("eceba", 2));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("a", 1));
    }
}