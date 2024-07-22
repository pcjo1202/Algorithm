import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        /*
        * N을 몇 개의 연속된 자연수의 합을 나타내는 가지 수
        * 15 -> 7+8 , 4+5+6 , 1+2+3+4
        *
        * 아이디어
        * - 자연수의 합에 들어가는 집합들은 N 이하!
        * - 일단 연속된 자연 수는 자기자신 포함
        * - 연속된 두 수를 더해서 N이 되게 하려면,
        * - 순서대로 더해보고, 더한 값이 N이 되면 count++
        * -
        * -
        * */

        int small = 1;
        int big = 1;
        int sum = 1;
        int count = 0;

        while(big <= N){ //중간까지 어차피 big의 최대 값은 N / 2 까지 (소수점은 버리니까 + 1)
            if(sum == N){
                count++;
                big++;
                sum += big;
            } else if(sum < N){
                big++;
                sum += big;
            } else{
                sum -= small;
                small++;
            }

        }

        System.out.println(count);
    }
}
