import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static StringBuilder sb;
	static final String DEPTH = "--";
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		Node root = new Node();
		
		for (int i = 0; i < N; i++) {
			String[] input = sc.nextLine().split(" ");
			int cnt = Integer.parseInt(input[0]);
			
			Node curr = root;
			here: for (int j = 1; j <= cnt; j++) {
				
				for (Node next : curr.child) {
					if (next.data.equals(input[j])) {
						curr = next;
						continue here;
					}
				}
				
				Node node = new Node(input[j]);
				curr.child.add(node);
				curr = node;
				
				// 나머지 털기
				while (++j <= cnt) {
					Node n = new Node(input[j]);
					curr.child.add(n);
					curr = n;
				}
			}
		}
		
		sb = new StringBuilder();
		dfs(root, 0);
		
		System.out.println(sb.toString());
		sc.close();
	}

	private static void dfs(Node root, int cnt) {
		
		Collections.sort(root.child);
		
		for (Node next : root.child) {
			for (int i = 0; i < cnt; i++) sb.append(DEPTH);
			sb.append(next.data + "\n");
			dfs(next, cnt+1);
		}
	}

}

class Node implements Comparable<Node>{
	String data;
	List<Node> child;
	
	Node() {
		this.child = new ArrayList<>();
	}
	
	Node(String data) {
		this.data = data;
		this.child = new ArrayList<>();
	}

	@Override
	public int compareTo(Node o) {
		return data.compareTo(o.data);
	}
}