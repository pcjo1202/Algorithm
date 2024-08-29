import java.io.*;
import java.util.*;

public class Solution {
    static int N, minTime;
    static int[][] board;
    static int[] arr;

    static List<int[]> start;
    static List<int[]> stairs;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N + 1][N + 1];


            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < N + 1; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            start = new ArrayList<>();
            stairs = new ArrayList<>();
            // 처음 사람의 위치, 계단 위치 파악
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (board[i][j] == 0) continue;

                    if (board[i][j] == 1) { // 사람
                        start.add(new int[]{i, j});
                    } else if (board[i][j] > 0) {
                        stairs.add(new int[]{i, j});
                    }
                }
            }

            // 전략 : 모든 사람이 계단을 이용하는 모든 경우를 찾아낸다!! -> dfs
            // 계단을 이용하는 모든 경우를 가지고, 시뮬레이션 돌린다.
            minTime = Integer.MAX_VALUE;
            arr = new int[start.size()];

            combination(0);

            sb.append(String.format("#%d %d\n", test_case, minTime));
        }
        System.out.println(sb);
    }

    static void combination(int depth) {
        // 기저조건 모든 뎁스를 돌았을 때
        if (depth == start.size()) {
            simulation(arr);
            return;
        }

        // 탐색 조건 : 계단 1을 이용할 때, 0을 이용할 때
        arr[depth] = 0;
        combination(depth + 1);

        arr[depth] = 1;
        combination(depth + 1);
    }

    static void simulation(int[] arr) { // 계단을 이용하는 경우를 저장한 배열을 받음
        // 이동완료 시간 = 처음 위치~계단거리 + 계단 내려가는 시간 + {대기 시간}
        List<Integer> temp1 = new ArrayList<>(); // 1번 계단으로 가는 모든 사람의 처음 위치~계단거리를 저장할 리스트
        List<Integer> temp2 = new ArrayList<>(); // 2번 계단으로 가는 모든 사람의 처음 위치~계단거리를 저장할 리스트

        int stairTime1 = board[stairs.get(0)[0]][stairs.get(0)[1]]; // 1번 계단의 내려가는 시간
        int stairTime2 = board[stairs.get(1)[0]][stairs.get(1)[1]]; // 2번 계단의 내려가는 시간

        // 처음 위치 ~ 계단 거리 계산 후 저장
        for (int i = 0; i < arr.length; i++) {
            int[] from = start.get(i); // 현재 이동할 사람
            int[] to = stairs.get(arr[i]); // 현재 이동할 사람이 도착할 계단

            int goStair = calMove(from, to); // 처음 위치 ~ 계단거리
            if (arr[i] == 0) {
                temp1.add(goStair);
            } else {
                temp2.add(goStair);
            }
        }

        // 정렬: 시간이 오래 걸리는 순으로 정렬
        Collections.sort(temp1);
        Collections.sort(temp2);

        // 대기 시간이 생기는 친구가 있을 때, 혹은 없을 때 최대 시간 확인
        int temp1Last = calLastTime(temp1, stairTime1); // 1번 계단 이용
        int temp2Last = calLastTime(temp2, stairTime2); // 2번 계단 이용

        int lastTime = Math.max(temp1Last, temp2Last);

        // 최소 시간 갱신
        minTime = Math.min(minTime, lastTime);
    }

    static int calLastTime(List<Integer> list, int stairTime) {
        if (list.isEmpty()) return 0;

        Collections.sort(list);

        int[] stairUse = new int[3];
        int lastTime = 0;

        for (int arriveTime : list) {
            int availableTime = Math.max(arriveTime, stairUse[0]);
            lastTime = availableTime + stairTime;

            stairUse[0] = lastTime;
            Arrays.sort(stairUse);
        }

        return lastTime + 1;
    }

    static int calMove(int[] start, int[] end) { // 처음 위치에서 계단까지의 거리
        return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
    }
}