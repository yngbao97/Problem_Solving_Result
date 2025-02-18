import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static boolean[] dr = {false, false, true, false};	// 행 역순이어야 하냐
	static boolean[] dc = {false, true, false, false};	// 열 역순이어야 하냐
	static boolean[] rFir = {false, true, false, true};	// 행 우선순회냐
	static int n;
	static int answer;
	static Deque<Integer> deque;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		answer = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, board[i][j]);
			}
		}

		dfs(0, board);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int cnt, int[][] board) {
//		System.out.println(cnt + "회차");
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
//		System.out.println();
		if (cnt >= 5) return;

		for (int d = 0; d < 4; d++) {
			int[][] copy = new int[n][];
			for (int i = 0; i < n; i++) copy[i] = Arrays.copyOf(board[i], n);
			boolean changed = false;

			for (int i = 0; i < n; i++) {
				deque = new ArrayDeque<>();
				for (int j = 0; j < n; j++) {
					if (rFir[d] && board[i][j] != 0) deque.addLast(board[i][j]);
					else if (!rFir[d] && board[j][i] != 0) deque.addLast(board[j][i]);
				}
				int[] newLine = rFir[d] ? getNew(dc[d]) : getNew(dr[d]);

				for (int j = 0; j < n; j++) {
					if (rFir[d] && copy[i][j] != newLine[j]) {
						copy[i][j] = newLine[j];
						changed = true;
					}
					else if (!rFir[d] && copy[j][i] != newLine[j]) {
						copy[j][i] = newLine[j];
						changed = true;
					}
				}
			}

			if (changed) dfs(cnt + 1, copy);
		}
	}

	public static int[] getNew(boolean deOrder) {
		int[] line = new int[n];
		int idx = deOrder ? n-1 : 0;
		int move = deOrder ? -1 : 1;

		int before = 0;
		while (!deque.isEmpty()) {
			int curr = poll(deOrder);
			if (curr != 0 && before == curr) {
				line[idx] = curr + curr;
				answer = Math.max(answer, line[idx]);
				before = 0;
				idx += move;
			} else {
				if (before != 0) {
					line[idx] = before;
					idx += move;
				}
				before = curr;
			}
		}
		if (before != 0) line[idx] = before;

//		System.out.println(Arrays.toString(line));
		return line;
	}

	public static int poll(boolean deOrder) {
		if (deOrder) return deque.pollLast();
		return deque.pollFirst();
	}
}