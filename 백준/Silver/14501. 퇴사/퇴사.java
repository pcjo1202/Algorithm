import java.io.*;
import java.util.*;

public class Main {
    private static int N; // 일 할 수 있는 남은 일
    private static int[] T; // 상담 걸리는 일 수
    private static int[] P; // 상담 후 받는 금액
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        // N의 최대 값이 15 이니까 완전 탐색으로 탐색해보자!
        
        dfs(0, 0);
        System.out.println(max);
    }

    public static void dfs(int i, int accPay) { // 반복해서 탐색 할 일 수, 누적해서 더한 페이
        // 종료 조건 : i 가 마지막 날이거나, 더 크다면 종료
        if (i >= N) {
            max = Math.max(max, accPay);
            return;
        }

        // 탐색 조건 (상담 O or 상담 X)
        // 상담 O
        if (i + T[i] <= N) {
            dfs(i + T[i], accPay + P[i]);
        }
        // 상담 X
        dfs(i + 1, accPay);
    }
}
