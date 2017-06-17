package com.lihau;

import com.lihau.ds.ListNode;
import com.lihau.ds.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lhtan on 17/6/17.
 */
public class LeetCodeUtil {

    public static ListNode toLinkedList(Integer[] array) {
        if (array.length < 1) return null;
        ListNode ans = new ListNode(array[0]);
        ListNode tail = ans;
        for(int i=1;i<array.length;i++) {
            tail.next = new ListNode(array[i]);
            tail = tail.next;
        }
        return ans;
    }

    public static TreeNode toBinaryTree(Integer[] array) {
        if (array.length < 1) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode ans = new TreeNode(array[0]);
        queue.add(ans);
        for (Integer i=1;i<array.length && !queue.isEmpty();i+=2) {
            TreeNode node = queue.remove();
            if (array[i] != null) {
                node.left = new TreeNode(array[i]);
                queue.add(node.left);
            }
            if (i + 1 < array.length && array[i+1] != null) {
                node.right = new TreeNode(array[i+1]);
                queue.add(node.right);
            }
        }
        return ans;
    }

    public static Integer[] toArray(ListNode node) {
        List<Integer> ans = new ArrayList<>();
        while(node != null) {
            ans.add(node.val);
            node = node.next;
        }
        Integer[] array = new Integer[ans.size()];
        return ans.toArray(array);
    }

    public static void printList(ListNode n) {
        List<String> sb = new ArrayList<>();
        while(n != null) {
            sb.add(n.toString());
            n = n.next;
        }
        String listString = String.join(" -> ", sb);
        System.out.println(listString);
    }

    public static int getLengthOfList(ListNode n) {
        int length = 0;
        while (n != null) {
            n = n.next;
            length ++;
        }
        return length;
    }

    public static int getHeightOfTree(TreeNode n) {
        if (n == null) return 0;
        return Math.max(getHeightOfTree(n.left), getHeightOfTree(n.right)) + 1;
    }

    public static void compare(Integer[] a, Integer[] b) {
        if (a.length != b.length) {
            System.err.println("Length of both arrays are not equal");
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.err.print(String.format("arr2[%d] = %d != %d", i, b[i], a[i]));
            }
        }
    }
}
