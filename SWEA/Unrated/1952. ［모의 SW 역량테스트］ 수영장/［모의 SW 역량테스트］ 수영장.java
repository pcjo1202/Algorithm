import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int minTotal;
    static int[] plan, prices, isSelectedTicket;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            plan = new int[20]; // 각 달의 이용 계획
            prices = new int[10]; // 0: 1일, 1: 1달 : 2: 3달 3: 1년 이용권
            minTotal = Integer.MAX_VALUE; // 가장 적은 비용으로 이용한 금액
            isSelectedTicket = new int[4]; // 이용권을 선택한 횟수를 저장

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            subset(0, 0);

            sb.append(String.format("#%d %d\n", test_case, minTotal));
        }
        System.out.println(sb);
    }

    static void subset(int depth, int cnt) { // cnt..누적 요금?
        // 가지치기
        if (cnt >= minTotal) return;

        // 기저 조건
        if (depth > 12) {
            return;
        }
        if (depth == 12) {
            minTotal = Math.min(minTotal, cnt);
            return;
        }

        // 탐색 조건
        // 0달에 1년권을  선택 했을 때 =>
        subset(depth + 12, cnt + prices[3]);
        // 0달에 3달권을  선택 했을 때 =>
        subset(depth + 3, cnt + prices[2]); // 3달뒤로
        // 0달에 1달권을  선택 했을 때 =>
        subset(depth + 1, cnt + prices[1]);
        // 0달에 1일권을  선택 했을 때 =>
        subset(depth + 1, cnt + prices[0] * plan[depth]);
    }
}