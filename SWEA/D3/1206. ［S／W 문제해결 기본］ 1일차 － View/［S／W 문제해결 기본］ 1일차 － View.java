import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, result;
    static int[] building;
    static int[] di = {-1, 1, -2, 2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

//        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= 10; tc++) {
            N = Integer.parseInt(br.readLine());
            building = new int[N];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                building[i] = Integer.parseInt(st.nextToken());
            }

            // 좌우 2개의 빌딩 체크
            for (int i = 2; i < N - 2; i++) {
                int cur = building[i]; // 확인 해보고자 하는 빌딩

                boolean flag = true;
                // 좌우 가능한지 체크
                int num = 255;
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];

//                    if (ni < 0 || ni >= N) continue;
                    int next = building[ni];

                    int target = cur - next;

                    // 좌우 높이 크기가 같거나, cur 보다 높을 때
                    if (target <= 0) {
                        flag = false;
                        break;
                    } else {
                        // 좌우 높이보다 cur이 높을 때,
                        num = Math.min(num, target);
                    }
                }
                if (flag) {
                    result += num;
                }
            }

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

}