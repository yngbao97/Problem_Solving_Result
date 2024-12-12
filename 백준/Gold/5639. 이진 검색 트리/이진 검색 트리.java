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

        int rootNum = Integer.parseInt(br.readLine());
        Node root = new Node(rootNum);

        Node curr = root;
        while(br.ready()){
            int tmp = Integer.parseInt(br.readLine());
            Node created = new Node(tmp);

            if (tmp < curr.num) {
                curr.left = created;
            } else if (tmp > curr.num) {
                while (curr.parent != null && tmp > curr.parent.num ) curr = curr.parent;
                while (curr.right != null && tmp > curr.right.num) curr = curr.right;
                if (curr.num > tmp) curr.left = created;
                else curr.right = created;
                curr.right = created;
            }
            created.parent = curr;
            curr = created;
        }

        dfs(root);

		bw.flush();
		bw.close();
		br.close();
	}

    private static void dfs(Node curr) throws IOException {

        if (curr.left != null) {
            dfs(curr.left);
        }

        if (curr.right != null) {
            dfs(curr.right);
        }

        bw.write(String.valueOf(curr.num) + "\n");
    }
}

class Node {
    int num;
    Node parent;
    Node left;
    Node right;

    Node (int num) {
        this.num = num;
    }
}