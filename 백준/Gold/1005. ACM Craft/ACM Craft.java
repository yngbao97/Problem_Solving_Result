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
		
		int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());   // 건물 개수
            int k = Integer.parseInt(st.nextToken());   // 규칙 개수
            Building[] buildings = new Building[n+1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= n; i++) {
                buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int before = Integer.parseInt(st.nextToken());
                int after = Integer.parseInt(st.nextToken());

                buildings[before].next.add(after);
                buildings[after].enter++;
            }
            int w = Integer.parseInt(br.readLine());    // 건설해야하는 건물 번호

            PriorityQueue<Building> pq = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                if (buildings[i].enter == 0) pq.add(buildings[i]);
            }

            int answer = 0;
            while (!pq.isEmpty()) {

                Building curr = pq.poll();

                if (curr.num == w) {
                    answer = curr.delay;
                    break;
                };

                for (int i : curr.next) {
                    Building next = buildings[i];
                    if (--next.enter == 0) {
                        next.delay += curr.delay;
                        pq.add(next);
                    }
                }
            }

            bw.write(String.valueOf(answer) + "\n");
        }
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class Building implements Comparable<Building> {
    int num;
    int delay;
    int enter;
    List<Integer> next;

    Building() {}
    Building(int num, int delay) {
        this.num = num;
        this.delay = delay;
        this.enter = 0;
        this.next = new ArrayList<>();
    }

    @Override
    public int compareTo(Building o) {
        return Integer.compare(this.delay, o.delay);
    }

    @Override
    public String toString() {
        return num + ": " + delay + "초, 진입차수: " + enter + ", 다음순서: " + next;
    }
}