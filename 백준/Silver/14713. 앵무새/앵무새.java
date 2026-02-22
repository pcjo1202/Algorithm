import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<String>> arr;
    static String[] L;
    static String POSSIBLE = "Possible";
    static String IMPOSSIBLE = "Impossible";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>(); // 앵무새가 말한 문장

        for (int i = 0; i < N; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (String str : temp) {
                arr.get(i).add(str);
            }
        }

        L = br.readLine().split(" "); // 받아 적은 문장

        Queue<String> queue = new ArrayDeque<>();

        for (String each : L) {
            queue.offer(each);
        }

        int[] idxArr = new int[N];

        // queue에서 하나씩 꺼내서 각 자료구조에서 순서대로 나오는지 확인하기.
        while (!queue.isEmpty()) {
            String cur = queue.poll();

            boolean matched = false;
            for (int i = 0; i < N; i++) {
                // 각 앵무새의 idx의 단어가 Cur과 일치하면, 해당 앵무새 idx 증가
                if (idxArr[i] < arr.get(i).size() && arr.get(i).get(idxArr[i]).equals(cur)) {
                    idxArr[i]++;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                System.out.println(IMPOSSIBLE);
                return;
            }
        }

        // 모든 앵무새가 자기 문장을 끝까지 말했는지 확인
        for (int i = 0; i < N; i++) {
            if (idxArr[i] != arr.get(i).size()) {
                System.out.println(IMPOSSIBLE);
                return;
            }
        }

        System.out.println(POSSIBLE);

    }
}
