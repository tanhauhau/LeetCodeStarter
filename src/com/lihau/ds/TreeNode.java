package com.lihau.ds;

/**
 * Created by lhtan on 17/6/17.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
