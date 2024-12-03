import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] p;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		double[][] stars = new double[N][2];
		p = new int[N];
		
		for (int i = 0; i < N; i++) {
			stars[i] = new double[] {sc.nextDouble(), sc.nextDouble()};
		}
		
		int edgeCnt = N*(N-1) / 2;
		Edge[] edges = new Edge[edgeCnt];
		int idx = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				edges[idx++] = new Edge(i, j, getDist(stars[i], stars[j]));
			}
		}
		
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
		
		Arrays.sort(edges);
		
		double answer = 0;
		int cnt = 0;
		for (Edge edge : edges) {
			
			int a = findSet(edge.st);
			int b = findSet(edge.ed);
			
			if (a == b) continue;
			
			p[b] = a;
			answer += edge.w;
			cnt++;
			if (cnt >= N-1) break;
		}
		
		System.out.println(Math.round(answer*100) / 100.0);
		sc.close();
	}

	private static int findSet(int x) {
		if (x == p[x]) return x;
		return p[x] = findSet(p[x]);
	}

	private static double getDist(double[] st, double[] ed) {
		return Math.sqrt(Math.pow(st[0] - ed[0], 2) + Math.pow(st[1] - ed[1], 2));
	}
}

class Edge implements Comparable<Edge>{
	int st;
	int ed;
	double w;
	
	public Edge(int st, int ed, double w) {
		this.st = st;
		this.ed = ed;
		this.w = w;
	}

	@Override
	public String toString() {
		return "Edge [st=" + st + ", ed=" + ed + ", w=" + w + "]";
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(w, o.w);
	}
}