package treetraversal;

import utils.TreeBuilder.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Preorder (root->left->right) traversal of the tree in 3 ways
 * <p>
 * 1) recursive approach. Time O(n), space O(n)
 * 2) iterative approach using stack. Time O(n), space O(n)
 * 3) iterative approach using Morris approach. Time O(n), space O(1)
 */
public class PreOrder {

    public List<Integer> recursiveTraversal(TreeNode root) {
        List<Integer> state = new ArrayList<>();

        recursive(root, state);

        return state;
    }

    private void recursive(TreeNode curr, List<Integer> state) {
        if (curr == null) {
            return;
        }

        state.add(curr.val);

        recursive(curr.left, state);
        recursive(curr.right, state);
    }

    public List<Integer> iterativeTraversal(TreeNode root) {
        List<Integer> state = new ArrayList<>();
        if (root == null) {
            return state;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            state.add(root.val);

            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }

        return state;
    }

    public List<Integer> morrisTraversal(TreeNode root) {
        List<Integer> state = new ArrayList<>();

        TreeNode curr = root;
        while (curr != null) {
            // if current node has a left child go left
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
                    // we are done with current node and ready to move left but first we have to visit the current node
                    state.add(curr.val);
                    curr = curr.left;
                } else {
                    // at this point, we are going up thus we have to remove the link that we set above and thus
                    // reverting back tree's state to its initiate state
                    pred.right = null;
                    // just going right, without visiting any node
                    curr = curr.right;
                }
            } else {
                // current doesn't have a left child, thus current node can be visited
                state.add(curr.val);
                curr = curr.right;
            }
        }

        return state;
    }
}
