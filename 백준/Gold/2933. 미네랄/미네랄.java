import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static boolean[][] mineral;
	static Queue<int[]> cluster;
	static int[] bottom;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] search = {1, 3, 2};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		mineral = new boolean[n+1][m];
		for (int i = n; i >= 1; i--) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (tmp[j] == 'x') mineral[i][j] = true;
			}
		}

		int t = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < t; i++) {
			int hieght = Integer.parseInt(st.nextToken());

//			System.out.println(hieght + "에 막대기 던짐");
			// 깨진 미네랄이 없는 경우
			int col = getBroken(hieght, i % 2 == 0);
			if (col < 0 || col >= m) {
//				System.out.println("깨진 미네랄 없음");
				continue;
			}
			mineral[hieght][col] = false;
//			System.out.println(hieght + ", " + col + "깨짐");
			// 깨진 미네랄과 연결된 윗, 옆 부분이 없는 경우, 또는 남은 클러스터의 가장 아래가 바닥인 경우
//			int[] start = getStart(hieght, col);
//			if (start[0] <= 1) {
////				System.out.println("깨졌지만 이미 바닥이거나, 옆, 윗부분 없음");
//				continue;
//			}

//			System.out.println("클러스터 탐색");
			if (separate(hieght, col)) {
				int gap = getGap();
//				System.out.println("클러스터 공중에 있어서 " + gap + "만큼 내릴거야");
				move(gap);
			}
//			else System.out.println("깨졌지만 클러스터 변함 없음");
		}

		StringBuilder sb = new StringBuilder();
//		for (int i = 1; i <= n; i++) {
		for (int i = n; i >= 1; i--) {
			for (int j = 0; j < m; j++) {
				if (mineral[i][j]) sb.append("x");
				else sb.append(".");
			}
			if (i > 1) sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void move(int gap) {
		int size = cluster.size();
		for (int cnt = 0; cnt < size; cnt++) {
			int[] curr = cluster.poll();
			mineral[curr[0]][curr[1]] = false;
			cluster.add(curr);
		}

		while (!cluster.isEmpty()) {
			int[] curr = cluster.poll();
			mineral[curr[0] - gap][curr[1]] = true;
		}
	}

	public static int getGap() {
//		System.out.println(Arrays.toString(bottom));
		int gap = mineral.length;
		for (int i = 0; i < bottom.length; i++) {
			if (bottom[i] == mineral.length) continue;
			int h = bottom[i] - 1;
			while (h > 0 && !mineral[h][i]) h--;
//			System.out.println(i + "번 열 : " + h + "에 바닥 또는 미네랄");
			gap = Math.min(gap, bottom[i] - h - 1);
		}
		return gap;
	}

	public static boolean separate(int row, int col) {
		boolean[][] checked = new boolean[mineral.length][mineral[0].length];
		for (int i = 0; i < 4; i++) {
			int r = row + dr[i];
			int c = col + dc[i];

			if (r < 1 || r >= mineral.length || c < 0 || c >= mineral[0].length
					|| checked[r][c] || !mineral[r][c]) continue;

//			System.out.println(r + ", " + c + "부터 클러스터 탐색");
			cluster = new ArrayDeque<>();
			bottom = new int[mineral[0].length];
			int bott = mineral.length;
			Arrays.fill(bottom, mineral.length);
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {r, c});
			checked[r][c] = true;
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();

//				System.out.print(Arrays.toString(curr)+ " ");
				// 같은 열 최저 값 갱신
				bott = Math.min(bott, curr[0]);
				bottom[curr[1]] = Math.min(bottom[curr[1]], curr[0]);
				cluster.add(new int[] {curr[0], curr[1]});

				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (nr < 1 || nr >= mineral.length || nc < 0 || nc >= mineral[0].length
							|| checked[nr][nc] || !mineral[nr][nc]) continue;
					checked[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
			if (bott > 1) return true;
		}
		return false;
	}

	public static int getBroken(int h, boolean fromLeft) {
		int broken = 0;
		// 던지는 높이에서 가장 먼저 만나는 미네랄 위치 구하기
		if (fromLeft) {
//			System.out.println("왼쪽에서");
			while (broken < mineral[h].length && !mineral[h][broken]) broken++;
		}
		else {
//			System.out.println("오른쪽에서");
			broken = mineral[h].length - 1;
			while (broken >= 0 && !mineral[h][broken]) broken--;
		}
		return broken;
	}
}