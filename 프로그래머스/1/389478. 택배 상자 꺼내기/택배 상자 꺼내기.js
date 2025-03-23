/*
*/
function solution(n, w, num) {
    var answer = 0;
    
    const i_size = Math.ceil(n / w) // row
    const j_size = w; // col
    const map = Array.from({ length: i_size }, () => Array(j_size).fill(0));
    
    let i = 0;
    let j = 0;
    let numi = 0;
    let numj = 0;
    let idx = 1;
    
    // Map index에 채우기
    while(idx <= n){
        map[i][j] = idx; // 맵에 채우기
        
        // ! num의 위치 저장하기
        if(idx == num){
            numi = i;
            numj = j;
        }
        
        // j 변화 시키기
        // 짝수 열 -> 
        if(i % 2 == 0){
            // i 변화 시키기
            if(idx % w == 0){
                i++;
            }else{
                j++;
            }
        }else{
            // 홀수 열 <-
            // i 변화 시키기
            if(idx % w == 0){
                i++;
            }else{
                j--;
            }
        }
        
        idx++;
    }
    
    // num를 꺼내는 상자의 개수 구하기
    let cnt = 0;
    while(numi < i_size && map[numi][numj] !== 0){
        cnt++;
        numi++;
    }
    
    return cnt;
}