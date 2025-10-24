import java.io.*;
import java.util.*;

public class Main {
    static int numA, numB;
    static String[] commands = {"D", "S", "L", "R"};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());

            numA = Integer.parseInt(st.nextToken());
            numB = Integer.parseInt(st.nextToken());

            sb.append(String.format("%s\n", bfs(numA, numB)));
        }

        System.out.println(sb.toString());
    }

    static String bfs(int A, int B) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10001];
        queue.offer(new Node(A, ""));
        visited[A] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.num == B) return cur.str;

            for (String command : commands) {
                int newN = facade(command, cur.num);
                if (visited[newN]) continue;

                queue.offer(new Node(newN, cur.str + command));
                visited[newN] = true;
            }
        }
        return "";
    }

    static int[] convertor(int number) {
        int[] arr = new int[4];

        arr[0] = number / 1000;
        arr[1] = number / 100 % 10;
        arr[2] = number / 10 % 10;
        arr[3] = number % 10;

        return arr;
    }

    static int facade(String command, int n) {
        if (command == "D") return commD(n);
        else if (command == "S") return commS(n);
        else if (command == "L") return commL(n);
        else if (command == "R") return commR(n);
        return -1;
    }

    // n을 두배로 바꿈
    static int commD(int n) {
        int newN = n * 2;

        if (newN > 9999) {
            newN = newN % 10000;
        }
        return newN;
    }

    // n - 1 결과
    static int commS(int n) {
        if (n == 0) {
            return 9999;
        }
        return n - 1;
    }

    // n의 자리수를 왼편으로 회전
    static int commL(int n) {
        int[] arr = convertor(n);
        int temp = arr[0];

        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = temp;

        return arr[0] * 1000 + arr[1] * 100 + arr[2] * 10 + arr[3];
    }

    static int commR(int n) {
        int[] arr = convertor(n);
        int size = arr.length;
        int temp = arr[size - 1];

        for (int i = size - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }

        arr[0] = temp;

        return arr[0] * 1000 + arr[1] * 100 + arr[2] * 10 + arr[3];
    }

    static class Node {
        int num;
        String str;

        public Node(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }
}
