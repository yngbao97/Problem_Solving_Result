import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] p;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] adj = new int[N+1][N+1];
		int[] plan = new int[M];
		p = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adj[i][j] = sc.nextInt();
			}
			p[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			plan[i] = sc.nextInt();
		}
		
		int start = plan[0];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			
			int curr = queue.poll();
			if(visited[curr]) continue;
			
			for (int i = 1; i <= N; i++) {
				if(adj[curr][i] == 1) {
					if(findSet(curr) != findSet(i)) {
						unionSet(curr, i);
						queue.add(i);
					}
				}
			}
			
			visited[curr] = true;
		}
		
		String answer = "YES";
		for (int i = 0; i < M; i++) {
			if (p[plan[i]] != start) {
				answer = "NO";
			}
		}
		
		System.out.println(answer);
		sc.close();
	}

	private static void unionSet(int curr, int i) {
		p[i] = p[curr];
	}

	private static int findSet(int x) {
		if (p[x] != x) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
}