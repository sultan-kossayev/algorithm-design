package treetraversal;

import utils.TreeBuilder.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Inorder (left->root->right) traversal of the tree in 3 ways
 * <p>
 * 1) recursive approach. Time O(n), space O(n)
 * 2) iterative approach using stack. Time O(n), space O(n)
 * 3) iterative approach using Morris approach. Time O(n), space O(1)
 */
public class InOrder {

    public List<Integer> recursiveTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();

        recursive(root, inorder);

        return inorder;
    }

    private void recursive(TreeNode node, List<Integer> state) {
        if (node == null) {
            return;
        }

        recursive(node.left, state);
        state.add(node.val);
        recursive(node.right, state);
    }

    public List<Integer> iterativeTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {

            // traverse left
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // capture node's value
            TreeNode top = stack.pop();
            list.add(top.val);

            // traverse right
            root = top.right;
        }

        return list;
    }

    public List<Integer> morrisTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        TreeNode curr = root;
        while (curr != null) {
            // if the current node has a left subtree go left
            if (curr.left != null) {
                // find rightmost node in the left subtree. It can be current's direct left child or it can sit
                // in the right subtree of the current's left child
                TreeNode pred = curr.left;
                // second condition ensures that rightmost node will not be equal to the current node
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                // if rightmost doesn't have a right child then it means that we are still going down
                // in tree traversal
                if (pred.right == null) {
                    // this link will help us later when we have to jump to the parent of the subtree
                    // although we are modifying the tree, we will remove this link later when we go up in tree traversal
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // at this point, we are going up thus we have to remove the link that we set above and thus
                    // reverting back tree's state to its initiate state
                    pred.right = null;

                    // if we are going up, then it means the current is a parent of the subtree, thus it can be visited
                    list.add(curr.val);
                    curr = curr.right;
                }

            } else {
                // current doesn't have a left child, thus current node can be visited because it is a parent of the current subtree
                list.add(curr.val);
                // as soon as the current is visited, we can move right
                curr = curr.right;
            }
        }

        return list;
    }

}
