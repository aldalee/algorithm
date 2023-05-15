package list;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 * 复制带随机指针的链表
 * @author HuanyuLee
 * @date 2023/5/15
 */
public class Code0138_CopyListWithRandomPointer {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 方法一: 使用容器（笔试用）
     * @param head 原链表的头节点
     * @return 复制链表的头节点
     */
     public Node clone(Node head) {
        Map<Node, Node> map = new HashMap<>();          // <old node, copy node>
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);      // map.get(cur)为新节点
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 方法二: 迭代 + 节点拆分（面试用）
     * @param head 原链表的头节点
     * @return 复制链表的头节点
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Node next;
        // 将克隆节点插入原节点中间
        // 1 -> 2 -> 3 -> NULL
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> NULL
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy;
        // 每次遍历一对节点，设置拷贝节点的random指针
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // next方向上，分离新老链表
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
