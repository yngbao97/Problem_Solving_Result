import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static List<Node>[] adj;
	static int[] dist;
	static boolean[] visited;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();	// 정점의 개수
		int E = sc.nextInt();	// 간선의 개수
		adj = new List[V+1];
		dist = new int[V+1];
		visited = new boolean[V+1];
		
		for (int i = 1; i <= V; i++) {
			dist[i] = INF;
		}
		
		int start = sc.nextInt();	// 시작 정점 번호
		
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int v = sc.nextInt();
			
			adj[st].add(new Node(ed, v));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		visited[start] = true;
		
		for (Node n : adj[start]) {
			if (dist[n.ed] > n.v) {
				dist[n.ed] = n.v;
				pq.add(n);
			}
		}
		
		while (!pq.isEmpty()) {
			
			Node curr = pq.poll();
			
			if (visited[curr.ed]) continue;
			visited[curr.ed] = true;
			
			for (Node n : adj[curr.ed]) {
				if (!visited[n.ed] && dist[n.ed] > dist[curr.ed]+ n.v) {
					dist[n.ed] = dist[curr.ed]+ n.v;
					pq.add(new Node(n.ed, dist[n.ed]));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
		sc.close();
	}
}

class Node implements Comparable<Node>{
	int ed;
	int v;
	
	Node(int ed, int v) {
		this.ed = ed;
		this.v = v;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(v, o.v);
	}
}