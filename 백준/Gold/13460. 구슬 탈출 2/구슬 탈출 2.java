import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] board;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        board = new int[n][m];
        State start = new State();

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (chars[j] == '#') board[i][j] = 1;
                else if (chars[j] == 'O') board[i][j] = -1;
                else if (chars[j] == 'R') {
                    start.redR = i;
                    start.redC = j;
                } else if (chars[j] == 'B') {
                    start.blueR = i;
                    start.blueC = j;
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }
//        System.out.println();

        Queue<State> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        queue.add(start);
        visited[start.redR][start.redC][start.blueR][start.blueC] = true;

        int cnt = 0;
        int answer = -1;
        out: while (!queue.isEmpty() && cnt <= 10) {

//            System.out.println(cnt+ "-----회차-----");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                State curr = queue.poll();

                if (board[curr.redR][curr.redC] < 0) {
//                    System.out.print("red: "+ curr.redR + ", " + curr.redC);
//                    System.out.println(", blue: " + curr.blueR + ", " + curr.blueC);
                    answer = cnt;
                    break out;
                }

                board[curr.redR][curr.redC] = 2;
                board[curr.blueR][curr.blueC] = 3;

                for (int d = 0; d < 4; d++) {
//                    System.out.println(d + "방향");
                    int[] nRed = moveNext(curr.redR, curr.redC, d);
                    int[] nBlue = moveNext(curr.blueR, curr.blueC, d);
                    nRed = moveNext(nRed[0], nRed[1], d);

                    if (!visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] && board[nBlue[0]][nBlue[1]] >= 0) {
//                        for (int k = 0; k < n; k++) {
//                            System.out.println(Arrays.toString(board[k]));
//                        }
//                        System.out.print("red: "+ curr.redR + ", " + curr.redC);
//                        System.out.print(", blue: " + curr.blueR + ", " + curr.blueC + "에서 ");
//                        System.out.print("red: "+ Arrays.toString(nRed));
//                        System.out.println(", blue: " + Arrays.toString(nBlue) + "로 이동");
                        queue.add(new State(nRed[0], nRed[1], nBlue[0], nBlue[1]));
                        visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] = true;
                    }

                    if (board[nRed[0]][nRed[1]] > 0) board[nRed[0]][nRed[1]] = 0;
                    if (board[nBlue[0]][nBlue[1]] > 0) board[nBlue[0]][nBlue[1]] = 0;
                    board[curr.redR][curr.redC] = 2;
                    board[curr.blueR][curr.blueC] = 3;
                }

                board[curr.redR][curr.redC] = 0;
                board[curr.blueR][curr.blueC] = 0;
            }

            cnt++;
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

    public static int[] moveNext(int r, int c, int d) {
        int[] next = new int[] {r, c};
        if (board[r][c] < 0) return next;

//        for (int i = 0; i < board.length; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }

        while (board[next[0] + dr[d]][next[1] + dc[d]] <= 0 && board[next[0]][next[1]] >= 0) {
            next[0] += dr[d];
            next[1] += dc[d];
        }

//        System.out.println(board[r][c] + "를" + r + ", " + c + "에서" + Arrays.toString(next) + "로 이동");
        if (board[next[0]][next[1]] == 0) {
            board[next[0]][next[1]] = board[r][c];
        }
        if (!(r == next[0] && c == next[1])) board[r][c] = 0;
        return next;
    }
}

class State {
    int redR;
    int redC;
    int blueR;
    int blueC;

    State () {}
    State(int redR, int redC, int blueR, int blueC) {
        this.redR = redR;
        this.redC = redC;
        this.blueR = blueR;
        this.blueC = blueC;
    }
}