import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
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
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            minTotal = dp();

            sb.append(String.format("#%d %d\n", test_case, minTotal));
        }
        System.out.println(sb);
    }

    static int dp() {
        int[] dp = new int[13];

        for (int i = 1; i <= 12; i++) {
            // 전의 달과 현재 4개의 이용권중 하나를 선택한 것을 합한 것을 비교
            int selDay = dp[i - 1] + plan[i] * prices[0]; // i 달에서 1일권 선택
            int selMonth = dp[i - 1] + prices[1]; // i 달에서 한달권 선택
            dp[i] = Math.min(selDay, selMonth);

            if (i >= 3) { // 3달권도 확인
                dp[i] = Math.min(dp[i], dp[i - 3] + prices[2]);
            }
            if (i >= 12) { // 1년권 확인
                dp[i] = Math.min(dp[i], prices[3]);
            }
        }
        return dp[12];

    }
}
