package leetcode.LinkList;

public class Solution {

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        int length = 1;
        ListNode temp = head;
        while (temp.next != null) {
            length ++ ;
            temp = temp.next;
        }

        if (length % 2 == 0) {
            for (int i = 0; i < (length / 2); i++) {
                head = head.next;
            }
        } else {
            for (int i = 0; i < (length - 1) / 2; i++) {
                head = head.next;
            }
        }
        return head;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
