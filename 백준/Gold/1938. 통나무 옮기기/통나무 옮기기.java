import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static char[][] map;
    static boolean[][][] visited;
    static int[] di = {-1, 1, 0, 0, -1, 1, -1, 1}; // 상 하 좌 우, 대각선
    static int[] dj = {0, 0, -1, 1, -1, -1, 1, 1};

    static class Node {
        int i, j, count, shape;
        Node parent; // 경로 추적을 위한 부모 노드

        public Node(int i, int j, int count, int shape, Node parent) {
            this.i = i;
            this.j = j;
            this.count = count;
            this.shape = shape;
            this.parent = parent; // 현재 노드의 부모 노드를 저장
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", j=" + j +
                    ", count=" + count +
                    ", shape=" + shape +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N][2];
        result = 0;


        Node start = new Node(0, 0, 0, 0, null);
        Node end = new Node(0, 0, 0, 0, null);

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B') {
                    start.i += i;
                    start.j += j;
                } else if (map[i][j] == 'E') {
                    end.i += i;
                    end.j += j;
                }
            }
        }

        // 시작 부분의 중앙 좌표
        start.i /= 3;
        start.j /= 3;
        // 도착 부분의 중앙 좌표
        end.i /= 3;
        end.j /= 3;


        start.shape = getShape(start);
        end.shape = getShape(end);

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        // 위쪽에 B가 있다면 세로, 오른쪽에 B가 있다면 가로
        visited[start.i][start.j][start.shape] = true;
        // 여기서 방문 처리
        // [0] : 가로 [1]: 세로 모양 방문 저장

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 현재 모양이 세로인지 가로인지 확인 (가로 = 0, 세로 = 1) 이 true
            // 중앙값의 좌표가 end와 같고, 방향도 end와 같다면 종료
            if (cur.i == end.i && cur.j == end.j && cur.shape == end.shape) {
                // 여기까지의 동작 횟수 저장
                result = cur.count;

//                // 경로 역추적
//                List<Node> path = new ArrayList<>();
//                while (cur != null) {
//                    path.add(cur);
//                    cur = cur.parent;
//                }
//                Collections.reverse(path); // 시작 노드부터 순서대로 출력하기 위해 반전
//
//                // 경로 출력
//                for (Node node : path) {
//                    System.out.println("i: " + node.i + ", j: " + node.j + ", count: " + node.count + ", shape: " + (node.shape == 0 ? "가로" : "세로"));
//                }
                break;
            }

            // 1. 상 하 좌 우 이동
            if (cur.shape == 0) { // 가로일 때
                // 위 아래로 이동 가능한지
                for (int d = 0; d < 2; d++) {
                    int ni = cur.i + di[d];
                    int nj = cur.j;

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    if (map[ni][nj] == '1' || map[ni][nj - 1] == '1' || map[ni][nj + 1] == '1') continue;
                    if (visited[ni][nj][cur.shape]) continue;

                    queue.offer(new Node(ni, nj, cur.count + 1, cur.shape, cur));
                    visited[ni][nj][cur.shape] = true;
                }
                // 좌우로 이동 가능한지
                for (int d = 2; d < 4; d++) {
                    int ni = cur.i;
                    int nj = cur.j + dj[d] * 2;

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    if (map[ni][nj] == '1') continue;
                    if (visited[ni][d == 2 ? nj + 1 : nj - 1][cur.shape]) continue; // 가운데 통나무로 방문표시

                    queue.offer(new Node(ni, d == 2 ? nj + 1 : nj - 1, cur.count + 1, cur.shape, cur));
                    visited[ni][d == 2 ? nj + 1 : nj - 1][cur.shape] = true;
                }
            } else if (cur.shape == 1) { // 세로일 때
                // 위 아래로 이동 가능한지
                for (int d = 0; d < 2; d++) {
                    int ni = cur.i + di[d] * 2;
                    int nj = cur.j;

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    if (map[ni][nj] == '1') continue;
                    if (visited[d == 0 ? ni + 1 : ni - 1][nj][cur.shape]) continue;

                    queue.offer(new Node(d == 0 ? ni + 1 : ni - 1, nj, cur.count + 1, cur.shape, cur));
                    visited[d == 0 ? ni + 1 : ni - 1][nj][cur.shape] = true;
                }

                // 좌우로 이동 가능한지
                for (int d = 2; d < 4; d++) {
                    int ni = cur.i;
                    int nj = cur.j + dj[d];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    if (map[ni][nj] == '1' || map[ni - 1][nj] == '1' || map[ni + 1][nj] == '1')
                        continue; // 세로 - 좌우로 이동했을 때 세로모양이 가능한지 확인
                    if (visited[ni][nj][cur.shape]) continue;

                    queue.offer(new Node(ni, nj, cur.count + 1, cur.shape, cur));
                    visited[ni][nj][cur.shape] = true;
                }
            }

            // 2. 회전
            // 8방에 나무가 있다면 불가능, 아니면 회전 가능
            if (checkTree(cur) && !visited[cur.i][cur.j][cur.shape == 1 ? 0 : 1]) {
                queue.offer(new Node(cur.i, cur.j, cur.count + 1, cur.shape == 1 ? 0 : 1, cur));
                visited[cur.i][cur.j][cur.shape == 1 ? 0 : 1] = true;
            }
        }


        System.out.println(result);
    }

    // 회전 가능한지 확인 : 주변에 나무가 있는지 체크해주는 함수
    static boolean checkTree(Node node) {
        for (int d = 0; d < 8; d++) {
            int ni = node.i + di[d];
            int nj = node.j + dj[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
                return false;
            }

            if (map[ni][nj] == '1') {
                return false;
            }
        }
        return true;
    }

    // 초기 중앙 Node의 모양 확인해주는 함수
    static int getShape(Node node) {
        // [0] : 가로 [1]: 세로 모양 방문 저장


        if (node.i > 0 && map[node.i - 1][node.j] == 'B' || node.i > 0 && map[node.i - 1][node.j] == 'E') { // 세로
            return 1;
        } else if (node.j > 0 && map[node.i][node.j - 1] == 'B' || node.j > 0 && map[node.i][node.j - 1] == 'E') { // 가로
            return 0;
        }
        return -1;
    }


}