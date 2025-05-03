const di = [-1, 1, 0, 0]; // 상하좌우
const dj = [0, 0, -1, 1];

const start = { i: 0, j: 0}
const end = { i: 0, j: 0}

function solution(board) {
    const N = board.length;
    const M = board[0].length;
    
    board.forEach((str, i)=>{
        for(let j = 0; j < M ; j++){
            const e = str[j];
            if(e === "R"){
                start.i = i;
                start.j = j;
            }else if(e === "G"){
                end.i = i;
                end.j = j;
            }
        }
    })
    
   function bfs() {
    const queue = [];
    const visited = new Set();

    queue.push({...start, count: 0 });

    while (queue.length > 0) {
        const { i, j, count } = queue.shift();

        if (`${i}-${j}` in visited) continue;
        visited.add(`${i}-${j}`);

        if (i === end.i && j === end.j) return count;

            for (let d = 0; d < 4; d++) {
                let ni = i, nj = j;

                while (true) {
                    const ti = ni + di[d];
                    const tj = nj + dj[d];

                    if (ti < 0 || ti >= N || tj < 0 || tj >= M || board[ti][tj] === "D") break;
                    
                    ni = ti;
                    nj = tj;
                }

                if (!visited.has(`${ni}-${nj}`)) {
                    queue.push({ i: ni, j: nj, count: count + 1 });
                }
            }
        }

        return -1;
    }

    
    return bfs();
}