import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<int[]> combChickenList;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 남길 치킨 집의 개수 1 ~ M개

        board = new int[N + 2][N + 2]; // 1,1부터 시작하고, 불가능한 범위를 -1로 초기화

        for (int[] arr : board) {
            Arrays.fill(arr, -1);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N]; // 방문 여부 체크

        List<int[]> house = new ArrayList<>();
        List<int[]> chicken = new ArrayList<>();

        // 집의 좌표를 찾아서 저장
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] == 1) { // 집이라면,
                    house.add(new int[]{i, j});
                } else if (board[i][j] == 2) { // 치킨이라면,
                    chicken.add(new int[]{i, j});
                }
            }
        }


//        // 모든 치킨집의 조합 중 M개 뽑은 모든 조합들
        combChickenList = new ArrayList<>();
        combination(0, 0, new int[M], new boolean[chicken.size()]);

        int min = Integer.MAX_VALUE;


        for (int j = 0; j < combChickenList.size(); j++) { // 뽑은 조합을 기준, 집과 가장 가까운..치킨집 거리 구하고, 합
            // M개씩 뽑은 여러개의 조합들을 하나씩 돌아볼거에요~
            // 하나의 집 -> 치킨집까지 가는 거리의 최단 거리의 조합 찾기!
            int distSum = 0;
            for (int i = 0; i < house.size(); i++) {
                int[] curHouse = house.get(i);
                // 집 ~ 치킨집까지 거리 계산
                int minDist = Integer.MAX_VALUE;

                for (int k = 0; k < M; k++) {
                    int curChickenIdx = combChickenList.get(j)[k];
                    int[] curChicken = chicken.get(curChickenIdx); // 어떤 치킨집 하나의 위치

                    int tempDist = calDist(curHouse[0], curHouse[1], curChicken[0], curChicken[1]);
                    minDist = Math.min(minDist, tempDist);
                }

                distSum += minDist;
            }

            min = Math.min(distSum, min);
        }

        System.out.println(min);
    }

    static void combination(int depth, int start, int[] arr, boolean[] isSelect) { // arr는 치킨집의 인덱스를 저장
        if (depth == M) {
            combChickenList.add(arr.clone());
            return;
        }

        for (int i = start; i < isSelect.length; i++) { // 치킨집의 번호를 뽑을 겁니다.
            if (isSelect[i]) continue;
            arr[depth] = i;
            isSelect[i] = true;
            combination(depth + 1, i + 1, arr, isSelect);
            isSelect[i] = false;
        }
    }

    static int calDist(int hi, int hj, int ci, int cj) { // 거리 계산해주는 함수
        return Math.abs(hi - ci) + Math.abs(hj - cj);
    }
}