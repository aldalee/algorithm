package sets;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/number-of-islands-ii/
 * 免费测试链接: https://www.lintcode.com/problem/434/
 * LeetCode题目描述: https://leetcode.ca/2016-09-30-305-Number-of-Islands-II/
 * 岛屿数量II
 * @author HuanyuLee
 * @date 2023/5/23
 */
public class Code0305_NumberOfIslandsII {
    static class Point {        // LintCode节点定义
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] path;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            path = new int[len];
        }

        public int connect(int r, int c) {
            int idx = map(r, c);
            if (size[idx] == 0) {
                parent[idx] = idx;
                size[idx] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

        // r行c列的位置的映射 (r, c) -> idx
        private int map(int r, int c) {
            return r * col + c;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || c1 < 0 || c1 == col) return;
            if (r2 < 0 || r2 == row || c2 < 0 || c2 == col) return;
            int i = map(r1, c1);
            int j = map(r2, c2);
            if (size[i] == 0 || size[j] == 0) {
                return;
            }

            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        private int find(int idx) {
            int pdx = 0;
            while (idx != parent[idx]) {
                path[pdx++] = idx;
                idx = parent[idx];
            }
            for (pdx--; pdx >= 0; pdx--) {
                parent[path[pdx]] = idx;
            }
            return idx;
        }
    }

    // LeetCode
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    // LintCode
    public List<Integer> numIslands2(int m, int n, Point[] operators) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (Point opt : operators) {
            ans.add(uf.connect(opt.x, opt.y));
        }
        return ans;
    }
}
