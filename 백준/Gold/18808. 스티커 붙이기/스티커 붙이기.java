import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int N, M, K, result;
    static int[][] board;
    static List<Sticker> stickers;

    static class Sticker {
        int n, m;
        int[][] map;

        public Sticker(int n, int m) throws Exception {
            this.n = n;
            this.m = m;
            this.map = new int[n][m];
            setMap();
        }

        public void setMap() throws Exception {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    this.map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // map 90도 회전
        public void rotateMap() {
            int[][] tempMap;
            int tn = n, tm = m;

            // 직사각형 일 때, 세로, 가로 크기 변환
            if (n != m) {
                int temp = tn;
                tn = tm;
                tm = temp;
            }
            tempMap = new int[tn][tm];

            // 돌리기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tempMap[j][n - i - 1] = this.map[i][j];
                }
            }

            // 돌린 맵으로 전환
            this.n = tn;
            this.m = tm;
            this.map = tempMap;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            for (int[] arr : map) {
                str.append(Arrays.toString(arr)).append("\n");
            }
            return str.toString();
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 스티커 개수

        stickers = new ArrayList<>(); // 스티커 목록

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 스티커 세로
            int b = Integer.parseInt(st.nextToken()); // 스티커 가로

            stickers.add(new Sticker(a, b));
        }


        board = new int[N][M]; // 스티커를 붙힐 노트북
        result = 0; // 노트북에 붙힌 스티커의 수

        // 각 스티커를 붙힐 때 붙힐 수 있는 곳을 모두 완전 탐색으로 확인
        dfs(0);

        // 답
        System.out.println(result);
    }

    static void dfs(int cnt) { // cnt: 붙힌 cnt의 수
        // 기저조건
        if (cnt == K) { // 스티커를 다 붙히면 종료
            int count = 0;
            // 보드에 스티커가 붙은 개수 확인
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 1) {
                        count++;
                    }
                }
            }
            result = count;
            return;
        }
        // 이미 붙어있는 친구 중 가능한 경우의 수를 모두 탐색
        Sticker cur = stickers.get(cnt); // 현재 붙히려고 하는 스티커
        // 탐색 조건 : 스티커를 붙힐 수 있을 때
        // 스티커가 붙을 수 있는 경우의 수
        // 0. 스티커의 크기가 맞는지 확인 (안맞으면 회전)

        boolean flag = false; // 회전시킬지 말지 확인할 flag

        // 보드의 가로, 세로 크기가 안맞으면 바로 회전 (노트북을 벗어남)
        if (cur.n > N || cur.m > M) flag = true;

        // 크기가 맞을 때, 전체 보드를 회전 안시키고 확인
        if (!flag) {
            // 스티커를 붙힐 수 있다면, 보드에 그리기
            //        붙힐 수 없다면, 회전
            boolean isAdd = false;
            out:
            for (int i = 0; i < N - cur.n + 1; i++) {
                for (int j = 0; j < M - cur.m + 1; j++) {
                    if (check(i, j, cur)) { // 붙힐 수 있다면, 보드에 추가
                        addSticker(i, j, cur);
                        isAdd = true;
                        break out; // 붙히면 다음꺼 확인할 필요 없음
                    }
                }
            }

            if (!isAdd) { // 추가하지 못했다면 회전 시키기
                flag = true;
            }
        }

        // 회전해야 한다면 회전 시키기
        if (flag) {
            out:
            for (int d = 0; d < 4; d++) {
                // 회전시키기
                cur.rotateMap();
                // 보드 확인
                for (int i = 0; i < N - cur.n + 1; i++) {
                    for (int j = 0; j < M - cur.m + 1; j++) {
                        if (check(i, j, cur)) { // 붙힐 수 있다면, 보드에 추가
                            addSticker(i, j, cur);
                            break out; // 붙히면 다음꺼 확인할 필요 없음
                        }
                    }
                }
            }
        }

        dfs(cnt + 1); // 다음 스티커로 넘어감
    }

    static boolean check(int i, int j, Sticker cur) {
        // 현재 스티커의 크기 만큼을 탐색
        sticker:
        for (int k = 0; k < cur.n; k++) {
            for (int l = 0; l < cur.m; l++) {
                // 스티커를 붙이려고 하는 자리에 이미 스티커가 붙어 있을 때, false
                if (cur.map[k][l] == 1 && board[i + k][j + l] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void addSticker(int i, int j, Sticker cur) {
        for (int k = 0; k < cur.n; k++) {
            for (int l = 0; l < cur.m; l++) {
                // 스티커의 모양 처럼 붙힘
                if (cur.map[k][l] == 1) {
                    board[i + k][j + l] = 1;
                }
            }
        }
    }
}