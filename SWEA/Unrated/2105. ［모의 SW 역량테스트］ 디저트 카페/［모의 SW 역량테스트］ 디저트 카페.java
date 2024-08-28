import java.util.*;
import java.io.*;

public class Solution {

    static int answer;
    static int size;
    static int[][] map;
    // 우하, 좌하, 좌상, 우상 방향을 나타내는 배열
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {1, 1, -1, -1};
    // 디저트를 먹었는지 여부를 기록하는 배열
    static boolean[] visited;
    // 출발점을 저장
    static int startX, startY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int test_case = 1; test_case <= T; test_case++) {
            size = Integer.parseInt(br.readLine()); // 맵의 크기
            map = new int[size][size];
            answer = -1; // 결과값 초기화
            for (int i = 0; i < size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가능한 모든 시작점을 탐색
            for (int i = 0; i < size - 2; i++) {
                for (int j = 1; j < size - 1; j++) {
                    startX = j;
                    startY = i;
                    visited = new boolean[101]; // 디저트 번호는 1~100
                    visited[map[i][j]] = true; // 시작점 디저트 방문 처리
                    dfs(j, i, j, i, 1, 0); // DFS 탐색 시작
                }
            }

            // 결과 출력
            sb.append(String.format("#%d %d\n", test_case, answer));
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y, int preX, int preY, int cnt, int start) {
        for (int i = start; i < 4; i++) {
            int mx = x + dx[i];
            int my = y + dy[i];

            // 인덱스 범위 초과 방지
            if (mx < 0 || my < 0 || mx >= size || my >= size) continue;
            // 직전에 방문한 곳으로 돌아가는 것 방지
            if (preX == mx && preY == my) continue;

            // 출발점으로 돌아온 경우
            if (startX == mx && startY == my) {
                answer = Math.max(cnt, answer); // 최대 경로 길이 갱신
                return;
            }
            // 이미 방문한 디저트 카페는 다시 방문하지 않음
            if (visited[map[my][mx]]) continue;

            // 방문 처리
            visited[map[my][mx]] = true;
            dfs(mx, my, x, y, cnt + 1, i); // 재귀 호출로 탐색 진행
            visited[map[my][mx]] = false; // 백트래킹을 위한 방문 해제
        }
    }
}