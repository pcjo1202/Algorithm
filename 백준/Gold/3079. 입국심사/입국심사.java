import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long M, result;
    static long[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 입국 심사대 수
        M = Long.parseLong(st.nextToken()); // 친구 수

        times = new long[N];

        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        // 어떤 심사대를 이용하던 최소 시간으로 할 수 있다면, 이용하지 않아도 되는 심사대가..?
        // 가장 오래 걸리는 시간은 times에서 가장 오래걸리는 심사대에 모든 사람이 이용
        // 가장 적게 걸리는 시간은..?
        // 주어진 심사대를 이용하는 각각의 사람 수를 찾기..?

        Arrays.sort(times); // 제일 적게 걸리는 시간을 확인해야하므로, 시간이 적게 걸리는 것들부터 확인해보자

        long min = 0;
        long max = times[N - 1] * M; // M명 모두가 제일 오래 걸리는 심사대를 이용할 경우가 제일 오래걸림
        result = max;

        while (min <= max) {
            long mid = (min + max) / 2; // 중간 시간 구하기

            long totalM = 0; // 각 심사대에서 이용가능한 인원수의 합

            for (long time : times) {
                long curM = mid / time; // 현재 심사대를 최대로 사용하는 인원 수
                totalM += curM;

                // 문제에서 주어진 인원보다 커지거나, 같을 때 break
                if (totalM >= M) break;
            }

            if (totalM >= M) {
                // 현재 탐색중인 Mid 시간을 통해 구한 인원수 >= 주어진 인원
                // 시간이 남아서 수용 가능한 인원이 있다는 것이므로, 더 짧은 시간을 구할 수 있다.
                max = mid - 1;
                result = Math.min(result, mid);
            } else {
                // 현재 탐색중인 Mid 시간을 통해 구한 인원수 < 주어진 인원
                // 부족하다는 것이니 더 큰 시간을 찾아야 한다.
                min = mid + 1;
            }
        }

        System.out.println(result);
    }
}