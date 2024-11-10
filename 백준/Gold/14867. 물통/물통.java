import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Bottle size, end;
    static int result;

    static class Bottle {
        int A, B, count;

        public Bottle(int a, int b, int count) {
            this.A = a;
            this.B = b;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = new Bottle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        end = new Bottle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        result = bfs(size);

        System.out.println(result);
    }

    static int bfs(Bottle size) {
        boolean[][] visited = new boolean[size.A + 1][size.B + 1]; // [a][b] 의 물 상태 방문 처리?
        Queue<Bottle> queue = new ArrayDeque<>();
        queue.offer(new Bottle(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Bottle cur = queue.poll(); // 현재 보틀의 상태

            // 종료 조건 : end의 상태와 같아진다면
            if (cur.A == end.A && cur.B == end.B) {
                return cur.count;
            }

            // 탐색
            // 1. fill 작업
            if (cur.A != size.A) {
                Bottle next = new Bottle(fill(0), cur.B, cur.count + 1); // 0 : A, 1 : B 채우기
                if (!visited[next.A][next.B]) {
                    queue.offer(next);
                    visited[next.A][next.B] = true;
                }
            }

            if (cur.B != size.B) {
                Bottle next = new Bottle(cur.A, fill(1), cur.count + 1); // 0 : A, 1 : B 채우기
                if (!visited[next.A][next.B]) {
                    queue.offer(next);
                    visited[next.A][next.B] = true;
                }
            }

            // 2. empty 작업
            if (cur.A != 0) {
                Bottle next = new Bottle(0, cur.B, cur.count + 1); // 0 : A, 1 : B 채우기
                if (!visited[next.A][next.B]) {
                    queue.offer(next);
                    visited[next.A][next.B] = true;
                }
            }

            if (cur.B != 0) {
                Bottle next = new Bottle(cur.A, 0, cur.count + 1); // 0 : A, 1 : B 채우기
                if (!visited[next.A][next.B]) {
                    queue.offer(next);
                    visited[next.A][next.B] = true;
                }
            }

            // 3. move 작업
            if (cur.A != size.A && cur.B != 0) {
                // B가 비어있지 않고, A가 가득 차있지 않으면 move B -> A
                Bottle next = move(cur, 1);
                if (!visited[next.A][next.B]) {
                    queue.offer(next);
                    visited[next.A][next.B] = true;
                }
            }

            if (cur.B != size.B && cur.A != 0) {
                // A가 비어있지 않고, B가 가득 차있지 않으면 move A-> B
                Bottle next = move(cur, 0);
                if (!visited[next.A][next.B]) {
                    queue.offer(next);
                    visited[next.A][next.B] = true;
                }
            }

        }

        return -1;
    }

    // fill 작업
    static int fill(int target) {
        // 0 : A, 1 : B 채우기
        // 0
        if (target == 0) {
            return size.A;
        }
        // 1
        return size.B;
    }

    // num == 0 : A -> B
    // num == 1 : B -> A
    static Bottle move(Bottle cur, int num) {
        int newA = 0, newB = 0;
        // A -> B로 물 옮김

        if (num == 0) {
            // 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 적거나 같다면 물통 x의 물을 물통 y에 모두 붓는다.
            if (cur.A <= size.B - cur.B) {
                newA = 0;
                newB = cur.B + cur.A;
            } else if (cur.A > size.B - cur.B) {
                //물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 많다면 부을 수 있는 만큼 최대로 부어 물통 y를 꽉 채우고 나머지는 물통 x에 남긴다.
                newA = cur.A - (size.B - cur.B);
                newB = size.B;
            }
        } else {
            // 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 적거나 같다면 물통 x의 물을 물통 y에 모두 붓는다.
            if (cur.B <= size.A - cur.A) {
                newB = 0;
                newA = cur.A + cur.B;
            } else if (cur.B > size.A - cur.A) {
                //물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 많다면 부을 수 있는 만큼 최대로 부어 물통 y를 꽉 채우고 나머지는 물통 x에 남긴다.
                newB = cur.B - (size.A - cur.A);
                newA = size.A;
            }
        }

        return new Bottle(newA, newB, cur.count + 1);
    }
}