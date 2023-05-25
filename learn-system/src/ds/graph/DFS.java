package ds.graph;

import java.util.*;

/**
 * 图的深度优先遍历
 * @author HuanyuLee
 * @date 2023/5/25
 */
public class DFS {
    public static List<Integer> dfs(Vertex start) {
        List<Integer> ans = new ArrayList<>();
        if (start == null) {
            return ans;
        }
        LinkedList<Vertex> stack = new LinkedList<>();
        Set<Vertex> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        ans.add(start.value);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            for (var next : vertex.nexts) {
                if (!set.contains(next)) {
                    stack.push(vertex);
                    stack.push(next);
                    set.add(next);
                    ans.add(next.value);
                    break;
                }
            }
        }
        return ans;
    }
}
