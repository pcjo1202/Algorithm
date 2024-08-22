import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, max, roomNum;
    static int[][] board;
    static int[] di = {-1 ,1, 0, 0}; // 상 하 좌 우
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T ; test_case++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            roomNum = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j]) continue;
                    dfs(i, j, 1);
                }
            }
            sb.append(String.format("#%d %d %d\n", test_case, roomNum, max));
        }
        System.out.println(sb);
    }
    static int dfs(int i, int j, int cnt){
        // 탐색 조건
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if(ni < 0|| ni >= N || nj < 0 || nj >= N) continue; // 벽을 만나면 컨티뉴
            if(board[ni][nj] - board[i][j] != 1) continue;

            // 현재와 다음 탐색 위치가 현재 위치와 1차이가 난다면
            cnt = dfs(ni, nj, cnt + 1);

            if(cnt > max){ // 최대 횟수가 갱신된다면 roomNum 최소값 갱신
                roomNum = board[i][j];
                max = cnt;
            }
            if(cnt == max){
                roomNum = Math.min(roomNum, board[i][j]);
            }
        }
        return cnt;
    }
}