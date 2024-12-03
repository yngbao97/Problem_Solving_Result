import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static List<Edge>[] adj;
	static int start;
	static int max;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+Main.class.getPackage().getName()+"/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int V = Integer.parseInt(br.readLine());

		adj = new List[V+1];
		for (int i = 1; i <= V; i++) {
			String[] inputs = br.readLine().split(" ");
			int nodeNum = Integer.parseInt(inputs[0]);
			adj[nodeNum] = new ArrayList<>();
			for (int j = 1; j < inputs.length - 1; j++) {
				int next = Integer.parseInt(inputs[j++]);
				int weight = Integer.parseInt(inputs[j]);
				adj[nodeNum].add(new Edge(next, weight));
			}
		}
		
		bfs(1, 0, 0);
		bfs(start, 0, 0);
		
		bw.append(String.valueOf(max));

		bw.flush();
		bw.close();
		br.close();

	}

	private static void bfs(int curr, int from, int dist) {
		if (dist > max) {
			start = curr;
			max = dist;
		}
		
		for (Edge e : adj[curr]) {
			if (e.next != from) bfs(e.next, curr, dist + e.weight);
		}
	}
}

class Edge {
	int weight;
	int next;
	
	Edge(int next, int weight) {
		this.weight = weight;
		this.next = next;
	}
}