import java.io.*;
import java.util.*;

public class Main {
    static int N, MAX_NUM, result;
    static int[][] board, tempBoard;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        MAX_NUM = 5; // 최대 수행 횟 수
        result = 0;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        tempBoard = new int[N][N]; // 보드 저장

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                tempBoard[i][j] = board[i][j];

                result = Math.max(result, board[i][j]); // 현재 최대 값 구하기
            }
        }

        // 1. 상하좌우 움직일 수 있는 5번의 경우의 수 구하기 -> 4^5개
        combination(0, new int[MAX_NUM]);

        // 2. 구한 조합대로 시뮬레이션 구현
        //  모든 방향으로 이동시키는 것을 오른쪽으로 미는 것으로 바꾸자.
        // - 위쪽으로 밀기 => 오른쪽으로 1번 회전
        // - 아래쪽으로 밀기 => 오른쪽으로 3번 회전
        // - 왼쪽으로 밀기 => 배열 오른쪽으로 두번 회전
        // - 오른쪽으로 밀기 => 배열 그대로

        // 3. 시뮬레이션의 결과를 저장 후 max 값 구하기

        System.out.println(result);
    }

    static void rotateArr(int count) { // 오른쯕으로 배열 회전
        for (int k = 0; k < count; k++) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp[j][N - 1 - i] = board[i][j];
                }
            }
            board = temp; // 회전한 배열로 바꾸기
        }

    }


    static void simulation(int[] arr) {
        for (int i = 0; i < MAX_NUM; i++) {
            // 1. 배열 회전 시키기
            int d = arr[i]; // 현재 밀고자하는 방향

            // 상 우 하 좌
            // 0 1 2 3
            if (d == 0) { // 위로 밀때 -> 1번 회전
                rotateArr(1);
            } else if (d == 2) { // 아래로 밀때 -> 3번 회전
                rotateArr(3);
            } else if (d == 3) { // 좌로 밀때 -> 2번 회전
                rotateArr(2);
            }

            // 2. 회전 시킨 배열 오른쪽으로 밀기
            goMove();

        }

    }

    static void goMove() { // 오른쪽으로 밀기
        for (int i = 0; i < N; i++) {
            // 해당 열의 가장 오른쪽 요소부터 밀기
            // 1. 모든 요소 일단 오른쪽 끝으로 빈 공간 없이 밀기
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int idx = j + 1;

                    int value = board[i][j]; // 이동할 요소의 값

                    while (idx < N) {
                        if (board[i][idx] == 0) { // 다음 요소가 비어 있을 때
                            board[i][idx] = value;
                            board[i][idx - 1] = 0;
                        }
                        idx++;
                    }
                }
            }

            // 2. 오른쪽부터 확인해서 같은 숫자가 연속 되면 합치기
            for (int j = N - 1; j > 0; j--) {
                if (board[i][j] != 0) {
                    int cur = board[i][j]; //오른쪽 값
                    int prev = board[i][j - 1]; // cur의 바로 왼쪽 값

                    if (cur == prev) {
                        board[i][j] *= 2;
                        board[i][j - 1] = 0;
                        result = Math.max(result, board[i][j]);
                    }
                }
            }

            // 3. 옮기고 빈 공간이 생겼다면 다시 옆으로 밀기
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int idx = j + 1;

                    int value = board[i][j]; // 이동할 요소의 값

                    while (idx < N) {
                        if (board[i][idx] == 0) { // 다음 요소가 비어 있을 때
                            board[i][idx] = value;
                            board[i][idx - 1] = 0;
                        }
                        idx++;
                    }
                }
            }
        }
    }

    static void combination(int depth, int[] arr) {
        // 기저 조건
        if (depth == MAX_NUM) {
            // 배열 복사 (기본값으로 초기화)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = tempBoard[i][j];
                }
            }

            simulation(arr); // 해당 조합으로 시뮬레이션
            return;
        }

        // 조합 뽑기
        for (int d = 0; d < 4; d++) {
            arr[depth] = d;
            combination(depth + 1, arr);
        }

    }
}