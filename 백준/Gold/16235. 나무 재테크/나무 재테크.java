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
        boolean alive;

        public Tree(int age) {
            this.age = age;
            this.alive = true;
        }
    }

    static class Ground {
        int nutrient; // 양분
        List<Tree> trees;

        public Ground() {
            this.nutrient = 5;
            this.trees = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 땅의 크기
        M = Integer.parseInt(st.nextToken()); // 구매한 나무의 개수
        K = Integer.parseInt(st.nextToken()); // K년이 지나고 살아있는 나무 개수 구하기

        grounds = new Ground[N + 1][N + 1];
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
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

        for (int year = 0; year < K; year++) {
            spring();
            summer();
            fall();
            winter();
        }

        // K년이 지난 후 살아있는 나무 개수 세기
        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Tree tree : grounds[i][j].trees) {
                    if (tree.alive) result++;
                }
            }
        }
        System.out.println(result);
    }

    static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Ground ground = grounds[i][j];
                ground.trees.sort(Comparator.comparingInt(tree -> tree.age)); // 나이 순 정렬

                List<Tree> aliveTrees = new ArrayList<>();
                List<Tree> deadTrees = new ArrayList<>();

                for (Tree tree : ground.trees) {
                    if (ground.nutrient >= tree.age) {
                        ground.nutrient -= tree.age;
                        tree.age++;
                        aliveTrees.add(tree);
                    } else {
                        tree.alive = false;
                        deadTrees.add(tree);
                    }
                }

                ground.trees = aliveTrees;
                for (Tree deadTree : deadTrees) {
                    ground.nutrient += deadTree.age / 2; // 죽은 나무의 나이를 양분으로 변환
                }
            }
        }
    }

    static void summer() {
        // 봄에 deadTrees 리스트에 추가한 나무의 나이를 양분으로 바꿔 이미 적용하였으므로 summer()는 생략 가능합니다.
    }

    static void fall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Ground ground = grounds[i][j];

                for (Tree tree : ground.trees) {
                    if (tree.alive && tree.age % 5 == 0) { // 나이가 5의 배수인 나무만 번식
                        for (int d = 0; d < 8; d++) {
                            int ni = i + di[d];
                            int nj = j + dj[d];

                            if (ni <= 0 || ni > N || nj <= 0 || nj > N) continue;
                            grounds[ni][nj].trees.add(new Tree(1)); // 나이가 1인 나무 추가
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grounds[i][j].nutrient += arr[i][j];
            }
        }
    }
}