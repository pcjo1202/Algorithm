import java.util.*;

class Solution {
    static int[] di = {1, 0, 0, -1}; // d, l, r, u
    static int[] dj = {0, -1, 1, 0};
    static String[] direction = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        // 문자열이 사전순? d, l, r, u
        // 일단 먼저 아래로 쭉 이동 (열이 일치할 때 까지)
        // 불가능 한 조건
        // - k번 이동해도 도착을 못할 때 (경로길이 > k)
        // - 도착 후에도 횟수가 남아서 더 움직였다가 다시 돌아가지 못할 때 (k - 경로길이 % 2 != 0)
        
        int pathLength = Math.abs(x - r) + Math.abs(y - c);
        if(pathLength > k || (k - pathLength) % 2 != 0){
            return "impossible";
        }
        
        // 가능할 때
        int i = x;
        int j = y;
        while(k > 0){
            
            for(int d = 0 ; d < 4 ; d++){
                int ni = i + di[d];
                int nj = j + dj[d];
                
                if(ni <= 0 || ni > n || nj <= 0 || nj > m) continue;
                int temp = Math.abs(ni - r) + Math.abs(nj - c);
                if(temp > k) continue;
                
                i = ni;
                j = nj;
                answer += direction[d];
                k--;
                break;
            }
            
        }
        
        return answer;
    }
}