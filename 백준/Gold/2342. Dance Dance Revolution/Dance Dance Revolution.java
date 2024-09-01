import java.io.*;
import java.util.*;

public class Main {
    static int size;
    static List<Integer> list;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<>();

        while (st.hasMoreTokens()) {
            int a = Integer.parseInt(st.nextToken());
            if (a == 0) break; // 0이 입력되면 종료
            list.add(a);
        }

        size = list.size();

        dp = new int[size][5][5]; // 동작 순서에따라, 발의 위치에 따라 값을 저장할 dp

        for (int i = 0; i < size; i++) { // -1 로 초기화
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int result = dfs(0, 0, 0);

        System.out.println(result);
    }

    static int dfs(int depth, int left, int right) {
        if (depth == size) {
            return 0;
        }

        if (dp[depth][left][right] != -1) return dp[depth][left][right]; // 이미 밟았던 발판이라면,

        int next = list.get(depth); // 다음 이동해야하는 곳

        int leftScore = dfs(depth + 1, next, right) + addScore(left, next);
        int rightScore = dfs(depth + 1, left, next) + addScore(right, next);

        dp[depth][left][right] = Math.min(leftScore, rightScore);

        return dp[depth][left][right];
    }

    static int addScore(int prev, int next) {
        if (prev == 0) return 2; // 0에서 시작할 때
        if (prev == next) return 1; // 같은 지점
        if (Math.abs(prev - next) == 2) return 4; // 반대쪽
        return 3; // 인접한 경우 (1칸 이동)
    }
}