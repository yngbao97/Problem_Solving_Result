import java.util.*;
import java.lang.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static List<Node>[] adj;
	static int n;
	static final int INFINITE = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);		// 정점의 개수
		int e = Integer.parseInt(input[1]);		// 간선의 개수

		adj = new List[n+1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < e; i++) {
			String[] info = br.readLine().split(" ");

			int start = Integer.parseInt(info[0]);
			int end = Integer.parseInt(info[1]);
			int dist = Integer.parseInt(info[2]);

			adj[start].add(new Node(dist, end));
			adj[end].add(new Node(dist, start));
		}

		String[] dest = br.readLine().split(" ");
		int a = Integer.parseInt(dest[0]);
		int b = Integer.parseInt(dest[1]);

		int[] fromStart = dijkstra(1);
		int[] fromEnd = dijkstra(n);
		int[] fromA = dijkstra(a);

		if (!(fromStart[a] != INFINITE && fromStart[b] != INFINITE && fromStart[n] != INFINITE)) {
			bw.write("-1");
		} else {
			int answer = Math.min(fromStart[a] + fromA[b] + fromEnd[b], fromStart[b] + fromA[b] + fromEnd[a]);
			bw.write(String.valueOf(answer));
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static int[] dijkstra(int start) throws IOException {

		int[] dist = new int[n+1];
		Arrays.fill(dist, INFINITE);
		boolean[] visited = new boolean[n+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, start));
		dist[start] = 0;

		while(!pq.isEmpty()) {

			Node curr = pq.poll();

			if (visited[curr.end]) continue;
			visited[curr.end] = true;

			for (Node next : adj[curr.end]) {
				if ((!visited[next.end]) && dist[next.end] > curr.dist + next.dist) {
					pq.add(new Node(curr.dist + next.dist, next.end));
					dist[next.end] = curr.dist + next.dist;
				}
			}
		}

		return dist;
	}
}

class Node implements Comparable<Node>{

	int dist;
	int end;

	Node (int dist, int end) {
		this.dist = dist;
		this.end = end;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.dist, o.dist);
	}

	@Override
	public String toString() {
		return end + "까지 " + dist + "\n";
	}
}