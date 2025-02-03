import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static Long[] trees;
	static int m;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		trees = new Long[n];
		m = Integer.parseInt(input[1]);
		st = new StringTokenizer(br.readLine(), " ");
		long max = 0;
		for (int i = 0; i < n; i++) {
			trees[i] = Long.parseLong(st.nextToken());
			max = Math.max(max, trees[i]);
		}
		Arrays.sort(trees, Collections.reverseOrder());

		int answer = binarySearch(1, (int)max);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int binarySearch(int low, int high) {
		if (low > high) {
			return high;
		}

		int mid = (low + high) / 2;

		if (isPossible(mid)) return binarySearch(mid + 1, high);
		else return binarySearch(low, mid - 1);
	}

	private static boolean isPossible(int height) {
		long sum = 0;
		for (long tree : trees) {
			sum += (tree-height);
			if (sum >= m) return true;
		}
		return false;
	}
}