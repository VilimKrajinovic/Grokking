package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NoRepeatSubstrings {

    public static int findLength(String str) {
        int maxLength = 0;
        int windowStart = 0;
        Map<Character, Integer> frequency = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            frequency.put(rightChar, frequency.getOrDefault(rightChar, 0) + 1);
            while (frequency.get(rightChar) > 1) {
                char leftChar = str.charAt(windowStart);
                frequency.put(leftChar, frequency.get(leftChar) - 1);
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static int findLengthUsingStringBuilder(String str) {
        int maxLength = 0;

        StringBuilder tmp = new StringBuilder();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            tmp.append(str.charAt(windowEnd));
            while (!hasNoRepeatingCharacters(tmp.toString())) {
                tmp.deleteCharAt(0);
            }
            maxLength = Math.max(maxLength, tmp.toString().length());
        }
        return maxLength;
    }

    public static boolean hasNoRepeatingCharacters(String str) {
        Set<Character> unique = new HashSet<>();
        str.chars().mapToObj(c -> (char) c).forEach(unique::add);
        return unique.size() == str.length();
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + findLengthUsingStringBuilder("aabccbb"));
        System.out.println("Length of the longest substring: " + findLengthUsingStringBuilder("abbbb"));
        System.out.println("Length of the longest substring: " + findLengthUsingStringBuilder("abccde"));

        System.out.println("Length of the longest substring: " + findLength("aabccbb"));
        System.out.println("Length of the longest substring: " + findLength("abbbb"));
        System.out.println("Length of the longest substring: " + findLength("abccde"));
    }
}