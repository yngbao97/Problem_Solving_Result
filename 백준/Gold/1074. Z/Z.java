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

		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		long r = Long.parseLong(inputs[1]);
		long c = Long.parseLong(inputs[2]);

		long answer = findOrder(N-1, r, c);
		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static long findOrder(int n, long r, long c) {
		if (n < 0) return 0;

		long count = 0;
		long length = (long) Math.pow(2, n);
		if (r + 1 > length) {
			count += (length * length * 2);
			r -= length;
		}
		if (c + 1 > length) {
			count += (length * length);
			c -= length;
		}
		count += findOrder(n - 1, r, c);

		return count;
	}
}