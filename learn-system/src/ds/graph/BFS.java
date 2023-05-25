package ds.graph;

import java.util.*;

/**
 * 图的宽度优先遍历
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class BFS {
    public static List<Integer> bfs(Vertex start) {
        List<Integer> ans = new ArrayList<>();
        if (start == null) {
            return ans;
        }
        Deque<Vertex> queue = new LinkedList<>();
        Set<Vertex> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            ans.add(vertex.value);
            for (var next : vertex.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
        return ans;
    }
}
