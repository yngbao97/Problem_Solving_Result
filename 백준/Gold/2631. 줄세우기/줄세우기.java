import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        for (int i = 0; i < n; i++) num[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (num[j] < num[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }

        bw.write(String.valueOf(n - max));
		bw.flush();
		bw.close();
		br.close();
	}
}