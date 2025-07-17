import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Class> list, rooms;

    static class Class {
        int S, T;

        public Class(int S, int T) {
            this.S = S;
            this.T = T;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        list = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.S, o2.S));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            Class cur = new Class(s, t);
            list.add(cur);
        }

        // 강의실 종료 시간 과 강의 시작 시간 비교
        rooms = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.T, o2.T));

        // 모든 수업을 한번씩만 순회(시작 시간이 빠른 것부터)
        while (!list.isEmpty()) {
            Class cur = list.poll();

            // 존재하는 강의실 중 재사용 가능 여부 확인
            // 가장 빨리 종료되는 강의실의 종료시간 <= 현재 강의 시작 시간
            if (!rooms.isEmpty() && rooms.peek().T <= cur.S) {
                rooms.poll(); // 제거
            }
            rooms.offer(cur);
        }

        System.out.println(rooms.size());

    }
}
