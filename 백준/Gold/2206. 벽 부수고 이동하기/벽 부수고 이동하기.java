import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int[] dr = new int[]{-1, 0, 1, 0};
	static int[] dc = new int[]{0, 1, 0, -1};
	static int n;
	static int m;
	static int[][] map;
	static int[][][] dist;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		map = new int[n][m];
		dist = new int[2][n][m];

		// 맵 입력
		for (int i = 0; i < n; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = tmp[j] - '0';
			}
		}

		int answer = bfs();

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static int bfs() {

		boolean[][] visited = new boolean[n][m];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0});
		dist[0][0][0] = 1;

		while (!queue.isEmpty()) {

			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int broken = curr[2];

			if (r == n-1 && c == m-1) return dist[broken][r][c];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

				if (map[nr][nc] == 0) {
					if (dist[broken][nr][nc] == 0) {
						queue.offer(new int[] {nr, nc, broken});
						dist[broken][nr][nc] = dist[broken][r][c] + 1;
					}

				} else {
					if (broken == 0 && dist[1][nr][nc] == 0) {
						queue.offer(new int[] {nr, nc, 1});
						dist[1][nr][nc] = dist[broken][r][c] + 1;
					}
				}
			}
		}

		return -1;
	}
}