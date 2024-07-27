import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        ArrayList<String> arrSet = new ArrayList<>(set);

        Collections.sort(arrSet, (o1, o2) -> {
            int sort = Integer.compare(o1.length(), o2.length());
            if (sort == 0) return o1.compareTo(o2);
            else if (sort == 1) return 1;
            else return -1;
        });


        for (String i : arrSet) {
            System.out.println(i);
        }
    }
}