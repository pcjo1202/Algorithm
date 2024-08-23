import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static int H,W,N, direct, curi, curj;
    static char[][] map;
    static String command;
    static int[] di = { -1, 1, 0, 0}; // 상하좌우
    static int[] dj = {0 ,0 , -1, 1};
    static char [] directChar = {'^','v','<', '>'}; // 상하좌우

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                }
            }

            N = Integer.parseInt(br.readLine());

            command = br.readLine();

            // 현재 전차의 위치를 찾기
            out: for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    for (int k = 0; k < 4; k++) {
                        if(map[i][j] == directChar[k]){
                            curi = i;
                            curj = j;
                            direct = k;  // 전차 방향 저장 => 여기 틀렷어
                            break out;
                        }
                    }
                }
            }
            // 여기서 명령어 실행
            for(char c : command.toCharArray()){
                game(c);
            }

           sb.append("#").append(test_case).append(" ");
            for (char[] arr : map) {
                for (char r : arr) {
                    sb.append(r);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    // 명령어 별로 동작을 실행 시킬 함수
    static void game(char common) {
        switch (common){
            case 'U' :
                up();
                break;
            case 'D' :
                down();
                break;
            case 'L' :
                left();
                break;
            case 'R' :
                right();
                break;
            case 'S' :
                shoot();
                break;
        }
    }

    private static void shoot() {
        int ni = curi;
        int nj = curj;

        while(0<= ni && ni < H && 0 <= nj && nj < W){ // 총알을 쏜 방향이 평지, 물 이라면 다음으로 쭉
            ni += di[direct];
            nj += dj[direct];
            if(ni < 0 || ni >= H || nj < 0|| nj >= W || map[ni][nj] == '#'){ // 맵 밖을 나갔다면, 강철 벽을 만났다면
                return;
            }
            // 여기까지 왔다면 현재 ni, nj는 벽을 찾은 것임
            if(map[ni][nj] == '*'){ // 벽돌 벽이라면
                map[ni][nj] = '.'; // 벽이 파괴되고 평지
                return;
            }
        }
    }

    private static void right() {
        move(3);
    }

    private static void left() {
        move(2);
    }

    private static void down() {
        move(1);
    }

    private static void up() {
     /*
    U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
    */
        move(0);
    }

    static void move(int commonD){
        direct = commonD;
        int ni = curi + di[direct];
        int nj = curj + dj[direct];


        if(0<= ni && ni < H && 0 <= nj && nj < W && map[ni][nj] == '.'){ // 벽이 아니고, 다음 칸이 평지라면, 그 칸으로 이동
            map[ni][nj] = directChar[direct]; // 현재 방향을 유지한 문자로 빠구기
            map[curi][curj] = '.'; // 지나간 자리는 평지로 바꾸기
            
            // 현재 위치 update
            curi = ni;
            curj = nj;
        }else{
            // 이동하지 못하면 방향만 바꾸기
            map[curi][curj] = directChar[direct];
        }
    }

    //
}