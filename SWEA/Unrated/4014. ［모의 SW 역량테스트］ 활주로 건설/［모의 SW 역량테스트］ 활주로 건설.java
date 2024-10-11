import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;
            // 행 검사
            for (int i = 0; i < N; i++) {
                if (canBuildRunway(map[i], X, N)) {
                    result++;
                }
            }

            // 열 검사
            for (int j = 0; j < N; j++) {
                int[] column = new int[N];
                for (int i = 0; i < N; i++) {
                    column[i] = map[i][j];
                }
                if (canBuildRunway(column, X, N)) {
                    result++;
                }
            }

            System.out.println("#" + t + " " + result);
        }
    }

    static boolean canBuildRunway(int[] line, int X, int N) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) {
                // 높이가 같은 경우 계속 진행
                continue;
            } else if (line[i] + 1 == line[i + 1]) {
                // 올라가는 경사로 설치 가능 여부 체크
                // 경사로를 설치할 수 있는 경우
                // 높이 차이가 1인 경우만 가능
                // 올라가는 경사로의 경우 현재 위치에서 X칸만큼 뒤로 검사
                // 내려가는 경사로의 경우 현재 위치에서 X칸만큼 앞으로 검사
                // 설치할 위치에 이미 다른 경사로가 있거나, 높이가 맞지 않으면 설치할 수 없음
                for (int j = 0; j < X; j++) {
                    if (i - j < 0 || visited[i - j] || line[i] != line[i - j]) {
                        return false;
                    }
                    visited[i - j] = true;
                }
            } else if (line[i] - 1 == line[i + 1]) {
                // 내려가는 경사로 설치 가능 여부 체크
                for (int j = 1; j <= X; j++) {
                    if (i + j >= N || visited[i + j] || line[i + 1] != line[i + j]) {
                        return false;
                    }
                    visited[i + j] = true;
                }
            } else {
                // 높이 차이가 1보다 크면 활주로 설치 불가능
                return false;
            }
        }
        return true;
    }
}