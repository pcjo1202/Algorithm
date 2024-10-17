import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, U, V;
    static List<ArrayList<Integer>> list; // 내가 이기는 사람
    static List<ArrayList<Integer>> relist; // 내가 지는 사람

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 번호 (1 ~ N)
        M = Integer.parseInt(st.nextToken()); // 질문 횟 수
        X = Integer.parseInt(st.nextToken()); // 등수 알고 싶은 학생 번호
        U = 0; // 가장 높은 등수
        V = 0; // 가장 낮은 등수

        list = new ArrayList<>();
        relist = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
            relist.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            relist.get(b).add(a);
        }

        int loseCount = findWin(X, new boolean[N + 1]); // X보다 못한 사람 수
//        System.out.println("X보다 못한 사람 수 :" + loseCount);
        int winCount = findLose(X, new boolean[N + 1]); // X보다 잘한 사람 수
//        System.out.println("X보다 잘한 사람 수 : " + winCount);
        // 가장 높은 등수
        U = winCount + 1;
        // 가장 낮은 등수
        V = N - loseCount;

        System.out.printf("%d %d", U, V);
    }

    static int findWin(int node, boolean[] visited) {
        int count = 0;
        visited[node] = true;

        for (int next : list.get(node)) {
            if (visited[next]) continue;
            count += findWin(next, visited) + 1;
        }
        return count;
    }

    static int findLose(int node, boolean[] visited) {
        int count = 0;
        visited[node] = true;

        for (int next : relist.get(node)) {
            if (visited[next]) continue;
            count += findLose(next, visited) + 1;
        }
        return count;
    }
}