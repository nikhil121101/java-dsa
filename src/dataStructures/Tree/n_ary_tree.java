package dataStructures.Tree;

import java.util.ArrayList;

public class n_ary_tree {
    private class TreeNode {
        int val;
        ArrayList<TreeNode> childs;
        TreeNode(int val) {
            this.val = val;
            childs = new ArrayList<TreeNode>();
        }
    }



}
