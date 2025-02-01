import java.util.*;
import java.io.*;

public class Main {
    static int R, C, T;
    static int[][] map, tempMap;
    static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dj = {0, 0, -1, 1};
    static Node[] machine;

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken()); // 시간

        machine = new Node[2]; // [0] 위쪽, [1] 아래쪽

        map = new int[R][C];

        int Idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 로봇의 위치 저장
                if (map[i][j] == -1) {
                    machine[Idx++] = new Node(i, j);
                }
            }
        }


        // T초간 진행
        for (int i = 0; i < T; i++) {
            // 1. 미세먼지 확산 (동시에 일어남)
            spread();
            // 2. 공기 청정기 작동
            air();
        }

        int result = 0;

        // T초가 지난 후 방에 남아있는 미세먼지의 양
        for (int[] arr : map) {
            for (int res : arr) {
                if (res == -1) continue;
                result += res;
            }
        }

//        for (int[] arr : map) {
//            System.out.println(Arrays.toString(arr));
//        }
        System.out.println(result);
    }

    static void spread() {
        tempMap = new int[R][C];

        for (Node a : machine) tempMap[a.i][a.j] = -1; // 공기청정기 위치 유지

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] < 5) {
                    tempMap[i][j] += map[i][j];
                    continue;
                }

                int temp = map[i][j] / 5;
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d], nj = j + dj[d];
                    if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == -1) continue;

                    tempMap[ni][nj] += temp;
                    cnt++;
                }
                tempMap[i][j] += map[i][j] - (temp * cnt);
            }
        }

        // 배열 복사
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }

    static void air() {
        Node up = machine[0]; // 반시계 방향 (우, 상, 좌, 하) -> (a , 0)
        Node down = machine[1]; // 시계 방향 (우, 하, 좌, 상) -> (b , 0)

        // 위쪽 순환
        // 아래로
        for (int i = up.i; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // 왼쪽으로
        for (int i = 1; i < C; i++) {
            map[0][i - 1] = map[0][i];
        }
        // 위로
        for (int i = 1; i <= up.i; i++) {
            map[i - 1][C - 1] = map[i][C - 1];
        }
        // 오른쪽으로
        for (int i = C - 2; i >= 0; i--) {
            map[up.i][i + 1] = map[up.i][i];
        }

        map[up.i][1] = 0;


        // 아래쪽 순환
        // 위로
        for (int i = down.i + 1; i < R; i++) {
            map[i - 1][0] = map[i][0];
        }
        // 왼쪽으로
        for (int i = 1; i < C; i++) {
            map[R - 1][i - 1] = map[R - 1][i];
        }

        // 아래로
        for (int i = R - 1; i > down.i; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        // 오른쪽으로
        for (int i = C - 2; i >= 0; i--) {
            map[down.i][i + 1] = map[down.i][i];
        }

        map[down.i][1] = 0;

        // 공기 청정기 다시 -1
        map[up.i][up.j] = -1;
        map[down.i][down.j] = -1;
    }

}