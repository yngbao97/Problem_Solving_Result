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

        int[][] dp = new int[15][15];
        for (int i = 0; i < 15; i++) dp[0][i] = i;
        for (int i = 1; i < 15; i++) {
            int sum = 0;
            for (int j = 1; j < 15; j++) {
                sum += dp[i-1][j];
                dp[i][j] = sum;
            }
        }
		int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[k][n]) + "\n");
        }
		
		bw.flush();
		bw.close();
		br.close();
	}
}