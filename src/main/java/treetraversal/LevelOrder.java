package treetraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static utils.TreeBuilder.TreeNode;

/**
 * Level order (layer by layer) traversal using queue data structure
 * <p>
 * Time O(n), space O(n)
 */
public class LevelOrder {

    public List<Integer> iterativeTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            // visit a node
            list.add(node.val);

            if (node.left != null) {
                q.offer(node.left);
            }

            if (node.right != null) {
                q.offer(node.right);
            }
        }

        return list;
    }
}
