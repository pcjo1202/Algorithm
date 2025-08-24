function solution(str1, str2) {
  var answer = 0;

  // 1-2. 원문을 순회하며 두 글자 쌍 만들기 (둘 다 알파벳일 때만)
  const temp1 = [];
  const temp2 = [];

  const s1 = str1.toLowerCase();
  const s2 = str2.toLowerCase();
  const isAlpha = (ch) => ch >= 'a' && ch <= 'z';

  for (let i = 0; i < s1.length - 1; i++) {
    const a = s1[i], b = s1[i + 1];
    if (isAlpha(a) && isAlpha(b)) temp1.push(a + b);
  }
  for (let i = 0; i < s2.length - 1; i++) {
    const a = s2[i], b = s2[i + 1];
    if (isAlpha(a) && isAlpha(b)) temp2.push(a + b);
  }

  // 3. 다중집합 빈도 맵
  const map1 = new Map();
  const map2 = new Map();
  temp1.forEach(e => map1.set(e, (map1.get(e) || 0) + 1));
  temp2.forEach(e => map2.set(e, (map2.get(e) || 0) + 1));

  // 4. 교집합 & 합집합
  let 교집합크기 = 0;
  let 합집합크기 = 0;
  const allKeys = new Set([...map1.keys(), ...map2.keys()]);
  allKeys.forEach(key => {
    const c1 = map1.get(key) || 0;
    const c2 = map2.get(key) || 0;
    교집합크기 += Math.min(c1, c2);
    합집합크기 += Math.max(c1, c2);
  });

  // 5. 자카드 유사도
  answer = 합집합크기 === 0 ? 65536 : Math.floor((교집합크기 / 합집합크기) * 65536);
  return answer;
}
