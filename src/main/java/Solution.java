import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int x : to_delete) {
            set.add(x);
        }
        Set<TreeNode> resSet = new HashSet<>();
        resSet.add(root);
        dfs(root, set, resSet, null, false);

        List<TreeNode> res = new ArrayList<>();
        for (TreeNode node : resSet) {
            res.add(node);
        }
        return res;
    }

    void dfs(TreeNode root, Set<Integer> set, Set<TreeNode> resSet, TreeNode prev, boolean left) {
        if (root == null) {
            return;
        }

        if (set.contains(root.val)) {
            resSet.remove(root);
            if (prev != null) {
                if (left) {
                    prev.left = null;
                } else prev.right = null;
            }
            if (root.left != null) {
                resSet.add(root.left);
                dfs(root.left, set, resSet, null, true);
            }
            if (root.right != null) {
                resSet.add(root.right);
                dfs(root.right, set, resSet, null, false);
            }

        } else {
            dfs(root.left, set, resSet, root, true);
            dfs(root.right, set, resSet, root, false);
        }
    }
}
