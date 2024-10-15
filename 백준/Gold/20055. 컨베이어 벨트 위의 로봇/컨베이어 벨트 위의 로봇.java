import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, size, zeroBelt, roundCnt;
    static int[] arr;
    static boolean[] robotState;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 칸의 개수
        K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수가 K개가 되면 종료

        size = 2 * N + 1;
        arr = new int[size];// 내구도 저장 (1번 ~ 2N 번)
        robotState = new boolean[size]; // 컨테이너 안에서 로봇의 위치

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        // 앞쪽에서

        roundCnt = 0; // 단계
        zeroBelt = 0; // 내구도가 0인 칸의 개수
        while (true) {
            ++roundCnt; // 새로운 라운드
            // 컨테이너 이동시키기
            moveBelt();

            // 로봇 이동시키기
            if (!moveRobot()) {
                break;
            }

            // 올리는 위치 체크
            if (arr[1] > 0) {
                robotState[1] = true;
                arr[1]--; // 내구도 감소

                if (arr[1] == 0) { // 내구도 0이 되면 카운트
                    zeroBelt++;

                    // 종료 조건
                    if (zeroBelt >= K) {
                        break;
                    }
                }
            }


        }

        System.out.println(roundCnt);
    }

    static void moveBelt() {
        // 컨테이너 이동시키기
        // 1. 내구도 이동시키기
        for (int i = size - 1; i >= 0; i--) {
            // 1. 내구도 이동시키기
            int newidx = (i + 1) % size;
            arr[newidx] = arr[i];
            arr[i] = 0;

            // 로봇 이동시키기
            if (i < N) {
                robotState[newidx] = robotState[i];
                robotState[i] = false;
            }
        }

        // 내리는 곳에 도착했는지 체크
        if (robotState[N]) {
            robotState[N] = false;
        }
    }

    static boolean moveRobot() {
        for (int i = N - 1; i >= 0; i--) {
            if (robotState[i]) {
                boolean nextRobot = robotState[i + 1]; // 다음 칸

                if (!nextRobot & arr[i + 1] > 0) { // 이동하는 칸 로봇 X, 내구도 1 이상 -> 이동 & 내구도 감소
                    robotState[i + 1] = true;
                    robotState[i] = false;
                    arr[i + 1]--; // 내구도 감소

                    if (arr[i + 1] == 0) { // 내구도 0이 되면 카운트
                        zeroBelt++;

                        // 종료 조건
                        if (zeroBelt >= K) {
                            return false;
                        }
                    }
                }
            }
        }

        // 내리는 곳에 도착했는지 체크
        if (robotState[N]) {
            robotState[N] = false;
        }

        return true;
    }
}