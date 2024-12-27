import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[] commands, dice;
    static int[] di = {0, 0, 0, -1, 1}; // 0, 동, 서, 북, 남
    static int[] dj = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        commands = new int[K];
        dice = new int[6]; // 윗, 동, 바닥, 서, 북, 남
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        int ci = x;
        int cj = y;

        for (int i = 0; i < K; i++) {
            int command = commands[i];
            int ni = ci + di[command];
            int nj = cj + dj[command];

            if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;

            roll(command);

            if (map[ni][nj] == 0) {
                map[ni][nj] = dice[2];
            } else {
                dice[2] = map[ni][nj];
                map[ni][nj] = 0;
            }

            sb.append(dice[0]).append("\n");


            ci = ni;
            cj = nj;
        }

        System.out.println(sb);
    }

    static void roll(int command) {
        int[] temp = dice.clone();
        switch (command) {
            case 1: // 동
                dice[0] = temp[3];
                dice[1] = temp[0];
                dice[2] = temp[1];
                dice[3] = temp[2];
                break;
            case 2: // 서
                dice[0] = temp[1];
                dice[1] = temp[2];
                dice[2] = temp[3];
                dice[3] = temp[0];
                break;
            case 3: // 북
                dice[0] = temp[5];
                dice[4] = temp[0];
                dice[2] = temp[4];
                dice[5] = temp[2];
                break;
            case 4: // 남
                dice[0] = temp[4];
                dice[4] = temp[2];
                dice[2] = temp[5];
                dice[5] = temp[0];
                break;
        }
    }
}