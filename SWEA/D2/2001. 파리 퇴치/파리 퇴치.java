import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] board;
    static boolean[][] visited;
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};
    static int N, M, max = 0;

    static void dfs(int i, int j){
        // 기저 조건
        if(i == N -2 && j == M -2){
            return;
        }

        // 해야할 일 : M * M 사이에 있는 사각형들의 합을 구해야함
        visited[i][j] = true;
        int subSum = 0;
        for(int n = 0; n < M; n++){
            for(int m = 0; m < M; m++){
                subSum += board[i+n][j+m];
            }
        }

        max = Math.max(max, subSum);

        // 탐색
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if( 0 > ni || ni + M -1 > N -1  || 0 > nj || nj + M -1 > N -1 || visited[ni][nj]) continue;
            dfs(ni, nj);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            max = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);

            sb.append("#").append(test_case).append(" ").append(max).append('\n');
        }
        System.out.println(sb);
    }
}