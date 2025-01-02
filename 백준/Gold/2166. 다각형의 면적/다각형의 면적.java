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
		long[][] point = new long[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			point[i] = new long[] {x, y};
		}

		double sum = 0;
		for (int i = 1; i < n-1; i++) {
			sum += getArea(point[0], point[i], point[i+1]);
		}

		double answer = Math.abs(sum) / 2.00;
		bw.write(String.format("%.1f", answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static double getArea(long[] fixed, long[] first, long[] second) {
		return (fixed[0]*first[1] + first[0]*second[1] + second[0]*fixed[1]) - (first[0]*fixed[1] + second[0]*first[1] + fixed[0]*second[1]);
	}
}