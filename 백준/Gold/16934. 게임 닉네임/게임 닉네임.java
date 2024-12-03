import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Node root = new Node();
		
		for (int i = 0; i < N; i++) {
			
			char[] input = br.readLine().toCharArray();
			int idx = 0;
			boolean flag = false;
			Node curr = root;
			
			while (idx < input.length) {
				Node next = curr.child.getOrDefault(input[idx], null);
				
				if (next != null) {
					curr = next;
					sb.append(curr.alpha);
					idx++;
					continue;
				} else {
					curr = getNext(curr, input[idx++]);
					sb.append(curr.alpha + "\n");
					flag = true;
					
					while (idx < input.length) {
						curr = getNext(curr, input[idx++]);
					}
				}
			}
			curr.end++;
			if (!flag) {
				int count = curr.end;
				sb.append(count > 1 ? count + "\n" : "\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static Node getNext(Node curr, char key) {
		curr.child.put(key, new Node(key));
		return curr.child.get(key);
	}
}

class Node {
	char alpha;
	Map<Character, Node> child;
	int end;
	
	Node(){
		child = new HashMap<>();
	}
	
	Node(char alpha) {
		this.alpha = alpha;
		child = new HashMap<>();
		int end = 0;
	}
}