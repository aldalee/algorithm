package ds.list;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 * https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=196&tqId=39711&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj&difficulty=undefined&judgeStatus=undefined&tags=&title=260
 * 复制带随机指针的链表
 * @author HuanyuLee
 * @date 2023/5/15
 */
public class CopyListWithRandomPointer {
    static class Node {
        int val;
        Node next;
        //rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 方法一: 使用容器
     * @param head 原链表的头节点
     * @return 复制链表的头节点
     */
    public Node Clone(Node head) {
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

    // 递归版本
    Map<Node, Node> cachedNode = new HashMap<Node, Node>();

    public Node clone(Node head) {
        if (head == null) return null;
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = clone(head.next);
            headNew.random = clone(head.random);
        }
        return cachedNode.get(head);
    }

    /**
     * 方法二: 迭代 + 节点拆分
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
