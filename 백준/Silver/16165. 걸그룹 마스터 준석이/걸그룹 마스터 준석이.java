import java.io.*;
import java.util.*;

public class Main {
    static class GirlGroup {
        String groupName;
        int memberNum;
        String[] members;

        public GirlGroup(String groupName, int memberNum, String[] members) {
            this.groupName = groupName;
            this.memberNum = memberNum;
            this.members = members;
        }
    }

    static int N, M;
    static GirlGroup[] list;

    public static void main(String[] arg) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 걸그룹 수
        M = Integer.parseInt(st.nextToken()); // 문제 수

        list = new GirlGroup[N];

        for (int i = 0; i < N; i++) {
            String gName = br.readLine();
            int gNum = Integer.parseInt(br.readLine());
            String[] members = new String[gNum];

            for (int j = 0; j < gNum; j++) {
                members[j] = br.readLine();
            }
            list[i] = new GirlGroup(gName, gNum, members);
        }

        // 게임
        // 1. 멤버 or 팀 이름 입력 받음
        // 2. 0 or 1 입력 받음 (0 : 멤버 이름 전체 출력(사전 순), 1: 팀 이름 출력)
        out:
        for (int i = 0; i < M; i++) {
            String gameInput = br.readLine();
            int gameNum = Integer.parseInt(br.readLine());

            if (gameNum == 0) { // 팀이름 input, 멤버 이름 전체 출력
                for (GirlGroup g : list) { // 전체 그룹 중...
                    if (g.groupName.equals(gameInput)) { // 주어진 이름과 팀 이름이 같다면
                        Arrays.sort(g.members, (o1, o2) -> {
                            return o1.compareTo(o2);
                        });
                        for (String name : g.members) {
                            System.out.println(name);
                        }
                        continue out;
                    }
                }

            } else if (gameNum == 1) { // 팀 이름 출력 (
                for (GirlGroup g : list) { // 전체 그룹 중
                    for (String name : g.members) {
                        // 멤버 이름 input, 멤버의 이름과 주어진 이름이 같다면, 다음 게임으로
                        if (name.equals(gameInput)) {
                            System.out.println(g.groupName);
                            continue out;
                        }
                    }
                }
            }
        }
    }
}