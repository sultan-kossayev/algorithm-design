package treetraversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static utils.TreeBuilder.TreeNode;

/**
 * Postorder (left->right->root) traversal of the tree in 2 ways
 * <p>
 * 1) recursive approach. Time O(n), space O(n)
 * 2) iterative approach using stack. Time O(n), space O(n)
 */
public class PostOrder {

    public List<Integer> recursiveTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();

        recursive(root, postorder);

        return postorder;
    }

    private void recursive(TreeNode node, List<Integer> state) {
        if (node == null) {
            return;
        }

        recursive(node.left, state);
        recursive(node.right, state);

        state.add(node.val);
    }

    /**
     * Iterative approach is based on the idea that a parent of the subtree is visited just right after
     * its right child (if it exists) is visited.
     * Thus, whenever we are going to pop a top node from the stack we have to check 2 things:
     * 1. if the top node has a right child
     * 2. and its right child is not equal to previously visited node
     * <p>
     * If above conditions are false, then it means that we visited both left and right subtrees of the top node and it
     * can be popped out from the stack.
     * If above are true, then we have to first check out the right subtree and then come back to the current top node
     */
    public List<Integer> iterativeTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            TreeNode top = stack.peek();
            // we have to check whether the top node has a right child and it is not equal to the previously visited node
            // if above is true, we have to first traverse the right subtree before we pop the parent
            if (top.right != null && top.right != prev) {
                curr = top.right;
            } else {
                stack.pop(); // we can remove the node from the stack as we already processed left and right subtrees.
                prev = top; // we have to remember previously visited node, as we will compare it later
                list.add(top.val); // visit a node
                // curr reference is null at this point
            }
        }

        return list;
    }
}
