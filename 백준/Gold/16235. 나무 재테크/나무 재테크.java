import java.io.*;
import java.util.*;


public class Main {
    static int N, M, K;
    static int[][] arr;
    static Ground[][] grounds;
    static int[] di = {-1, 1, 0, 0, -1, 1, -1, 1}; // 상 하 좌 우 왼위 왼아래, 오위 오아래
    static int[] dj = {0, 0, -1, 1, -1, -1, 1, 1};

    static class Tree {
        int age;
        boolean status; // 죽은 상태 살아있는 상태

        public Tree(int age) {
            this.age = age;
            this.status = true;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "age=" + age +
                    ", status=" + status +
                    '}';
        }
    }

    static class Ground {
        int nutrient; // 양분
        List<Tree> trees; // 나무의 수

        public Ground() {
            this.nutrient = 5;
            // 나무가 살아있는 상태일때는 나이가 어린 순, 죽어있는 상태일 때는 나이가 많은 순
            this.trees = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 땅의 크기
        M = Integer.parseInt(st.nextToken()); // 구매한 나무의 개수
        K = Integer.parseInt(st.nextToken()); // K년이 지나고 살아있는 나무 개수 구하기

        grounds = new Ground[N + 1][N + 1]; // 나무와 양분을 기록할 map
        arr = new int[N + 1][N + 1]; // 로봇이 추가하는 양분의 양

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                grounds[i][j] = new Ground();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            grounds[x][y].trees.add(new Tree(z));
        }

        int year = 0;

        while (year < K) {
            // 봄
            spring();

            // 가을
            fall();
            // 겨울
            winter();
            year++;
        }

        // K년이 지나고 난 후 살아있는 나무의 개수
        int result = 0;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                Ground cur = grounds[i][j];
                // 나무가 있는 것 중 살아있는 나무의 개수 구하기
                result += cur.trees.size();
            }
        }
        System.out.println(result);
    }

    /*
    봄
    - 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
    - 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다
    - 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다
    - 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
     * */
    static void spring() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                Ground cur = grounds[i][j];
                // 나무가 존재 할 때
                List<Tree> deadTree = new ArrayList<>(); // 죽은 나무 저장
                List<Tree> tempList = new ArrayList<>(); // 살아있는 나무 저장
                Collections.sort(cur.trees, ((o1, o2) -> o1.age - o2.age));

                for (Tree tree : cur.trees) {
                    if (cur.nutrient >= tree.age) { // 현재 땅의 양분이 나무의 나이보다 크거나 같을 때 먹임
                        cur.nutrient -= tree.age;
                        tree.age += 1;
                        tempList.add(tree);
                    } else {
                        // 죽은 나무
                        tree.status = false;
                        deadTree.add(tree);
                    }
                }

                cur.trees = tempList;

                // 여름
                for (Tree tree : deadTree) {
                    cur.nutrient += tree.age / 2;
                }
            }
        }
    }

    /*
    여름
    - 봄에 죽은 나무가 양분으로 변하게 된다
    - 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가 (소수점 아래 버림)
     * */

    /*
    가을
    - 나무가 번식한다, 번식하는 나무는 나이가 5의 배수
    -  인접한 8개의 칸에 나이가 1인 나무가 생긴다
    - 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
     * */
    static void fall() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                Ground cur = grounds[i][j];
                for (Tree tree : cur.trees) {
                    if (tree.status && tree.age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int ni = i + di[d];
                            int nj = j + dj[d];

                            if (ni <= 0 || ni > N || nj <= 0 || nj > N) continue;

                            grounds[ni][nj].trees.add(new Tree(1));
                        }
                    }
                }

            }
        }
    }

    /*
    겨울
    - S2D2가 땅을 돌아다니면서 땅에 양분을 추가
    - 각 칸에 추가되는 양분의 양은 A[r][c]
 * */
    static void winter() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                grounds[i][j].nutrient += arr[i][j];
            }
        }
    }


}