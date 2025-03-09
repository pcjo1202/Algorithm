import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 인식할 수 있는 알파벳 종류
        str = br.readLine().trim(); //

        Map<Character, Integer> alphabet = new HashMap<>();

        int left = 0, right = 0, count = 0, max = 0; // 투포인터, 서로다른 문자의 개수, 현재까지 최대 길이

        while (right < str.length()) {
            char cur = str.charAt(right++);
            // 새로운 알파벳일 때
            alphabet.put(cur, alphabet.getOrDefault(cur, 0) + 1);
            if (alphabet.get(cur) == 1) {
                count++;
            }

            // 사용된 알파벳의 수가 N 보다 클 때
            while (count > N) {
                char remove = str.charAt(left++);
                if (alphabet.get(remove) > 0) {
                    alphabet.replace(remove, alphabet.get(remove) - 1);
                }

                if (alphabet.get(remove) == 0) {
                    alphabet.remove(remove);
                    count--;
                }
            }

            max = Math.max(max, right - left);
        }

        System.out.println(max);
    }
}
