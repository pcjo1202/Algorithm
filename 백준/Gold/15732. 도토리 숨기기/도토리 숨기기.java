import java.io.*;
import java.util.*;

public class Main {
    static int N, K, D;
    static Rule[] rules;

    static class Rule {
        int start, end, step;

        public Rule(int start, int end, int step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 상자의 개수
        K = Integer.parseInt(st.nextToken()); // 규칙 개수
        D = Integer.parseInt(st.nextToken()); // 도토리 수

        rules = new Rule[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int step = Integer.parseInt(st.nextToken());

            rules[i] = new Rule(start, end, step);
        }

        int left = 1, right = N, result = N;

        while (left <= right) {
            int mid = (left + right) / 2;

            long cnt = check(mid);

            if (cnt >= D) {
                result = mid;   // 마지막 도토리는 mid 이하에 존재
                right = mid - 1;
            } else {
                left = mid + 1; // 더 뒤쪽 상자 탐색
            }
        }

        System.out.println(result);
    }

    // mid 상자까지 들어가는 도토리 개수를 구하는 함수
    static long check(int mid) {
        long sum = 0;
        for (Rule rule : rules) {

            if (rule.start > mid) continue; // mid 이전에 시작 안 함
            int last = Math.min(rule.end, mid);
            if (last < rule.start) continue;

            // start, start+step, start+2*step, start+3*step ... last
            sum += ((last - rule.start) / rule.step) + 1;

            if (sum > D) break; // D개 이상이면 더 계산할 필요 없음
        }
        return sum;
    }
}
