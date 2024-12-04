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

        int N = Integer.parseInt(br.readLine());
        long[][] results = new long[N+1][N+1];
        long answer = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int size = st.countTokens();
            for (int j = 1; j <= size; j++) {
                int num = Integer.parseInt(st.nextToken());
                results[i][j] = Math.max(num + results[i-1][j-1], num + results[i-1][j]);

                if (i == N) answer = Math.max(answer, results[i][j]);
            }
        }

        bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}
}