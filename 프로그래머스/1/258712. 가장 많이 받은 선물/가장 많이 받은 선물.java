import java.util.*;

class Solution {
    static int[][] table, memo;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int size = friends.length;
        
        table = new int[size][size]; // 주고 받은 선물
        memo = new int[size][3]; // 준 선물, 받은 선물, 선물지수
        
        for(int j = 0 ; j < gifts.length ; j++){
            String[] temp = gifts[j].split(" "); // 공백을 기준으로 자르기
            int from = getKey(friends, temp[0]); // 주는 친구 index
            int to = getKey(friends, temp[1]); // 받는 친구 index

            table[from][to] += 1; // 기록

            memo[from][0] += 1; // 준선물 ++;
            memo[from][2] += 1; // 선물 지수 증가

            memo[to][1] += 1; // 받은 선물++;
            memo[to][2] -= 1; // 선물 지수 감소
        }
        
        
        // for(int[] arr : memo){
        //     System.out.println(Arrays.toString(arr));
        // }
        
        
        // 다음달 선물 많이 받는 사람 찾기
        int[] result = new int[size];
        
        for(int i = 0 ; i < size ; i++){
            for(int j = i ; j < size; j++){
                
                int iToj = table[i][j]; // i -> j
                int jToi = table[j][i]; // j -> i
                
                if(iToj > jToi){ // i가 더 많이 줬을 때
                    result[i] += 1;
                }else if(iToj < jToi){ // j가 더 많이 줬을 때
                    result[j] += 1;
                }else{
                    if(iToj == jToi || iToj == 0 && jToi == 0){
                        // 선물 지수 비교
                        if(memo[i][2] > memo[j][2]){
                            result[i] += 1;
                        } else if(memo[i][2] < memo[j][2]){
                            result[j] += 1;
                        }
                    }
                }
            }
        }
        
        Arrays.sort(result);
        answer = result[size-1];
        
        // System.out.println(Arrays.toString(result));
        
        return answer;                                     
    }
    // index 찾는 함수
    int getKey(String[] friends, String name){
        for(int i = 0 ; i < friends.length ; i++){
            if(friends[i].equals(name)){
                return i;
            }
        }
        return -1;
    }
}