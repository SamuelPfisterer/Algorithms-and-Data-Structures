import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.String;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
     In.open("public/small.in");
     Out.compareTo("public/small.out");

    int m = In.readInt(); // number of operations
    char[] operationArray = new char[m]; // operators (insertion, deletion, height query)
    int[] operandArray = new int[m];     // keys
    
    for (int i = 0; i < m; i++) {
      operationArray[i] = In.readChar();
      operandArray[i] = In.readInt();
    }
    
    BinaryTree T = new BinaryTree();
    
    for (int i = 0; i < m; i++) {
      switch (operationArray[i]) {
        case 'I': // insertion
          T.insert(operandArray[i]);
          break;
          
        case 'D': // deletion
          T.delete(operandArray[i]);
          break;

        case 'H': // height query 
          Out.println(T.heightOfKey(operandArray[i]));
          break;
      }
    }
    
    // Uncomment the following line if you want to read from a file
    In.close();
  }
}

class TreeNode {
    public int key;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;
    public int height;              //additional attribute height
  
    TreeNode(int key) {
      this.key = key;
      this.parent = null;
      this.left = null;
      this.right = null;
      this.height = 1;                //height of every new node is 1
    }
}

class BinaryTree {
  TreeNode root;
  
  BinaryTree() {
    this.root = null;
  }
  
  public void insert(int key) {
    if (root == null) {
      root = new TreeNode(key);
    } else {
      insert(root, key);
    }
  }
  
  public void insert(TreeNode node, int key) {
    if (key < node.key) {
      if (node.left != null) {
        insert(node.left, key);
      } else {
        node.left = new TreeNode(key);
        node.left.parent = node;
        insertHeightAdjust(node.left);            //adjust height node, starting at the added node
      }
    } else {
      if (node.right != null) {
        insert(node.right, key);
      } else {
        node.right = new TreeNode(key);
        node.right.parent = node;
        insertHeightAdjust(node.right);         //adjust height node, starting at the added node
      }
    }
  }
  
  public void delete(int key) {
    TreeNode node = search(key); // find the node to be deleted
    
    if (node == null) {
      return;
    }
      
    if (node.left == null) {
      //                                          start of adjusting
      node.height = node.height-1;
      deleteHeightAdjust(node);
      //                                          end of adjusting height
      if (node.right == null) { // no child
        if (node == root) {
          root = null;
        } else {
          if (node.parent.left == node) {
             
             
            node.parent.left = null;
          } else {
            
            
            
            node.parent.right = null;
          }
        }
      } else { // only right child
        if (node == root) {
          root = node.right;
          root.parent = null;
        } else {
          if (node.parent.left == node){
            node.parent.left = node.right;
            node.right.parent = node.parent;
          } else {
            node.parent.right = node.right;
            node.right.parent = node.parent;
          }
        }
      }
    } else {
      if (node.right == null) { // only left child
        //                                          start of adjusting
        node.height = node.height-1;
        deleteHeightAdjust(node);
        //                                          end of adjusting height
        if (node == root) {
          root = node.left;
          root.parent = null;
        } else {
          if (node.parent.left == node){
            node.parent.left = node.left;
            node.left.parent = node.parent;
          } else {
            node.parent.right = node.left;
            node.left.parent = node.parent;
          }
        }
      } else { // two children
        TreeNode swapNode = node.left;
        while (swapNode.right != null) {
          swapNode = swapNode.right;
        }
          
        node.key = swapNode.key;
        
          //                                          start of adjusting
          swapNode.height = swapNode.height -1;
          deleteHeightAdjust(swapNode);
          //                                          end of adjusting
                                           
        if (swapNode.left != null) { // swapNode has left Child
          if (swapNode.parent.left == swapNode) {
            swapNode.parent.left = swapNode.left;
            swapNode.left.parent = swapNode.parent;
          } else {
            swapNode.parent.right = swapNode.left;
            swapNode.left.parent = swapNode.parent;
          }
        } else { // swapNode has no child
          
          if (swapNode.parent.left == swapNode) {
            swapNode.parent.left = null;
          } else {
            swapNode.parent.right = null;
          }
        }
      }
    }
  }
  
  public TreeNode search(int key) {
    if (root == null) {
      return null;
    } else {
      return search(root, key);
    }
  }
  
  public TreeNode search(TreeNode node, int key) {
    if (node == null) {
      return null;
    } else if (key == node.key) {
      return node;
    } else {
      if (key < node.key) {
        return search(node.left, key);
      } else {
        return search(node.right, key);
      }
    }
  }
  
  public int heightOfKey(int key) {
    if(search(key)==null){
      return -1;
    }
    return search(key).height;
  }
                                            //additional methods
  public void insertHeightAdjust(TreeNode node){ //this method gets called when a node gets inserted to update the height of every node needed to be updated
    if(node.parent == null){ //we stop here
      return;
    }
    if (node.parent.height == node.height){ //the old height path of the parent node referred to this node
      node.parent.height = node.parent.height +1; //we increase the height +1
      insertHeightAdjust(node.parent); //call the method on the parent node
      
    }
  }
  
  public void deleteHeightAdjust(TreeNode node){//is called when a node gets deleted
    if (node.parent==null ){//we stop, cause we are at the root node
      return;
    }
    //we need to find out wheter we are left or right node of the parent
    
    //we are right node
    int l = 0;
    int r = 0;
    if(node.parent.left!=null){
      l = node.parent.left.height;
    }
    if(node.parent.right!=null){
      r = node.parent.right.height;
    }
    
    
    if(node.parent.left==node){
      if(node.height+1 > r){
        node.parent.height = node.parent.height -1;
        deleteHeightAdjust(node.parent);
      }
    }
    if(node.parent.right == node){
      if(node.height+1>l){
        node.parent.height = node.parent.height -1;
        deleteHeightAdjust(node.parent);
      }
    
    }
  
    
  }
} 
