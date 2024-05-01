# Height in a Binary Tree

Your task is to augment the data structure of a binary search tree so that it also supports queries for finding the ***height*** of a query key.
More precisely, for a binary tree $T$ and a node $v$ in $T$, the *height* of $v$ is 1 plus the length of the longest path from $v$ to the leaves in the sub-tree of $v$. 
For a query key $k$, if a node in $T$ is associated with $k$, the *height* of $k$ is the height of the node; otherwise, the *height* of $k$ is -1. 
In this exercise, we assume that all the nodes in the binary search tree have different keys. 


Please see the Main.java file. The task is to implement the method "heightOfKey". You are free to create auxiliary methods. Note that you may also need to make small changes to some of the existing methods of the "TreeNode" and "BinaryTree" classes.

You get one point for each passing test set. 
To pass both test sets correctly, the time complexity per query has to be at most $O(h)$, where $h$ is the height of the root of the tree.
