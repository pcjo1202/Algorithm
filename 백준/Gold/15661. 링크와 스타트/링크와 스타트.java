import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, result;
    static int[][] board;
    static boolean[] isSelected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = Integer.MAX_VALUE;
        // 팀 조합 뽑기
        isSelected = new boolean[N];
        dfs(0);
        System.out.println(result);
        
    }

    static void dfs(int depth) {
        if (depth == N) {

            if (!teamCheck()) return; // 제대로된 팀이 아니라면 (한명만 있을 경우)

            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    teamA.add(i);
                } else {
                    teamB.add(i);
                }
            }
            solve(teamA, teamB);
            return;
        }

        // A팀
        isSelected[depth] = true;
        dfs(depth + 1);

        // B팀
        isSelected[depth] = false;
        dfs(depth + 1);

    }

    static int setSum(List<Integer> team) {
        int sum = 0;
        int size = team.size();

        for (int i = 0; i < size; i++) {
            int a = team.get(i);
            for (int j = 0; j < size; j++) {
                int b = team.get(j);
                if (a == b) continue;
                sum += board[a][b];
            }
        }
        return sum;
    }

    static void solve(List<Integer> teamA, List<Integer> teamB) {
        int a = setSum(teamA);
        int b = setSum(teamB);
        int temp = Math.abs(a - b);

        result = Math.min(temp, result);
    }

    static boolean teamCheck() {
        int teamA = 0;
        int teamB = 0;
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) {
                teamA++;
            } else {
                teamB++;
            }
        }
        // 팀이 제대로 생성이 안되었다면 빼기
        if (teamA < 2 || teamB < 2) {
            return false;
        }
        return true;
    }
}