import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] di = {0, -1, 0, 1}; // 우 상 좌 하
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        // 전체 드래콘 커브를 그릴 맵
        map = new int[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direct = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());

            // 1. 각각의 드래곤 커브를 구하고, 맵에 그린다.
            dragon(x, y, direct, gen);
        }

        // 2. 맵에서 정사각형을 뽑아낸다.
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    // 드래곤 커브를 구하고 맵에 그림
    static void dragon(int x, int y, int d, int gen) {
        List<int[]> list = new ArrayList<>();

        list.add(new int[]{y, x});
        list.add(new int[]{y + di[d], x + dj[d]}); // 0 세대 드래곤 커브

        // 세대 수 만큼 반복
        for (int i = 0; i < gen; i++) {
            // 직전 좌표의 끝점을 기준으로 시계방향으로 회전 -> list의 가장 마지막 지점
            int endi = list.get(list.size() - 1)[0];
            int endj = list.get(list.size() - 1)[1];

            /*
            끝점 좌표를 기준으로
            * */

            // 끝부터 거꾸로 이전세대의 드래곤 커브를 따라가면서, 다음 세대의 드래곤 커브를 그림
            for (int j = list.size() - 2; j >= 0; j--) {
                int curi = list.get(j)[0];
                int curj = list.get(j)[1];

                int diff_i = endi - curi;
                int diff_j = endj - curj;

                list.add(new int[]{endi - diff_j, endj + diff_i});
            }
        }

        // 맵에 드래곤 커브 표시
        for (int[] temp : list) {
            map[temp[0]][temp[1]] = 1;
        }
    }
}