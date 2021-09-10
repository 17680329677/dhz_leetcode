package leetcode.LinkList;

public class ListNode {
    int val;
    ListNode next;

    /**
     * 无参构造方法
     */
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}
