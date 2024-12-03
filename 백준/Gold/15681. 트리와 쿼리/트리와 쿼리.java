import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static List<Integer>[] adj;
	static int[] tree;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int root = Integer.parseInt(input[1]);
		int q = Integer.parseInt(input[2]);
		
		adj = new List[N+1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		tree = new int[N+1];
		makeTree(root, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int node = Integer.parseInt(br.readLine());
			sb.append(tree[node] + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	private static int makeTree(int curr, int parent) {
		int count = 1;
		for (int node : adj[curr]) {
			if (node != parent) count += makeTree(node, curr);
		}
		
		return tree[curr] = count;
	}
}