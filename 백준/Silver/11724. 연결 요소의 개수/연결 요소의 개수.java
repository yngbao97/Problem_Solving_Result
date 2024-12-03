import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		List<Integer>[] adj = new List[N+1];
		boolean[] visited = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		int idx = 1;
		int answer = 0;
		
		while (idx <= N) {

			queue.add(idx);
			visited[idx] = true;
			
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				
				for (int next : adj[curr]) {
					if (visited[next]) continue;
					queue.add(next);
					visited[next] = true;
				}
			}
			
			while (idx <= N && visited[idx]) idx++;
			answer++;
		}
		
		System.out.println(answer);
		sc.close();

	}
}