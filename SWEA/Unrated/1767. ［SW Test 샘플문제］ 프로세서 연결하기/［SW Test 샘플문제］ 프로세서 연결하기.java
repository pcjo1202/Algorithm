import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, result, maxCoreCnt;
    static int[][] map, tempMap;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static List<Node> cores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) cores.add(new Node(i, j));
                }
            }


            tempMap = new int[N][N];

            for (int i = 0; i < N; i++) {
                tempMap[i] = map[i].clone();
            }

            maxCoreCnt = 0;
            result = Integer.MAX_VALUE;
            dfs(0, 0, 0);

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.println(sb);
    }

    /*
    각 요소마다 그을 수 있는 라인이 여러가지가 있을 수 있음.
    라인을 그리는 경우의 수마다 백트래킹으로 그려보면서, 모두 연결하였을 때, 라인의 수를 갱신

    - 가장자리와 붙어있는 core는 이미 연결되어 있다고 보고 바로 다음 core로 넘기기
    - 다음 요소로 넘길 때, 선을 그린 tempMap을 전달??
    * */
    static void dfs(int depth, int count, int coreCount) {
        // 기저 조건 : 모든 코어를 확인 했을 때
        if (depth == cores.size()) {
            if (maxCoreCnt < coreCount) {
                maxCoreCnt = coreCount;
                result = count;
            }else if(maxCoreCnt == coreCount){
                result = Math.min(result, count);
            }
            return;
        }

        // 현재 코어
        Node cur = cores.get(depth);

        // 가장자리와 붙어있는지 확인 붙어있으면, 선 연결 안하고 바로 다음 재귀 호출
        if (cur.i == 0 || cur.i == N - 1 || cur.j == 0 || cur.j == N - 1) {
            dfs(depth + 1, count, coreCount + 1);
        } else {
            // 4방으로 선을 연결 할 수 있는지 확인 후 재귀 호출
            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                boolean flag = true;
                List<Node> lineList = new ArrayList<>();
                // 범위 안에 있을 때 반복
                while (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                    // 1. 이미 그어져 있는 선을 만날 경우 false
                    // 2. core를 만날 경우 false;
                    if (tempMap[ni][nj] == 2 || tempMap[ni][nj] == 1) {
                        flag = false;
                        break;
                    }
                    lineList.add(new Node(ni, nj));
                    ni += di[d];
                    nj += dj[d];
                }

                // 가능하면, 선을 긋고, 그은 선의 개수만큼 count 증가
                if (flag) {
                    // 라인 그리기
                    for (Node line : lineList) {
                        tempMap[line.i][line.j] = 2;
                    }

                    dfs(depth + 1, count + lineList.size(), coreCount + 1);

                    // 라인 그린거 원상복귀
                    for (Node line : lineList) {
                        tempMap[line.i][line.j] = 0;
                    }
                }

            }
        }
        dfs(depth + 1, count,  coreCount);
    }

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}