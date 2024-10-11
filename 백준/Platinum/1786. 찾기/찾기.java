import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int count;
    static String text, pattern;
    static int[] pArr;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        text = br.readLine();
        pattern = br.readLine();
        pArr = new int[pattern.length()];
        count = 0;

        // 1. Pi 배열 만들기
        setPArr();

        // 2.kmp 알고리즘
        kmp();

        System.out.println(count);
        System.out.println(sb);
    }

    static void setPArr() {
        // pi 만들기
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            char cur = pattern.charAt(i); // 접미사
            // 처음 접두사가 아니면서 일치하지 않으면, 반복되는 패턴의 앞부분으로 변경
            // 만약 반복되는 패턴이 없으면, j = 0까지 돌아갈거임
            while (j > 0 && cur != pattern.charAt(j)) { // 접미사와 접두사 비교
                j = pArr[j - 1];
            }
            // 일치할때, 접두사의 길이(경계) 저장
            // 현재 맞은 idx의 +1은 길이이자, 다음 체크할 idx가 됨
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pArr[i] = ++j;
            }
        }
    }

    static void kmp() {
        int idx = 0;  // 패턴 내 일치체크 idx

        // 전체 문자열 돌기
        for (int i = 0; i < text.length(); i++) {
            // 맞는 위치가 나올 때까지 j - 1칸의 공통 부분 위치로 이동
            while (idx > 0 && text.charAt(i) != pattern.charAt(idx)) {
                idx = pArr[idx - 1];
            }

            // text와 pattern 이 일치할때
            if (text.charAt(i) == pattern.charAt(idx)) {
                if (idx == pattern.length() - 1) { // 맞는 경우 패턴의 끝까지 확인했으면 정답
                    sb.append((i - idx + 1) + " "); // Text안에서 pattern이 나타는 위치 출력
                    idx = pArr[idx];
                    count += 1;
                } else { // 아니면 패턴의 다음 문자를 확인
                    idx++;
                }
            }
        }
    }
}