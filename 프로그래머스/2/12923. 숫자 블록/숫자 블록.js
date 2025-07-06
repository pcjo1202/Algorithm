const MAX = 1e7;

function solution(begin, end) {
    const answer = [];

    for (let i = begin; i <= end; i++) {
        if (i === 1) {
            answer.push(0);
            continue;
        }

        let maxNum = 1; // 최소값은 1 (모든 수는 1로 나눠짐)
        const sqrt = Math.floor(Math.sqrt(i));

        for (let j = 2; j <= sqrt; j++) {
            if (i % j === 0) {
                const pair = i / j;

                if (pair <= MAX) {
                    maxNum = pair;
                    break;
                }

                if (j <= MAX) {
                    maxNum = Math.max(maxNum, j);
                }
            }
        }

        answer.push(maxNum);
    }

    return answer;
}
