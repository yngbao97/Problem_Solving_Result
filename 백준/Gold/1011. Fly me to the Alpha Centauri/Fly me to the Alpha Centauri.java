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
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int gap = y - x;
            int answer = getCnt(gap);
            bw.write(String.valueOf(answer) + "\n");
        }

		bw.flush();
		bw.close();
		br.close();
	}

    private static int getCnt(int gap) {

        for (int i = 1; i < 100000; i++) {
            long dist = (long) Math.pow(i, 2);
            if (dist >= gap) {
                long mid = dist - i;
                if (gap <= mid) return (i - 1) * 2;
                else return (i * 2) - 1;
            }
        }
        return 0;
    }
}