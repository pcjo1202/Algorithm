import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] result;
    static Building[] buildings;

    static class Building {
        int time;
        List<Integer> list;

        public Building(int time, List<Integer> list) {
            this.time = time;
            this.list = list;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        buildings = new Building[N + 1];
        result = new int[N + 1];


        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            buildings[i] = new Building(time, new ArrayList<>());

            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                if (a == -1) break;
                buildings[i].list.add(a);
            }
        }


        for (int i = 1; i < N + 1; i++) {
            result[i] = dfs(i);
        }

        // 출력
        for (int i = 1; i < N + 1; i++) {
            System.out.println(result[i]);
        }
    }

    static int dfs(int num) {
        if (result[num] != 0) return result[num];

        Building target = buildings[num];
        // 먼저 지어야 할 빌딩이 없으면 종료
        if (target.list.isEmpty()) {
            result[num] = target.time;
            return result[num];
        }

        int max = 0;
        for (int next : target.list) {
            // 먼저 지어야하 하는 건물 중에서 가장 오래 걸리는 시간
            max = Math.max(max, dfs(next));
        }

        result[num] = max + target.time;

        return result[num];
    }
}
