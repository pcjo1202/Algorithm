import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //                        [북:0, 동:1, 남:2, 서:3]
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static int[][] map;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken()); // 바라보고 있는 방향의 idx

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(startX, startY, startD);
        System.out.println(count);
    }

    public static void dfs(int x, int y, int d) {
        if (map[y][x] == 0) { // 0이라면 청소하고, 카운트 증가
            map[y][x] = 2; // 청소 표시
            count++;
        }

        // 현재 방향 기준으로 왼쪽으로 90도 회전한 방향에 벽이 있는지 없는지 체크 0 -> 3 -> 2 -> 1
        boolean check = false; // 4방향이 모두 청소가 안되어 있다면 false

        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4; // 왼쪽으로 회전
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (map[ny][nx] == 0) {  // 청소하지 않은 빈 공간이 있다면
                dfs(nx, ny, d);  //
                check = true;
                break;
            }
        }

        // 종료 조건
        // 모든 방향을 탐색 했을 때, 후진을 하지 못하면 종료
        // 후진이 가능한지 check
        if (!check) {
            int backY = y - dy[d];
            int backX = x - dx[d];

            if (map[backY][backX] != 1) { // 후진 할 수 있을 때 탐색
                dfs(backX, backY, d);
            } else {
                return;
            }
        }
    }
}
