import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static Map<String, Integer> MBTI_list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            String[] list = br.readLine().split(" ");

            // 비둘기집 원리에 따라...-> 적어도 같은 MBTI 3조합이 생김 -> 0
            if (N >= 33) {
                sb.append(0).append("\n");
                continue;
            }
            
            MBTI_list = new HashMap<>();

            // 같은거 찾기
            for (String mbti : list) {
                MBTI_list.put(mbti, MBTI_list.getOrDefault(mbti, 0) + 1);
            }

            result = Integer.MAX_VALUE;
            List<String> temp = new ArrayList<>(MBTI_list.keySet());
            int size = temp.size();


            for (int i = 0; i < size; i++) {
                for (int j = i; j < size; j++) {
                    for (int k = j; k < size; k++) {
                        String a = temp.get(i);
                        String b = temp.get(j);
                        String c = temp.get(k);

                        // 불가능한 조건 체크
                        // 1. 두개가 같을 때 개수가 2개 미만이면 패스
                        if (i == j && MBTI_list.get(a) < 2) continue;
                        if (i == k && MBTI_list.get(a) < 2) continue;
                        if (j == k && MBTI_list.get(b) < 2) continue;
                        // 2. 3개가 같을 때 3개 미만이면 패스
                        if (i == j && j == k && MBTI_list.get(a) < 3) continue;

                        int sum = getScore(a, b)
                                + getScore(a, c)
                                + getScore(b, c);

                        result = Math.min(sum, result);
                    }
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    static int getScore(String str1, String str2) {
        int sum = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) sum++;
        }
        return sum;
    }
}
