import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());	// 도시(정점)의 개수
		int m = Integer.parseInt(br.readLine());	// 버스(간선)의 개수

		@SuppressWarnings("unchecked")
		List<City>[] adj = (List<City>[]) new List[n+1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<City>();
		}

		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int dep = Integer.parseInt(input[0]);
			int dest = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);

			adj[dep].add(new City(cost, dest));
		}

		String[] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);

		int[] p = new int[n+1];	// 이전 도시 저장
		int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.add(new City(0, start));
		dist[start] = 0;

		while (!pq.isEmpty()) {

			City curr = pq.poll();

			if (curr.cost > dist[curr.dest]) continue;	// 이미 저장된 최소 거리보다 길면 패스

			for (City next : adj[curr.dest]) {
				if (dist[next.dest] > curr.cost + next.cost) {
					int newCost = curr.cost + next.cost;
					dist[next.dest] = newCost;
					pq.add(new City(newCost, next.dest));
					p[next.dest] = curr.dest;
				}
			}
		}

		Deque<Integer> stack = new ArrayDeque<>();
		int parent = end;
		while (parent != 0) {
			stack.push(parent);
			parent = p[parent];
		}

		int minDist = dist[end];
		int cnt = stack.size();

		bw.write(String.valueOf(minDist) + "\n");
		bw.write(String.valueOf(cnt) + "\n");
		while (!stack.isEmpty()) {
			bw.write(String.valueOf(stack.pop() + " "));
		}

		bw.flush();
		bw.close();
		br.close();
	}
}

class City implements Comparable<City> {
	int cost;
	int dest;

	City (int cost, int dest) {
		this.cost = cost;
		this.dest = dest;
	}

	@Override
	public int compareTo(City o) {
		return Integer.compare(this.cost, o.cost);
	}
}