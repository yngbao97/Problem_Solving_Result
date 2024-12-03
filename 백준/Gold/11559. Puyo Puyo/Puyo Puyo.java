import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1}; 
	
	static char[][] field;
	static int combo;
	static boolean[][] visited;
	public static void main(String[] args) {
		
		field = new char[12][6];
		combo = 0;
		
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 12; i++) {
			char[] tmp = sc.nextLine().toCharArray();
			for (int j = 0; j < 6; j++) {
				field[i][j] = tmp[j];
			}
		}
		
		while (true) {
			visited = new boolean[12][6];
			boolean flag = false;
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (field[i][j] == '.') continue;
					
					if(bfs(i, j)) flag = true;
				}
			}
			
//			System.out.println("=====터진 후=====");
//			print(field);
			
			if (flag) {
				combo++;
//				System.out.println("터진게 있어 콤보"+combo+"번째");
				for (int i = 0; i < 6; i++) {
					int bottom = 11;
					int last = 11;
					while (bottom > 0 && last > 0) {
						while (field[bottom][i] != '.' && bottom > 0) bottom--;
						last = bottom-1;
						if (bottom <= 0 || last < 0) break;
						
						while (field[last][i] == '.' && last > 0) last--;
						
						field[bottom][i] = field[last][i];
						field[last][i] = '.';
					}
				}
				
//				System.out.println("=====중력 후=====");
//				print(field);
			} else break;
		}
		
		System.out.println(combo);
		sc.close();
	}
	
	private static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
	private static boolean bfs(int i, int j) {
		
		char color = field[i][j];
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> block = new ArrayList<>();
		
		queue.add(i*6+j);
		visited[i][j] = true;
		
		while (!queue.isEmpty()) {
			int repeat = queue.size();
			
			for (int k = 0; k < repeat; k++) {
				int curr = queue.poll();
				block.add(curr);
				int r = curr/6;
				int c = curr%6;
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 &&
							!visited[nr][nc] && field[nr][nc] == color) {
						queue.add(nr*6+nc);
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		if (block.size() >= 4) {
//			System.out.println(color +"이웃찾기");
//			System.out.println(block.size()+"개 이웃");
			for (int l : block) {
				field[l/6][l%6] = '.';
			}
			return true;
		}
		return false;
	}
}