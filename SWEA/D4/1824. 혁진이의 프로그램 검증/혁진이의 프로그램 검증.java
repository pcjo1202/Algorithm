import java.io.*;
import java.util.*;

public class Solution {
    static int R, C, memory;
    static boolean result;
    static boolean[][][][] visited;
    static char[][] map;
    static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            result = false;
            memory = 0;
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R][C];
            boolean end = false;

            for (int i = 0; i < R; i++) {
                String str = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') end = true;
                }
            }
            visited = new boolean[R][C][4][16];

            if (end) {
                dfs(0, 0, 3);
            }

            sb.append(String.format("#%d %s\n", tc, result ? "YES" : "NO"));
        }
        System.out.println(sb);
    }

    static void dfs(int i, int j, int dir) {
        if (result) return; // 이미 찾은 경우 재귀 중단

        // 범위 체크
        if (i < 0) i = R - 1;
        else if (i >= R) i = 0;
        else if (j < 0) j = C - 1;
        else if (j >= C) j = 0;

        // 종료 조건 : 정지하지 못할 때 (해당 위치로 같은 숫자가 돌아왔을 때)
        if (visited[i][j][dir][memory]) return;

        visited[i][j][dir][memory] = true;

        char command = map[i][j]; // 현재 명령어

        switch (command) {
            case '<':
                dir = 2;
                break;//좌2
            case '>':
                dir = 3;
                break;//우3
            case '^':
                dir = 0;
                break;//상0
            case 'v':
                dir = 1;
                break;//하1
            case '_':
                dir = memory == 0 ? 3 : 2;
                break;//우3:좌2
            case '|':
                dir = memory == 0 ? 1 : 0;
                break;//하1:상0
            case '?':
                break;//밑에서 4방으로
            case '.':
                break;
            case '@':
                result = true;
                return;//정답
            case '+':
                memory = (memory == 15 ? 0 : memory + 1);
                break;
            case '-':
                memory = (memory == 0 ? 15 : memory - 1);
                break;
            default:
                memory = map[i][j] - '0';
                break; // '0'~'9' -> int
        }

        if (command == '?') {
            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                dfs(ni, nj, d);
            }
        } else {
            int ni = i + di[dir];
            int nj = j + dj[dir];
            dfs(ni, nj, dir);
        }
    }
}