function solution(arr) {
    const answer = [0,0];
    
    const isCompress = Array.from({length: arr.length}).fill(Array.from({length: arr[0].length}).fill(false))
    
    // info : {si: 0, sj:0, size: 0}
    function dfs(info = {si: 0, sj:0, size: 0}){
        // 기저 조건 : 크기가 최소 일 때
        const {si, sj, size} = info
        const target = arr[si][sj];
        
        if(size === 1){
            answer[target]++;
            return;
        }
        
        // 기저 조건 : 압축 가능 여부
        if(check(info)){
            answer[target]++;
            return;
        }

        // 다음 : 4분할
        const nsize = Math.floor(size / 2);
        const mi = si + nsize;
        const mj = sj + nsize;
        
        dfs({si: si, sj: sj, size : nsize})              // 왼쪽 위
        dfs({si: si, sj: sj + nsize, size : nsize})      // 오른쪽 위
        dfs({si: si + nsize, sj: sj, size : nsize})      // 왼쪽 아래
        dfs({si: si + nsize, sj: sj + nsize, size : nsize})  // 오른쪽 아래
    }

    
    // 모든 숫자가 같은지 확인하기
    function check(info = {si: 0, sj:0, size: 0}){
        const {si, sj, size} = info
        const target = arr[si][sj];
        
        for(let i = si ; i < si + size ; i++){
            for(let j = sj ; j < sj + size ; j++){
                if(arr[i][j] !== target) return false;
            }
        }
    
        return true;
    }
    
    dfs({si: 0, sj:0, size: arr.length})
    
    return answer;
}

