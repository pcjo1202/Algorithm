import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21,716 kb ,119 ms
public class Solution {
    static int N, tree[], targetHeight;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            tree = new int[N]; // 나무들의 높이 저장 
            targetHeight = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                targetHeight = Math.max(tree[i], targetHeight); // 키가 가장 큰 나무 찾기 
            }
            System.out.println("#" + tc + " " + grow());
        }
    }

    public static int grow() {
        int total = 0; // 모든 나무가 총 자라야하는 크기
        int oddCnt = 0, res = 0;
        for (int i = 0; i < N; i++) {
            int diff = targetHeight - tree[i];
            if (diff % 2 == 1) oddCnt++;

            total += diff;
        }

        if (3 * (oddCnt - 1) + 1 >= total) { // 1씩 물을 줘야하는 날이 많은 경우가 됨 
            // 홀수 일자만큼 1 물주기를 할 수 있는 최소한의 일자 2*oddCnt-1.
            // 이날 까지 사이사이 2물주기를 모두 한다면 자라는 나무의 최대길이는 3*(oddCnt-1) + 1
            // 사이사이 2의 물을 모두 준다면 필요한 나무 성장량을 커버할 수 있다.
            res = 2 * oddCnt - 1;
        } else { // 1씩 물을 줘야하는 날과 2씩 물을 줘야하는 날이 같거나 2씩 물을 줘야하는 날이 많은 경우 
            // 매일 매일 물을 주고, 모자란 만큼 물을 더 준다.
            // 1이 모자라다 = 다음(홀수)날 바로 물을 준다 => 1
            // 2가 모자라다 = 다음(홀수) 쉬고 이튿날 물을 준다. => 2
            res = 2 * (total / 3) + total % 3;
        }
        return res;
    }
}