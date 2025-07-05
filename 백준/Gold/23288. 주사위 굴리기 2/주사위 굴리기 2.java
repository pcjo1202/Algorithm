import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, result, direction;
    static int[][] board;
    static int[] di = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dj = {1, 0, -1, 0}; //
    static Dice dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 이동 횟수

        board = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new Dice();
        result = 0;
        direction = 0; // 이동 방향
        Point cur = new Point(1, 1); // 현재 위치

        // 게임 진행
        for (int i = 0; i < K; i++) {
            // 1. 주사위 굴리기
            cur = move(cur);
            dice.rolling(direction);

            // 2. 점수 획득
            result += scoreCalculation(cur);

            // 3. 다음 이동방향 결정
            changeDirection(cur);
        }

        System.out.println(result);
    }

    // 이동 방향으로 이동하기
    static Point move(Point cur) {
        int ni = cur.i + di[direction];
        int nj = cur.j + dj[direction];

        // 이동 방향 반대로 바꾸기
        if (ni <= 0 || ni > N || nj <= 0 || nj > M) {
            direction = (direction + 2) % 4; // 반대로

            ni = cur.i + di[direction];
            nj = cur.j + dj[direction];
        }

        return new Point(ni, nj);
    }

    // 이동 방향 결정
    static void changeDirection(Point cur) {
        int diceScore = dice.bottom();
        int boardScore = board[cur.i][cur.j];

        if (diceScore > boardScore) {
            // 시계방향 회전
            direction = (direction + 1) % 4;
        } else if (diceScore < boardScore) {
            // 반시계 방향 회전
            direction = (direction - 1 + 4) % 4;
        }
    }

    // 점수 계산
    static int scoreCalculation(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];
        queue.offer(start);
        visited[start.i][start.j] = true;

        int count = 1;
        int target = board[start.i][start.j];

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni <= 0 || ni > N || nj <= 0 || nj > M) continue;
                if (visited[ni][nj]) continue;

                if (board[ni][nj] == target) {
                    queue.offer(new Point(ni, nj));
                    visited[ni][nj] = true;
                    count++;
                }
            }
        }

        return count * target;
    }

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // 주사위 정보
    static class Dice {
        int[][] dice;

        public Dice() {
            dice = new int[][]{
                    {0, 2, 0},
                    {4, 1, 3}, // (1,1)이 윗면
                    {0, 5, 0},
                    {0, 6, 0}
            };
        }

        // 주사위 굴리기
        void rolling(int direction) {
            // 0: 오른쪽, 1 : 남쪽, 2: 서쪽, 3: 북
            int temp = 0;

            switch (direction) {
                case 0: // dice[1][] -> 오른쪽으로 밀기
                    temp = dice[1][2];
                    dice[1][2] = dice[1][1];
                    dice[1][1] = dice[1][0];
                    dice[1][0] = temp;

                    temp = dice[3][1];
                    dice[3][1] = dice[1][0];
                    dice[1][0] = temp;
                    break;
                case 1: // dice[][1] -> 아래로 밀기
                    temp = dice[3][1];
                    dice[3][1] = dice[2][1];
                    dice[2][1] = dice[1][1];
                    dice[1][1] = dice[0][1];
                    dice[0][1] = temp;
                    break;
                case 2: // dice[1][] -> 왼쪽으로 밀기
                    temp = dice[1][0];
                    dice[1][0] = dice[1][1];
                    dice[1][1] = dice[1][2];
                    dice[1][2] = temp;

                    temp = dice[3][1];
                    dice[3][1] = dice[1][2];
                    dice[1][2] = temp;
                    break;
                case 3: // dice[][1] -> 위로 밀기
                    temp = dice[0][1];
                    dice[0][1] = dice[1][1];
                    dice[1][1] = dice[2][1];
                    dice[2][1] = dice[3][1];
                    dice[3][1] = temp;
                    break;
            }
        }

        int top() {
            return dice[1][1];
        }

        int bottom() {
            return dice[3][1];
        }

    }
}
