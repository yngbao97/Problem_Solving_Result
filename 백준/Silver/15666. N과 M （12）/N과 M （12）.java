import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int M;
    static int[] cases;
    static List<Integer> distinct;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        cases = new int[M];

        Set<Integer> distinctNums = new HashSet<>();
        String[] nums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(nums[i]);
            distinctNums.add(num);
        }
        distinct = new ArrayList<>(distinctNums);
        Collections.sort(distinct);

        perm(0);

        bw.flush();
        bw.close();
        br.close();
    }

    static void perm(int idx) throws Exception {
        if (idx >= M) {
            for (int i = 0; i < M; i++) {
                bw.write(String.valueOf(cases[i]) + " ");
            }
            bw.write("\n");
            return;
        }

        for (int n : distinct) {
            if (idx > 0 && n < cases[idx-1]) continue;

            cases[idx] = n;
            perm(idx + 1);
        }
    }
}