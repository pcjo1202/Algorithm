class Node {
    constructor(i, j){
        this.i = i;
        this.j = j;
    }
}

class Queue {
    constructor(){
        this.queue = [];
    }
    
    enqueue(value){
        this.queue.push(value);
    }
    
    dequeue(){
        return this.queue.shift();
    }
    
    isEmpty(){
        return this.queue.length === 0;
    }
}

const dist = [[-1,0], [1,0], [0, -1], [0, 1]];

function solution(places) {
    // 대기실
    return places.map(check);
}

function check(place){
    const temp = [];
    
    // 응시자 위치 저장
    place.forEach((e, i) =>{
        Array.from(e).forEach((_, j)=>{
            if (place[i].charAt(j) === 'P') {
                temp.push(new Node(i, j));
            }
        });
    });
    
    // 조건에 맞는지 BFS로 확인
    for(let person of temp){
        if(!bfs(person, place)){
            return 0;
        }
    }
    
    return 1;
}
                                 
function bfs(start, place){
    const N = place.length;
    const M = place[0].length;
    
    const queue = new Queue();
    const visited = Array.from({length: N}, () => Array(M).fill(-1));

    queue.enqueue(new Node(start.i, start.j));
    visited[start.i][start.j] = 0;
    
    while (!queue.isEmpty()) {
        const cur = queue.dequeue();
        
        for (const [di, dj] of dist) {
            const ni = cur.i + di;
            const nj = cur.j + dj;
            
            if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
            if (visited[ni][nj] !== -1) continue; // 
            if (place[ni][nj] === 'X') continue; // 칸막이가 있으면 이동 불가

            visited[ni][nj] = visited[cur.i][cur.j] + 1;

            if (place[ni][nj] === 'P' && visited[ni][nj] <= 2) {
                return false;
            }

            if (visited[ni][nj] < 2) {
                queue.enqueue(new Node(ni, nj));
            }
        }
    }
    
    return true;
}
