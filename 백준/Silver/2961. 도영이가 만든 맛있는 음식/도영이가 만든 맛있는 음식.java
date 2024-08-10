import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int min = 100000000;
    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) { // [0] 신맛, [1] 쓴맛
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);
        System.out.println(min);
    }

    public static void dfs(int i, int s, int b) {
        // i: 탐색하는 요소 s : 신맛, b: 쓴맛
        // 1. 종료 조건
        if (i == N) { // 모든 경우를 모두 탐색했으 경
            if (b != 0) { // 아무것도 탐색하지 않은 경우는 제외
                if (min > Math.abs(s - b)) {
                    min = Math.abs(s - b);
                }
            }
            return;
        }

        // 현재 재료를 선택 O 경우 누적 계산
        dfs(i + 1, s * arr[i][0], b + arr[i][1]);

        // 현재 재료를 선택 X 경우 pass
        dfs(i + 1, s, b);

    }
}
