package ds.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * https://www.luogu.com.cn/problem/P3366
 * P3366 【模板】最小生成树
 * 解法一: 使用Kruskal算法
 * @author HuanyuLee
 * @date 2023/5/26
 */
public class LuoGuP3366_1 {
    static int[][] graph = new int[200001][3];
    static int[] father = new int[5001];
    static int[] size = new int[5001];

    static int find(int vertex) {
        int root = vertex;
        while (root != father[root]) {
            root = father[root];
        }
        while (vertex != root) {
            int next = father[vertex];
            father[vertex] = root;
            vertex = next;
        }
        return root;
    }

    // graph[i] = [src, dest, weight]
    private static void kruskal(int n) {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));
        int sum = 0;
        for (int[] edge : graph) {
            int f1 = find(edge[0]);
            int f2 = find(edge[1]);
            if (f1 != f2) {                 // 并查集合并
                if (size[f1] > size[f2]) {
                    father[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    father[f1] = f2;
                    size[f2] += size[f1];
                }
                sum += edge[2];
            }
        }
        System.out.println(size[find(1)] == n ? sum : "orz");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }
        kruskal(n);
    }
}
