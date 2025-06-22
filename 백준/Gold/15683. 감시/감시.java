import java.io.*;
import java.util.*;

public class Main {
    static int N, M, min;
    static int[][] board;
    static int[] visited;
    static int[] di = {-1, 0, 1, 0}; // 0상 1우 2하 3좌
    static int[] dj = {0, 1, 0, -1};
    static List<CCTV> list;

    static class CCTV {
        int i, j, type;

        public CCTV(int i, int j, int type) {
            this.i = i;
            this.j = j;
            this.type = type;
        }
    }

    // CCTV 종류별 방향 조합
    static int[][][] direction = {
            {}, // 0번 없음
            {{0}, {1}, {2}, {3}}, // 1번
            {{0, 2}, {1, 3}}, // 2번
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번
            {{0, 1, 2, 3}} // 5번
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st.nextToken());
                board[i][j] = a;

                if (0 < a && a < 6) {
                    list.add(new CCTV(i, j, a));
                }
            }
        }

        min = Integer.MAX_VALUE;
        visited = new int[list.size()]; // 방향 저장
        dfs(0);

        System.out.println(min);
    }

    static void dfs(int depth) {
        // 기저 조건 : 모든 CCTV를 확인 했을 때
        if (depth == list.size()) {
            // 지도에서 감시 영역 그리기
            int[][] tempMap = copyBoard();

            for (int i = 0; i < list.size(); i++) {
                CCTV cctv = list.get(i);
                int type = cctv.type;
                int dirIndex = visited[i];

                for (int d : direction[type][dirIndex]) {
                    int ni = cctv.i + di[d];
                    int nj = cctv.j + dj[d];

                    while (ni >= 0 && nj >= 0 && ni < N && nj < M) {
                        if (tempMap[ni][nj] == 6) break; // 벽이면 종료
                        if (tempMap[ni][nj] == 0) tempMap[ni][nj] = -1; // 감시 영역 표시
                        ni += di[d];
                        nj += dj[d];
                    }
                }
            }
            // 확인하기.
            min = Math.min(min, count(tempMap));
            return;
        }

        CCTV cctv = list.get(depth);
        int type = cctv.type;

        for (int i = 0; i < direction[type].length; i++) {
            visited[depth] = i;
            dfs(depth + 1);
        }

    }

    static int[][] copyBoard() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, newMap[i], 0, M);
        }
        return newMap;
    }

    static int count(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}
