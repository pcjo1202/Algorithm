import java.util.Scanner;
// import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
            int M = sc.nextInt();

            int [] Ai = new int[N];
            int [] Bi = new int[M];

            for(int i = 0 ; i < N ; i ++){
                Ai[i] = sc.nextInt();
            }
            for(int i = 0 ; i < M ; i ++){
                Bi[i] = sc.nextInt();
            }

            int max = 0;

            for(int i = 0 ; i <= Math.abs(N - M); i ++){
                int sum = 0;

                if(N < M){
                    for(int j = 0 ; j < N ; j ++){
                        int current = Ai[j] * Bi[j + i];
                        sum += current;
                    }
                }else if (N > M){
                    for(int j = 0 ; j < M ; j ++){
                        int current = Ai[j + i] * Bi[j];
                        sum += current;
                    }
                }
                if(max < sum){
                    max = sum;
                }
            }

            System.out.printf("#%d %d \n", test_case, max);
		}
	}
}