import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Work[] list;

    static class Work {
        int d, w;

        public Work(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Work{" +
                    "d=" + d +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        list = new Work[N];

        // 점수가 가장 큰 것부터 꺼내기
        PriorityQueue<Work> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o2.w, o1.w);
        });

        int maxDay = 0; // 가장 마지막 날
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 마감일까지 남은 일수
            int w = Integer.parseInt(st.nextToken()); //

            list[i] = new Work(d, w);
            maxDay = Math.max(maxDay, d);
        }

        int sum = 0;
        //얻을 수 있는 점수의 최댓값
        // 마감일이 가장 늦은 것 순서대로 pq에 넣음
        int day = maxDay;
        while (day >= 1) {
            for (int i = 0; i < N; i++) {
                Work temp = list[i];
                if (temp.d == day) {
                    pq.offer(temp);
                    list[i].d = -1; // 이미 큐에 넣은거 처리
                }
            }

            if (!pq.isEmpty()) {
                sum += pq.poll().w; // 가장 점수 높은 과제 수행
            }

            day--;
        }

        System.out.println(sum);
    }
}
