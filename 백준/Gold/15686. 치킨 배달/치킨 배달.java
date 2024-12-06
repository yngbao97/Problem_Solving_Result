import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int n;
	static int m;
	static int[][] map;
	static List<int[]> chicken;
	static List<int[]> houses;
	static boolean[] open;
	static int answer;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		map = new int[n][n];
		houses = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) houses.add(new int[] {i, j});
				else if (map[i][j] == 2) chicken.add(new int[] {i, j});
			}
		}

		answer = Integer.MAX_VALUE;
		open = new boolean[chicken.size()];

		comb(0, 0);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	private static void comb(int idx, int cnt) {

		if (cnt >= m) {
			answer = Math.min(answer, cal());
			return;
		}

		if (idx >= chicken.size()) return;

		open[idx] = true;
		comb(idx + 1, cnt + 1);
		open[idx] = false;
		comb(idx + 1, cnt);

	}

	private static int cal() {
		int result = 0;

		for (int[] house : houses) {

			int dist = Integer.MAX_VALUE;
			for (int i = 0; i < chicken.size(); i++) {
				if (open[i]) {
					dist = Math.min(dist, getDist(house, chicken.get(i)));
				}
			}
			result += dist;
		}

		return result;
	}

	private static int getDist(int[] house, int[] store) {
		return Math.abs(house[0] - store[0]) + Math.abs(house[1] - store[1]);
	}
}