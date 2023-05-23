package sets;

import java.util.*;

/**
 * https://leetcode.cn/problems/number-of-islands/
 * 岛屿数量（岛问题）
 * @author HuanyuLee
 * @date 2023/5/23
 */
public class Code0200_NumberOfIslands {
    /**
     * 递归方法
     * @param grid 由'1'和'0'组成的的矩阵
     * @return 岛屿数量
     */
    public static int numIslands(char[][] grid) {
        int isLands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    isLands++;
                    infect(grid, i, j);
                }
            }
        }
        return isLands;
    }

    /**
     * 从(i,j)这个位置出发，把所有连成一片的'1'改成0
     */
    private static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 0;               // 感染标记
        infect(grid, i - 1, j);     // 感染左边
        infect(grid, i + 1, j);     // 感染右边
        infect(grid, i, j - 1);     // 感染上边
        infect(grid, i, j + 1);     // 感染下边
    }


    /**
     * 并查集方法（使用哈希表实现）
     * @param grid 由'1'和'0'组成的的矩阵
     * @return 岛屿数量
     */
    public static int numIslands2(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        DisjointSet set = new DisjointSet(dotList);
        // 处理第一行
        for (int j = 1; j < col; j++) {
            // 如果我自己是'1'并且我左边也是'1'，合并
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                set.union(dots[0][j - 1], dots[0][j]);
            }
        }
        // 处理第一列
        for (int i = 1; i < row; i++) {
            // 如果我自己是'1'并且我上边也是'1'，合并
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                set.union(dots[i - 1][0], dots[i][0]);
            }
        }
        // 处理剩余行列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    // 如果我自己是'1'并且我左边也是'1'，合并
                    if (grid[i][j - 1] == '1') {
                        set.union(dots[i][j - 1], dots[i][j]);
                    }
                    // 如果我自己是'1'并且我上边也是'1'，合并
                    if (grid[i - 1][j] == '1') {
                        set.union(dots[i - 1][j], dots[i][j]);
                    }
                }
            }
        }
        // 注意，虽然上面的3个for循环看似很多，但是优化了常数时间
        // 省去了复杂的判断，不用担心是否越界，从而只进行合并操作
        return set.sets();
    }


    /**
     * 并查集方法（使用数组实现）
     * @param grid 由'1'和'0'组成的的矩阵
     * @return 岛屿数量
     */
    public static int numIslands3(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        UnionFind set = new UnionFind(grid);
        // 处理第一行
        for (int j = 1; j < col; j++) {
            // 如果我自己是'1'并且我左边也是'1'，合并
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                set.union(0, j - 1, 0, j);
            }
        }
        // 处理第一列
        for (int i = 1; i < row; i++) {
            // 如果我自己是'1'并且我上边也是'1'，合并
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                set.union(i - 1, 0, i, 0);
            }
        }
        // 处理剩余行列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    // 如果我自己是'1'并且我左边也是'1'，合并
                    if (grid[i][j - 1] == '1') {
                        set.union(i, j - 1, i, j);
                    }
                    // 如果我自己是'1'并且我上边也是'1'，合并
                    if (grid[i - 1][j] == '1') {
                        set.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return set.sets();
    }

    // 为了测试
    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] grid = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return grid;
    }

    // 为了测试
    public static char[][] copy(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            System.arraycopy(grid[i], 0, ans[i], 0, col);
        }
        return ans;
    }

    public static void main(String[] args) {
        int row = 1000;
        int col = 1000;
        char[][] grid1 = generateRandomMatrix(row, col);
        char[][] grid2 = copy(grid1);
        char[][] grid3 = copy(grid1);
        long start = 0;
        long end = 0;

        System.out.println(">>>三种方法的运行结果和运行时间...");
        System.out.println("随机生成的二维矩阵规模: " + row + " * " + col + "\n");

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands(grid1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms\n");

        start = System.currentTimeMillis();
        System.out.println("并查集（哈希表实现）的运行结果: " + numIslands2(grid2));
        end = System.currentTimeMillis();
        System.out.println("并查集（哈希表实现）的运行时间: " + (end - start) + " ms\n");

        start = System.currentTimeMillis();
        System.out.println("并查集（数组实现）的运行结果: " + numIslands3(grid3));
        end = System.currentTimeMillis();
        System.out.println("并查集（数组实现）的运行时间: " + (end - start) + " ms");
    }
}

class Dot {
}    // 为了区分不同的'1'，使用内存地址

class DisjointSet {
    private HashMap<Dot, Dot> father = new HashMap<>();
    private HashMap<Dot, Integer> size = new HashMap<>();

    public DisjointSet(List<Dot> values) {
        for (Dot v : values) {
            father.put(v, v);
            size.put(v, 1);
        }
    }

    /**
     * 查询v所在集合的代表
     * @param v 待查询节点
     * @return v所在集合的代表节点
     */
    public Dot find(Dot v) {
        Deque<Dot> path = new LinkedList<>();
        while (v != father.get(v)) {
            path.push(v);
            v = father.get(v);
        }
        // 路径压缩，将v到根节点路径上的节点直接连接到根节点，降低树高（优化二）
        while (!path.isEmpty()) {
            father.put(path.pop(), v);
        }
        return v;
    }

    /**
     * 将a、b代表的集合合并为同一个集合
     * @param a a代表的集合
     * @param b b代表的集合
     */
    public void union(Dot a, Dot b) {
        Dot repA = find(a);                   // a的代表节点
        Dot repB = find(b);                   // b的代表节点
        if (repA != repB) {
            int sizeA = size.get(repA);
            int sizeB = size.get(repB);
            Dot big = sizeA >= sizeB ? repA : repB;
            Dot small = big == repA ? repB : repA;
            father.put(small, big);         // 小集合挂在大集合上（优化一）
            size.put(big, sizeA + sizeB);   // 更新大集合的大小记录
            size.remove(small);             // 移除小集合的大小记录
        }
    }

    /**
     * 并查集的集合总数
     */
    public int sets() {
        return size.size();
    }
}

class UnionFind {
    private int[] parent;
    private int[] size;
    private int[] path;
    private int col;
    private int sets;

    public UnionFind(char[][] grid) {
        col = grid[0].length;
        int row = grid.length;
        int n = row * col;      // 假设没溢出
        parent = new int[n];
        size = new int[n];
        path = new int[n];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    int i = index(r, c);
                    parent[i] = i;
                    size[i] = 1;
                    sets++;
                }
            }
        }
    }

    // r行c列的位置的映射 (r, c) -> idx
    private int index(int r, int c) {
        return r * col + c;
    }

    public int find(int idx) {
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

    public void union(int r1, int c1, int r2, int c2) {
        int f1 = find(index(r1, c1));
        int f2 = find(index(r2, c2));
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

    public int sets() {
        return sets;
    }
}


// >>>三种方法的运行结果和运行时间...
// 随机生成的二维矩阵规模: 1000 * 1000

// 感染方法的运行结果: 65923
// 感染方法的运行时间: 23 ms

// 并查集（哈希表实现）的运行结果: 65923
// 并查集（哈希表实现）的运行时间: 499 ms

// 并查集（数组实现）的运行结果: 65923
// 并查集（数组实现）的运行时间: 32 ms