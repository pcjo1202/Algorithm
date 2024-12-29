import java.util.*;

class Solution {
    static HashMap<Integer, String> dictionary; // 사전
    static HashMap<String, Integer> reverse; // 거꾸로
    static String input;
    
    public int[] solution(String msg) {
        int[] answer = {};
        
        
        dictionary = new HashMap<>();
        reverse = new HashMap<>();
        
        // 1. 초기 사전 초기화
        for(int i = 'A' - '0' ; i <= 'Z' - '0'; i++){
            String alphabet = String.valueOf((char)(i+'0'));
            dictionary.put(i - 16, alphabet);
            reverse.put(alphabet, i - 16);
        }
        
        
        input = msg;
        
        while(input.length() > 0){
            // 2. 현재 입력과 일치하는 가장 긴 문자열 w를 찾기
            int index = find(); // 가장 긴 문자열까지의 index
            String curStr = input.substring(0, index); // 가장 긴 문자열
        
            // 3. w에 해당하는 사전의 "색인 번호"를 출력
            answer = Arrays.copyOf(answer, answer.length + 1);
            
            // dictionary에서 curStr일치하는 key 값 찾기
            answer[answer.length - 1] = reverse.get(curStr);
        

            // 4. 입력에서 처리되지 않은 다음 글자 사전에 등록
            if(index < input.length()){
                String nextStr = input.substring(0, index + 1);
                dictionary.put(dictionary.size() + 1, nextStr);
                reverse.put(nextStr, dictionary.size());
            }
           
            // 5. input에서 제거
            input = input.substring(index);
            
        }
        
        return answer;
    }
    
    static int find(){
        int index = 0;
        
        for(int i = 1 ; i <= input.length() ; i++){
            String temp = input.substring(0, i); // 찾기
            
            if(reverse.containsKey(temp)){
                index = i;
            }else{ // 포함되어 있지 않는다면 break
                break;
            }
        }
        
        return index;
    }
}