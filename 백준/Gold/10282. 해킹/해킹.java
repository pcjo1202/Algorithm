import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, D, C;
    static int[] distance;
    static List<ArrayList<Depend>> dependencyList;

    static class Depend {
        int id, s;

        public Depend(int a, int s) {
            this.id = a;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            input();
            dijkstra();

            int count = 0, time = 0;

            for (int i = 1; i < N + 1; i++) {
                if (distance[i] == Integer.MAX_VALUE) continue;
                count++;
                time = Math.max(time, distance[i]);
            }

            System.out.println(count + " " + time);
        }
    }

    static void dijkstra() {
        distance = new int[N + 1]; // 최단거리 배열
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[C] = 0; // 처음 시작점

        PriorityQueue<Depend> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.s, o2.s));
        pq.offer(new Depend(C, 0));


        while (!pq.isEmpty()) {
            Depend cur = pq.poll();

            if (cur.s > distance[cur.id]) continue;

            for (Depend next : dependencyList.get(cur.id)) {
                if (next.s + cur.s < distance[next.id]) {
                    distance[next.id] = next.s + cur.s;
                    pq.offer(new Depend(next.id, next.s + cur.s));
                }
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
        D = Integer.parseInt(st.nextToken()); // 의존성 개수
        C = Integer.parseInt(st.nextToken()); // 해킹 당한 컴퓨터 번호

        dependencyList = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            dependencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            // b -> a , b 감염 s초 후 a 감염
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            dependencyList.get(b).add(new Depend(a, s));
        }
    }
}