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

        int[] prefixSum = new int[n+1];
        int[] dp = new int[n+1];
        int minIdx = 0;
        int answer = -1000;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i-1] + Integer.parseInt(st.nextToken());
            dp[i] = prefixSum[i] - prefixSum[minIdx];
            answer = Math.max(answer, dp[i]);
            if (prefixSum[minIdx] > prefixSum[i]) minIdx = i;
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}