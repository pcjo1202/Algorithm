import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] map, temp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];


        // 인구수 저장하는 맵
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MAX_VALUE;

        // X, Y, d1, d2 구하기
        for (int X = 1; X <= N; X++) {
            for (int Y = 1; Y <= N; Y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        // X, Y, d1, d2 뽑기
                        if (X + d1 + d2 <= N && 1 <= Y - d1 && Y + d2 <= N) {
                            result = Math.min(result, calculator(X, Y, d1, d2));
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    static int calculator(int X, int Y, int d1, int d2) {
        temp = new int[N + 1][N + 1];
        
        // 1. 5번 선거구 의 경계값 구하기
        for (int i = 0; i <= d1; i++) {
            temp[X + i][Y - i] = 5; // 왼쪽 대각선 아래
            temp[X + d2 + i][Y + d2 - i] = 5; // 오른쪽 대각선 아래에서 꺽여, 왼쪽 대각선 아래로
        }

        for (int i = 0; i <= d2; i++) {
            temp[X + i][Y + i] = 5; // 오른쪽 대각선 아래
            temp[X + d1 + i][Y - d1 + i] = 5; // 왼쪽 대각선 아래에서 꺾여, 오른쪽 대각선 아래로
        }

        // 2. 경계값 내부의 선거구 5번 선거구로 바꾸기
        for (int i = X + 1; i < X + d1 + d2; i++) {
            boolean flag = false;
            for (int j = 1; j <= N; j++) {
                if (temp[i][j] == 5) flag = !flag; // 처음 5번 구역을 만나면 true, 두번째 5번 구역을 만나면 false
                if (flag) temp[i][j] = 5;
            }
        }


        // 3. 나머지 선거구 구하기
        // 각각의 선거구의 인원 구하기
        int[] nums = new int[6]; // 각각의 선거구 인원수 저장할 배열 (1base)
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                // 각각의 선거구 구하고, 인원수 저장
                if (temp[r][c] == 5) {
                    nums[5] += map[r][c]; // 5번 선거구
                } else if (r < X + d1 && c <= Y) {
                    nums[1] += map[r][c]; // 1번 선거구
                    temp[r][c] = 1;
                } else if (r <= X + d2 && Y < c) {
                    nums[2] += map[r][c]; // 2번 선거구
                    temp[r][c] = 2;
                } else if (X + d1 <= r && c < Y - d1 + d2) {
                    nums[3] += map[r][c]; // 3번 선거구
                    temp[r][c] = 3;
                } else if (X + d2 < r && Y - d1 + d2 <= c) {
                    nums[4] += map[r][c]; // 4번 선거구
                    temp[r][c] = 4;
                }
            }
        }

        Arrays.sort(nums);

        int min = nums[1]; // 0 은 0이므로, 1이 가장 작은 수
        int max = nums[5]; // 가장 뒤에 있는게 가장 큰 수

        return max - min;
    }
}