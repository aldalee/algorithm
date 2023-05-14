package ds.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 将单向链表按某值划分为左边小，中间相等，右边大的形式
 * 题目描述:
 *     给定一个链表，再给定一个整数pivot，请将链表调整为左部分都是值小于pivot的节点，
 *     中间部分都是值等于pivot的节点，右边部分都是大于pivot的节点。
 *     除此之外，对调整后的节点顺序没有更多要求。
 * 输入描述:
 *     第一行两个整数n和pivot，n表示链表的长度。
 *     第二行n个整数ai表示链表的节点。
 * 输出描述:
 *     请在给定的函数内返回链表的头指针。
 * 测试用例:
 *     5 3
 *     9 0 4 5 1
 *     1 0 4 5 9
 * 测试链接：
 *     https://www.nowcoder.com/questionTerminal/04fcabc5d76e428c8100dbd855761778
 * 提交注意: 将类名改成Main，去掉package。方法一、方法二全部AC
 * @author HuanyuLee
 * @date 2023/5/14
 */
public class SmallerEqualBigger {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 方法一: 把链表放入数组里，在数组上做partition（笔试用）
     * @param head  链表头结点
     * @param pivot 划分值
     * @return 划分后的链表头结点
     */
    public static ListNode bruteForcePartition(ListNode head, int pivot) {
        if (head == null) return null;
        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        ListNode[] nodeArr = new ListNode[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    private static void arrPartition(ListNode[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].val < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].val == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    private static void swap(ListNode[] nodeArr, int a, int b) {
        ListNode tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    /**
     * 方法二: 分成小、中、大三部分，再把各个部分之间串起来（面试用）
     * @param head  链表头结点
     * @param pivot 划分值
     * @return 划分后的链表头结点
     */
    public static ListNode partition(ListNode head, int pivot) {
        if (head == null || head.next == null) return head;
        ListNode lessHead = null, lessTail = null;
        ListNode equalHead = null, equalTail = null;
        ListNode moreHead = null, moreTail = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;       // 分离剩余节点
            head.next = null;       // 分离头节点
            if (head.val < pivot) {
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                } else {
                    lessTail.next = head;
                    lessTail = head;
                }
            } else if (head.val == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else {
                if (moreHead == null) {
                    moreHead = head;
                    moreTail = head;
                } else {
                    moreTail.next = head;
                    moreTail = head;
                }
            }
            head = next;            // 将头节点指向剩余节点
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = moreHead;
        }
        return lessHead != null ? lessHead : (equalHead != null ? equalHead : moreHead);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int pivot = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ListNode head = new ListNode(Integer.parseInt(st.nextToken()));
        ListNode p = head;
        for (int i = 1; i < n; i++) {
            p.next = new ListNode(Integer.parseInt(st.nextToken()));
            p = p.next;
        }
        head = partition(head, pivot);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
