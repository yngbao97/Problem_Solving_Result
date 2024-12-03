import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[5][5];
		int[] bingo = new int[12];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		int cnt = 0;
		int answer = 0;
		out: for (int c = 1; c <= 25; c++) {
			int tmp = sc.nextInt();
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (tmp == board[i][j]) {
						if (++bingo[i] == 5) cnt++;
						if (++bingo[j+5] == 5) cnt++;
						if (i == j && ++bingo[10] == 5) cnt++;
						if (i+j == 4 && ++bingo[11] == 5) cnt++;
						if (cnt >= 3) {
							answer = c;
							break out;
						}
						continue out;
					}
				}
			}
		}
		
		System.out.println(answer);
		sc.close();

	}

}