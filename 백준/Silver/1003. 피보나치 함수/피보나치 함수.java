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

		int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][];
        dp[0] = new int[] {1, 0};
        dp[1] = new int[] {0, 1};
        for (int i = 2; i <= 40; i++) {
            dp[i] = new int[] {dp[i-1][0] + dp[i-2][0], dp[i-1][1] + dp[i-2][1]};
        }

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[n][0]) + " " + String.valueOf(dp[n][1]) + "\n");
        }

		bw.flush();
		bw.close();
		br.close();
	}
}