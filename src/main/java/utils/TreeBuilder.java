package utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBuilder {

    public static TreeNode build(Integer... values) {
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        for (int i = 1; i < values.length; ) {

            TreeNode node = stack.poll();

            node.left = new TreeNode(values[i++]);
            node.right = new TreeNode(values[i++]);

            stack.add(node.left);
            stack.add(node.right);
        }

        return root;
    }

    public static class TreeNode {
        public TreeNode left;
        public TreeNode right;

        public int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

}
