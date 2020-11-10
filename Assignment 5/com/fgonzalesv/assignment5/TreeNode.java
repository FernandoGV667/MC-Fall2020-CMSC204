package com.fgonzalesv.assignment5;

/**
 * The external Tree Node for Linked Trees. The class consists of a reference to the data and a
 * reference to the left and right child.
 * 
 * @author Fernando Gonzales-Vigil
 */
public class TreeNode<T> {

  protected T data;
  protected TreeNode<T> left, right;

  /**
   * Creates a new TreeNode with left and right child set to null and data set to the dataNode
   * 
   * @param dataNode - data to insert in treenode
   */
  public TreeNode(T dataNode) {
    this.data = dataNode;
    this.left = this.right = null;
  }

  /**
   * Used for making deep copies
   * 
   * @param node - TreeNode to deepcopy
   */
  public TreeNode(TreeNode<T> node) {
    this.data = node.data;
    this.left = node.left;
    this.right = node.right;
  }

  /**
   * Return the data within this TreeNode
   * 
   * @return data from TreeNode
   */
  public T getData() {
    return this.data;
  }

}
