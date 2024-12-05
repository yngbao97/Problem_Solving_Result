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
    static int[][] cheese;
    static int[][] air;
    static Queue<int[]> melting;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        cheese = new int[n][m];
        air = new int[n][m];
        melting = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkAir(0, 0);

        int hour = 0;
        while (!melting.isEmpty()) {

            hour++;
            int count = melting.size();

            for (int i = 0; i < count; i++) {

                int[] next = melting.poll();
                int r = next[0];
                int c = next[1];

                checkAir(r, c);
            }
        }

        bw.write(String.valueOf(hour));

		bw.flush();
		bw.close();
		br.close();
	}

    // 연결된 공기들을 처리
    private static void checkAir(int row, int col) {

        Queue<int[]> beAir = new ArrayDeque<>();
        beAir.offer(new int[] {row, col});
        cheese[row][col] = 2;

        while (!beAir.isEmpty()) {

            int[] curr = beAir.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m || cheese[nr][nc] == 2) continue;

                if (cheese[nr][nc] == 0) {
                    cheese[nr][nc] = 2;
                    beAir.offer(new int[] {nr, nc});
                } else if (cheese[nr][nc] == 1) {
                    if (++air[nr][nc] >= 2) {
                    	cheese[nr][nc] = 2;
                    	melting.offer(new int[] {nr, nc});
                    }
                }
            }
        }
    }
}