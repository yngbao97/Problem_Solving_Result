import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		Map<Integer, Node> cntMap = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int idx = 0;
		while (st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			Node curr = cntMap.getOrDefault(tmp, new Node(tmp, idx++));
			curr.count++;
			cntMap.put(tmp, curr);
		}
		
		List<Node> nodes = new ArrayList<>(cntMap.values());
		Collections.sort(nodes);
		
		StringBuilder sb = new StringBuilder();
		for (Node n : nodes) {
			for (int i = 0; i < n.count; i++) {
				sb.append(n.num + " ");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}

class Node implements Comparable<Node>{
	int num;
	int order;
	int count;
	
	Node(int num, int order) {
		this.num = num;
		this.order = order;
		this.count = 0;
	}

	@Override
	public int compareTo(Node o) {
		if (Integer.compare(o.count, this.count) == 0) 
			return Integer.compare(this.order, o.order);
		return Integer.compare(o.count, this.count);
	}
}