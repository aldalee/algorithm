package datastructure.linearlist;

import datastructure.linearlist.struct.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static util.Utils.*;

/**
 * 删除单链表中所有给定值的节点
 * @author HuanyuLee
 * @date 2022/10/1
 */
public class Code02_DeleteGivenValue {
    /**
     * 链表中删除指定的元素
     * @param head  头结点
     * @param value 节点值
     * @return 头结点
     */
    public static Node<String> removeValue(Node<String> head, String value) {
        while (head != null) {
            if (!Objects.equals(value, head.data)) {
                break;
            }
            head = head.next;
        }
        Node<String> prev = head;
        Node<String> cur = head;
        while (cur != null) {
            if (Objects.equals(cur.data, value)) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    // for test
    public static Node<String> testRemoveValue(Node<String> head, String value) {
        if (head == null) {
            return null;
        }
        List<Node<String>> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.removeIf(e -> Objects.equals(e.data, value));
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        return list.size() == 0 ? null : list.get(0);
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxSize = 1000;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            Node<String> head = generateRandomLinkedList(maxSize);
            String value = "" + (char) (Math.abs(randomNumber(25)) + 65);
            Node<String> head1 = removeValue(head, value);
            Node<String> head2 = testRemoveValue(head, value);
            if (head1 != head2 && head1 != null && head2 != null) {
                printNode(head);
                System.out.println("value = " + value);
                System.out.println("head1 = " + head1.data);
                System.out.println("head2 = " + head2.data);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
