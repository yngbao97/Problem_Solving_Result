import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int h;
	static int w;
	static int[][] room;
	static int[][] dist;
	static int answer;
	static List<int[]> dirt;
	static boolean[] used;
	static int count;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		h = Integer.parseInt(input[1]);
		w = Integer.parseInt(input[0]);

		while (h != 0 && w != 0) {

			room = new int[h+2][w+2];
			Arrays.fill(room[0], -1);
			Arrays.fill(room[h+1], -1);

			dirt = new ArrayList<>();
			dirt.add(new int[] {0, 0});
			for (int i = 1; i <= h; i++) {
				room[i][0] = room[i][w+1] = -1;
				char[] tmp = br.readLine().toCharArray();
				for (int j = 1; j <= w; j++) {
					char c = tmp[j-1];
					if (c == 'o') {
						room[i][j] = 0;
						dirt.set(0, new int[] {i, j});
					} else if (c == '*') {
						room[i][j] = dirt.size();
						dirt.add(new int[] {i, j});
					} else if (c == 'x') room[i][j] = -1;
				}
			}

			count = dirt.size();
			dist = new int[count][count];
			used = new boolean[count];
			for (int[] start : dirt) {
				bfs(start);
			}

			answer = Integer.MAX_VALUE;
			dfs(0, 1, 0);
			if (answer == Integer.MAX_VALUE) bw.write("-1\n");
			else bw.write(String.valueOf(answer) + "\n");

			input = br.readLine().split(" ");
			h = Integer.parseInt(input[1]);
			w = Integer.parseInt(input[0]);
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int currIdx, int cnt, int sum) {
		if(sum >= answer) return;

		if(cnt >= count) {
			answer = sum;
			return;
		}

		for (int i = 1; i < count; i++) {
			if (used[i] || dist[currIdx][i] == 0) continue;

			used[i] = true;
			dfs(i, cnt + 1, sum + dist[currIdx][i]);
			used[i] = false;
		}
	}

	public static void bfs (int[] start) {
		boolean[][] visited = new boolean[h+2][w+2];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;

		int depth = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] curr = queue.poll();

				if (room[curr[0]][curr[1]] > 0) dist[room[start[0]][start[1]]][room[curr[0]][curr[1]]] = depth;

				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (room[nr][nc] < 0 || visited[nr][nc]) continue;

					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
			depth++;
		}
	}
}