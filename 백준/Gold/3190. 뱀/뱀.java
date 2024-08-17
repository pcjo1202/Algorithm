import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, L, count;
    static boolean[][] apple;
    static int[][] board;
    static HashMap<Integer, Character> map;
    static int[] di = {-1, 0, 1, 0,}; // 상우하좌
    static int[] dj = {0, 1, 0, -1,};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 보드의 크기
        K = Integer.parseInt(br.readLine()); // 사과의 개수

        board = new int[N + 1][N + 1]; // 시작 인덱스를 1,1로 하기 위해서


        apple = new boolean[N + 1][N + 1]; // 사과의 위치 기록

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 행
            int b = Integer.parseInt(st.nextToken()); // 열
            apple[a][b] = true;
        }

        L = Integer.parseInt(br.readLine()); // 뱀 방향 변환 횟수

        map = new HashMap<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시간
            char b = st.nextToken().charAt(0); // 방향
            map.put(a, b);
        }
        //====================여기서부터 로직=====================

        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 뱀의 몸 상태를 저장하는 역할
        count = 0; // 초 카운트
        int d = 1; // 방향 인덱스 초기값 , 상우하좌 -> 0,1,2,3
        queue.offer(new int[]{1, 1}); // 초기값 큐에 넣기
        board[1][1] = 1; // 뱀의 초기 위치 표시

        while (!queue.isEmpty()) {
            int[] head = queue.peekLast(); // 뱀의 머리의 현재 위치 파악
            count++;

            int ni = head[0] + di[d]; // 다음 머리의 위치
            int nj = head[1] + dj[d];

            // 1. 자기 자신이거나(board == 1), 벽이면 break
            if (ni < 1 || ni > N || nj < 1 || nj > N || board[ni][nj] == 1) break;


            // 2. 다음 위치가 사과인지 확인
            // - 사과이면, 먹고, 몸 늘리기
            if (apple[ni][nj]) {
                apple[ni][nj] = false; // 사과 먹음
            }
            // - 사과가 아니면, 그대로
            else {
                int[] tail = queue.poll(); // 꼬리 제거
                board[tail[0]][tail[1]] = 0; // 뱀 표시 제거
            }

            // 3. 뱀이 다음 위치로 이동
            queue.offer(new int[]{ni, nj});
            board[ni][nj] = 1;


            // 만약 시간 (count)가 map의 key에 존재한다면, 방향 전환
            if (map.containsKey(count)) {
                char direct = map.get(count);
                if (direct == 'D') { // 기존 방향에서 오른쪽 회전
                    d = (d + 1) % 4;
                } else if (direct == 'L') { // 기존 방향에서 왼쪽 회전
                    d = (d + 3) % 4;
                }
            }
        }
        System.out.println(count);
    }
}