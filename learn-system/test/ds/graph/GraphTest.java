package ds.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static ds.graph.BFS.bfs;
import static ds.graph.DFS.dfs;
import static ds.graph.GraphGenerator.createGraph;
import static ds.graph.TopologicalSort.sortedTopology;
import static ds.graph.mst.Kruskal.kruskalMST;
import static ds.graph.mst.Prim.primMST;
import static ds.graph.sp.Dijkstra.dijkstra;

public class GraphTest {
    Graph DIG, UND;     // 有向图、无向图

    @Before
    public void initGraph() {
        // matrix[i] = [src, dest, weight]
        int[][] matrix = {
                {1, 2, 10},
                {1, 3, 20},
                {1, 4, 18},
                {1, 6, 2},
                {2, 3, 5},
                {2, 5, 2},
                {3, 4, 1},
                {5, 3, 2},
                {6, 4, 6},
        };
        DIG = createGraph(matrix);
        int[][] matrix2 = {
                {1, 2, 10},
                {2, 1, 10},
                {1, 3, 20},
                {3, 1, 20},
                {1, 4, 18},
                {4, 1, 18},
                {1, 6, 2},
                {6, 1, 2},
                {2, 3, 5},
                {3, 2, 5},
                {2, 5, 2},
                {5, 2, 2},
                {3, 4, 1},
                {4, 3, 1},
                {5, 3, 2},
                {3, 5, 2},
                {6, 4, 6},
                {4, 6, 6},
        };
        UND = createGraph(matrix2);
    }

    @Test
    public void testBFS() {
        Vertex start = DIG.vertices.get(1);
        System.out.println("start = " + start.value);
        System.out.println("BFS = " + bfs(start));
    }

    @Test
    public void testDFS() {
        Vertex start = DIG.vertices.get(2);
        System.out.println("start = " + start.value);
        System.out.println("DFS = " + dfs(start));
    }

    @Test
    public void testSortedTopology() {
        List<Vertex> topology = sortedTopology(DIG);
        System.out.println("Topology = " + topology);
    }

    @Test
    public void testKruskalMST() {
        Set<Edge> mst = kruskalMST(UND);
        System.out.println("mst = " + mst);
        // 对于本例子，它是正确的，但是并不适用所有的有向图
        Set<Edge> mst2 = kruskalMST(DIG);
        System.out.println("mst2 = " + mst2);
    }

    @Test
    public void testPrimMST() {
        Set<Edge> mst = primMST(UND);
        System.out.println("mst = " + mst);
    }

    @Test
    public void testDijkstra() {
        Vertex start = DIG.vertices.get(1);
        Map<Vertex, Integer> distanceMap1 = dijkstra(start);
        int size = DIG.vertices.size();
        Map<Vertex, Integer> distanceMap2 = dijkstra(start, size);
        System.out.println(distanceMap1);
        System.out.println(distanceMap2);
    }
}
