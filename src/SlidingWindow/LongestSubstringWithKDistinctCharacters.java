package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

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
    }
}