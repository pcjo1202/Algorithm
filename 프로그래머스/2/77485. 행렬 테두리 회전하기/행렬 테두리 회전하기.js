const dx = [0, 1, 0, -1] // 우 하 좌 상
const dy = [1, 0, -1, 0]

function solution(rows, columns, queries) {
    let num = 1;
    const result = [];

    const board = Array.from({length: rows + 1}, (_, i) => {
        return Array.from({length: columns + 1}, (_, j) => {
            if (i === 0 || j === 0) return 0;
            return num++;
        });
    });

    for (const query of queries) {
        const [x1, y1, x2, y2] = query;
        const min = rotate([x1, y1], [x2, y2]); // 회전 및 최소값 계산
        result.push(min);
    }

    // 시계 방향 회전 및 최소값 계산
    function rotate([x1, y1], [x2, y2]) {
        let prev = board[x1][y1]; // 회전 시작 값 저장
        let min = prev; // 최소값 추적
        let x = x1;
        let y = y1;

        // 4방향 시계방향 이동
        for (let d = 0; d < 4; d++) {
            while (true) {
                const nx = x + dx[d];
                const ny = y + dy[d];

                // 사각형 경계 밖으로 나가면 방향 전환
                if (nx < x1 || nx > x2 || ny < y1 || ny > y2) break;

                // 값 회전
                const temp = board[nx][ny];
                board[nx][ny] = prev;
                prev = temp;

                // 최소값 갱신
                min = Math.min(min, prev);

                // 다음 좌표로 이동
                x = nx;
                y = ny;
            }
        }

        return min;
    }

    return result;
}
