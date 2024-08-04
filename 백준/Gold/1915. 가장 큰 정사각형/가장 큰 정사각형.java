import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[][] dp;
    private static int maxSide = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            String str = br.readLine();
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = str.charAt(j - 1) - '0';
                dp[i][j] = -1; // 탐색 여부를 체크하기 위한 초기값
            }
        }

//        System.out.println(Arrays.deepToString(map));
        /*
         * 아이디어! (큰 사각형을 작은 사각형으로 쪼개) Top-Down 방식
         * 정사각형이라고 어떻게 판단하는가??
         * - 정사각형의 크기는 1, 4, 9.. 이렇게 될 것이다.
         * - 크기가 9일 경우에는 내부에 크기가 4인 정사각형이 4개 존재
         * - 크기가 4일 경우 => 내부에 크기가 1인 정사각형이 4개 존재
         * 이것을 일반화 해보면,
         * - 크기가 "한변의 크기"가 n 인 정사각형 내부에는 "한 변의 크기"가 (n-1)인 정사각형이 4개 존재
         *
         * - dp에 어떤 내용을 담았을 때 위 내용을 파악할 수 있을까???
         * - 모든 map을 돌아보며, 체크를 하며, 현재까지
         * - map에서 크기가 4인 정사각형을 기준으로 보았을 때, [한 모서리를 기준]으로, 주변에 모두 1이 있다면, 한변의 크기가 2인 정사각형이라고 저장.
         * - 한 모서리를 "오른쪽 하단"이라고 해보자
         *      - 주변에 0 이 존재한다면, 정사각형이 아니므로, 1을 저장
         *      - 주변 모서리에 모두 1이 존재한다면 (1,1,1) , 정사각형이므로, 2를 저장
         *      - 이것을 판단하는 기준! 나중에 더 큰 사각형을 생각했을 때, 주변이 (2,2,2) 가 된다면, 이것은 한변의 크기가 3인 정사각형을 판단하는 것으로 생각할 수 있다.
         *      - (1,2,2) 라면 정사각형이 안되므로,현재까지 최대 크기는 2라고 할 수 있겟다.
         *
         * - 탐색의 핵심! => 크기를 4인 정사각형의 필터가 있다고 생각하고, 주변을 보고, 누적 길이 dp + 1에 넣기!
         *
         * */

        for (int i = 1; i < N + 1; i++) { // 모든 배열을 돌며 체크
            for (int j = 1; j < M + 1; j++) {
                maxSide = Math.max(maxSide, check(i, j)); // check에서 반환한 누적 크기와, 현재 최댓값을 갱신
            }
        }
        System.out.println(maxSide * maxSide);
    }

    public static int check(int i, int j) {
        if (i == 0 || j == 0) { // 종료 조건
            return 0;
        }

        if (dp[i][j] != -1) { // 탐색하지 않은 초기값 -1 이 아니라면 탐색이 이미 완료된 것이니 그대로 반환
            return dp[i][j];
        }

        dp[i][j] = map[i][j]; // 이걸 지나가면, 탐색!

        if (dp[i][j] == 0) return dp[i][j]; // 0이 들어간 정사각형은 X

        // 현재 위치를 기준으로, 왼쪽 : [i][j - 1], 대각선 왼쪽위 : [i - 1][j - 1] 위 : [i - 1][j] 를 탐색
        int temp = Math.min(check(i, j - 1), check(i - 1, j - 1));
        dp[i][j] = Math.min(temp, check(i - 1, j)) + 1;

        return dp[i][j];
    }
}
