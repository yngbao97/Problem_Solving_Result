import java.util.*;
import java.lang.*;

class Solution {
    
    static Node[] ns;
    static Set<Integer> trees;
    static Set<Integer> holjjak;
    static Set<Integer> yeok;
    public int[] solution(int[] nodes, int[][] edges) {
        ns = new Node[1_000_001];
        holjjak = new HashSet<>();
        yeok = new HashSet<>();
        trees = new HashSet<>();
        
        // 노드 초기화, 트리 초기화
        for (int n : nodes) {
            ns[n] = new Node(n);
            trees.add(n);
        }
        
        for (int[] e : edges) {
            ns[e[0]].child.add(e[1]);
            ns[e[1]].child.add(e[0]);
            
            union(findSet(e[0]), findSet(e[1]));
        }
        
        for (int n : nodes) {
            boolean isHol = Math.abs(n - ns[n].child.size()) % 2 == 0;
            
            int head = findSet(n);
            if ((isHol && !holjjak.contains(head)) 
                || (!isHol && !yeok.contains(head))) {
                
                // System.out.println(n + " " + isHol + " 드가자!!!");
                
                if (dfs(0, n, isHol)) {
                    if (isHol) {
                        // System.out.println(n + " 홀짝트리 추가-------------");
                        holjjak.add(head);
                    }
                    else {
                        // System.out.println(n + " 역홀짝트리 추가-------------");
                        yeok.add(head);
                    }
                };
            }
        }
        
        return new int[] {holjjak.size(), yeok.size()};
    }
    
    private static boolean dfs(int before, int node, boolean isHol) {
        // System.out.print(node + " - " + (ns[node].child.size() - 1) +  "(자식 수)");
        if (before != 0 
            && (Math.abs(node - (ns[node].child.size() - 1)) % 2 == 0) != isHol) return false;
        
        boolean flag = true;
        // System.out.println(node +" 의 자식 순회");
        
        for (int next : ns[node].child) {
            if (next == before) continue;
            boolean result = dfs(node, next, isHol);
            
            if (!result) {
                flag = result;
                break;
            }
        }
        
        return flag;
    }
    
    private static int findSet(int x) {
        if (x == ns[x].p) return x;
        return ns[x].p = findSet(ns[x].p);
    }
    
    private static void union(int x, int y) {
        ns[x].p = y;
        trees.remove(x);
    }
}

class Node {
    List<Integer> child;
    int p;
    
    Node (int p) {
        child = new ArrayList<>();
        this.p = p;
    }
}