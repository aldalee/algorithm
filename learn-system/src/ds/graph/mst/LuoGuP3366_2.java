package ds.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.luogu.com.cn/problem/P3366
 * P3366 【模板】最小生成树
 * 解法二: 使用Prim算法
 * @author HuanyuLee
 * @date 2023/5/28
 */
public class LuoGuP3366_2 {
    public static void prim(ArrayList<int[]>[] graph, int n) {
        int sum = 0;
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        visited[1] = true;
        int cnt = 1;
        heap.addAll(graph[1]);
        while (!heap.isEmpty()) {
            int[] edge = heap.poll();
            int next = edge[0];
            int weight = edge[1];
            if (!visited[next]) {
                visited[next] = true;
                cnt++;
                sum += weight;
                heap.addAll(graph[next]);
            }
        }
        System.out.println(cnt == n ? sum : "orz");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        in.parseNumbers();      // 指定解析数字
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            @SuppressWarnings("unchecked")
            ArrayList<int[]>[] graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int u = (int) in.nval;
                in.nextToken();
                int v = (int) in.nval;
                in.nextToken();
                int w = (int) in.nval;
                graph[u].add(new int[]{v, w});
                graph[v].add(new int[]{u, w});
            }
            prim(graph, n);
        }
    }
}
