import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long Hatk;
    static List<Room> roomList;

    static class Room {
        int t, a, h;

        public Room(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Hatk = Long.parseLong(st.nextToken());

        roomList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            roomList.add(new Room(t, a, h));
        }

        long left = 1;
        long right = Long.MAX_VALUE;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (isClearable(mid)) { // 현재 체력으로 클리어 가능한지 확인
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean isClearable(long maxHP) {
        long curHP = maxHP;
        long curAtk = Hatk;

        for (Room room : roomList) {
            if (room.t == 1) {
                long turnsToKill = (room.h + curAtk - 1) / curAtk;
                long turnsToSurvive = (curHP + room.a - 1) / room.a;

                if (turnsToKill > turnsToSurvive) return false;

                curHP -= room.a * (turnsToKill - 1);
            } else {
                curAtk += room.a;
                curHP = Math.min(maxHP, curHP + room.h);
            }
        }
        return true;
    }
}
