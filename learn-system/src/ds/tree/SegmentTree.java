package ds.tree;

/**
 * 线段树
 * @author HuanyuLee
 * @date 2023/12/14
 */
public class SegmentTree {
    private int MAXN;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    public SegmentTree(int[] origin) {
        MAXN = origin.length + 1;
        arr = new int[MAXN];
        System.arraycopy(origin, 0, arr, 1, MAXN - 1);
        sum = new int[MAXN << 2];
        lazy = new int[MAXN << 2];
        change = new int[MAXN << 2];
        update = new boolean[MAXN << 2];
    }

    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int m = l + ((r - l) >> 1);
        build(l, m, rt << 1);
        build(m + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

    public void add(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            sum[rt] += C * (r - l + 1);
            lazy[rt] += C;
            return;
        }
        int m = l + ((r - l) >> 1);
        pushDown(rt, m - l + 1, r - m);
        if (L <= m) {
            add(L, R, C, l, m, rt << 1);
        }
        if (R > m) {
            add(L, R, C, m + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            update[rt] = true;
            change[rt] = C;
            sum[rt] = C * (r - l + 1);
            lazy[rt] = 0;
            return;
        }
        int m = ((r - l) >> 1) + l;
        pushDown(rt, m - l + 1, r - m);
        if (l <= m) {
            update(L, R, C, l, m, rt << 1);
        }
        if (r > m) {
            update(L, R, C, m + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public long query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int m = (l + r) >> 1;
        pushDown(rt, m - l + 1, r - m);
        long ans = 0;
        if (L <= m) {
            ans += query(L, R, l, m, rt << 1);
        }
        if (R > m) {
            ans += query(L, R, m + 1, r, rt << 1 | 1);
        }
        return ans;
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    /**
     * 分发策略
     * @param rt 父节点
     * @param ln 左子树元素节点个数
     * @param rn 右子树元素节点个数
     */
    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1 | 1] += lazy[rt] * rn;
            lazy[rt] = 0;
        }
    }


    public static void main(String[] args) {

    }
}
