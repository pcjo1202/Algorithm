import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()); // 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 부분문자열 길이

        String str = br.readLine(); // DNA 문자열

        st = new StringTokenizer(br.readLine());
        int[] rule = new int[4];

        for (int i = 0; i < 4; i++) {
            rule[i] = Integer.parseInt(st.nextToken());
        }


//        final HashMap<Character, Integer> rule = new HashMap<>(); // count를 증가시킬 pwd 규칙

//        rule.put('A', Integer.parseInt(st.nextToken()));
//        rule.put('C', Integer.parseInt(st.nextToken()));
//        rule.put('G', Integer.parseInt(st.nextToken()));
//        rule.put('T', Integer.parseInt(st.nextToken()));

        // 부분 문자열의 개수를 담을 배열을 만듬
        int[] countPart = new int[4]; // 순서대로 A, C, G, T 담기

        // 초기 부분 문자열 세팅
        for (int i = 0; i < P; i++) {
            if (str.charAt(i) == 'A') countPart[0]++;
            else if (str.charAt(i) == 'C') countPart[1]++;
            else if (str.charAt(i) == 'G') countPart[2]++;
            else if (str.charAt(i) == 'T') countPart[3]++;
        }

        int count = 0;

        // 초기 문자열 체크 rule 과 countPart가 같다면,
        if (check(rule, countPart)) count++;


        // 두번째 문자열 부터
        for (int i = 0; i < S - P; i++) { // 전체 문자열에서 부분 분자열의 크기 만큼 움직일 거임
            // 첫 부분 문자열 countPart에서 빼기
            if (str.charAt(i) == 'A') countPart[0]--;
            else if (str.charAt(i) == 'C') countPart[1]--;
            else if (str.charAt(i) == 'G') countPart[2]--;
            else if (str.charAt(i) == 'T') countPart[3]--;


            // 마지막 문자열에 countPart 추가하기
            if (str.charAt(i + P) == 'A') countPart[0]++;
            else if (str.charAt(i + P) == 'C') countPart[1]++;
            else if (str.charAt(i + P) == 'G') countPart[2]++;
            else if (str.charAt(i + P) == 'T') countPart[3]++;

            // rule 과 부분 문자열의 내용을 비교
            if (check(rule, countPart)) count++;
        }
        System.out.println(count);
    }

    public static boolean check(int[] rule, int[] countPart) {
        for (int i = 0; i < 4; i++) {
            if (rule[i] > countPart[i]) {
                return false;
            }
        }
        return true;
    }
}
