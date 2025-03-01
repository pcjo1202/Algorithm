import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static TreeSet<Problem> list;
    static HashMap<Integer, Integer> dictionary;
    static List<Integer> result;
    static Command[] commandList;

    static class Problem {
        int P, L;

        public Problem(int p, int l) {
            P = p;
            L = l;
        }
    }

    static class Command {
        String c;
        int x, y;

        public Command(String c, int x, int y) {
            this.c = c; // 명령어
            this.x = x; //
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new TreeSet<>(((o1, o2) -> { // 문제를 정렬해서 가지고 있을
            if (o1.L == o2.L) {
                return Integer.compare(o1.P, o2.P);
            }
            return Integer.compare(o1.L, o2.L);
        }));

        dictionary = new HashMap<>(); // 문제 번호, 난이도

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            list.add(new Problem(P, L));
            dictionary.put(P, L);
        }

        M = Integer.parseInt(br.readLine());

        commandList = new Command[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            String c = st.nextToken();
            int x = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
            int y = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;

            commandList[i] = new Command(c, x, y);
        }

        result = new ArrayList<>();

        // 명령어 수행
        for (Command cur : commandList) {
            switch (cur.c) {
                case "recommend":
                    recommend(cur.x);
                    break;
                case "add":
                    add(cur.x, cur.y);
                    break;
                case "solved":
                    solved(cur.x);
                    break;
            }
        }

        for (int res : result) {
            System.out.println(res);
        }
    }

    static void recommend(int X) {
        if (X == 1) { // 가장 어려운 문제 (난이도 최댓값 & 가장 큰 문제번호)
            result.add(list.last().P);
        } else if (X == -1) { // 가장 쉬운 문제 (난이도 최솟값 & 가장 작은 문제번호)
            result.add(list.first().P);
        }
    }

    static void add(int P, int L) {
        list.add(new Problem(P, L));
        dictionary.put(P, L);
    }

    static void solved(int P) {
        list.remove(new Problem(P, dictionary.get(P)));
    }
}