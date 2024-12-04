import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());    // 도시(정점)의 개수
        int m = Integer.parseInt(br.readLine());    // 버스(간선)의 개수

        List<City>[] adj = new List[n+1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
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

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] prev = new int[n+1];

        PriorityQueue<City> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        dist[start] = 0;
        pq.offer(new City(0, start));

        while (!pq.isEmpty()) {
            City curr = pq.poll();

            if (curr.cost > dist[curr.dest]) continue;

            for (City next : adj[curr.dest]) {
                int newCost = curr.cost + next.cost;
                if (newCost < dist[next.dest]) {
                    dist[next.dest] = newCost;
                    prev[next.dest] = curr.dest;
                    pq.offer(new City(newCost, next.dest));
                }
            }
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != 0; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        // Write output
        bw.write(dist[end] + "\n");
        bw.write(path.size() + "\n");
        for (int city : path) {
            bw.write(city + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class City {
        int cost;
        int dest;

        City(int cost, int dest) {
            this.cost = cost;
            this.dest = dest;
        }
    }
}