function solution(files) {
    // 파일을 파싱하여 head, number, 원본 문자열을 포함한 객체 배열 생성
    const parsed = files.map((file, index) => {
        const match = file.match(/^([^\d]+)(\d{1,5})/); // HEAD + NUMBER
        return {
            head: match[1].toLowerCase(),
            number: parseInt(match[2]),
            original: file,
        };
    });

    // 정렬
    parsed.sort((a, b) => {
        if (a.head !== b.head) {
            return a.head.localeCompare(b.head);
        }
        return a.number - b.number;
    });

    // 결과 추출
    return parsed.map(entry => entry.original);
}
