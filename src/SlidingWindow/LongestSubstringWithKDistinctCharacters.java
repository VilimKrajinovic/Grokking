package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class LongestSubstringWithKDistinctCharacters {

    public static int findLength(String str, int k) {
        int max = 0;
        StringBuilder builder = new StringBuilder();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            builder.append(str.charAt(windowEnd));

            while (distinctCharacters(builder.toString()) > k) {
                builder.deleteCharAt(0);
            }

            max = Math.max(max, builder.toString().length());
        }
        return max;
    }

    public static int findLengthNoStreams(String str, int k) {
        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightCharacter = str.charAt(windowEnd);
            frequencyMap.put(rightCharacter, frequencyMap.getOrDefault(rightCharacter, 0) + 1);

            while (frequencyMap.size() > k) {
                char leftCharacter = str.charAt(windowStart);
                frequencyMap.put(leftCharacter, frequencyMap.get(leftCharacter) - 1);
                if (frequencyMap.get(leftCharacter) == 0) {
                    frequencyMap.remove(leftCharacter);
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
        System.out.println("Length of the longest substring: " + findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + findLength("cbbebi", 3));
        System.out.println("Length of the longest substring: " + findLength("eceba", 2));
        System.out.println("Length of the longest substring: " + findLength("a", 1));

        System.out.println("Length of the longest substring: " + findLengthNoStreams("araaci", 2));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("araaci", 1));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("cbbebi", 3));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("eceba", 2));
        System.out.println("Length of the longest substring: " + findLengthNoStreams("a", 1));
    }
}