import java.io.*;
import java.util.*;

public class Main {
    static Map<String, List<String>> list;
    static Set<String> visited, visitedGroup;
    static String firstGroup;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N;

        // 학회이름:회원이름, 회원이름,
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            list = new HashMap<>();

            // 1. 자료구조 만들기
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().trim().split(":");
                String name = temp[0]; // 그룹 이름
                String[] members = temp[1].split(","); // 소속 그룹 혹은 사람
                String last = members[members.length - 1].replace(".", "").trim();
                members[members.length - 1] = last;

                if (list.isEmpty()) {
                    firstGroup = name; // 회원 수 찾을 그룹
                }

                list.put(name, new ArrayList<>()); // 이름 - 소속 멤버 혹은 그룹

                for (String res : members) {
                    list.get(name).add(res);
                }

            }


            // 2. 개수 구하기
            visited = new HashSet<>();
            visitedGroup = new HashSet<>();

            sb.append(bfs(firstGroup)).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(String teamName) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(teamName);

        // 멤버 찾기
        while (!queue.isEmpty()) {
            String cur = queue.poll();

            for (String member : list.get(cur)) {
                // 그룹이라면
                if (list.containsKey(member)) {
                    if (visitedGroup.contains(member)) continue;
                    queue.offer(member); // 그룹 넣기
                    visitedGroup.add(member);
                } else {
                    // 멤버라면
                    visited.add(member);
                }
            }
        }

        return visited.size();
    }
}
