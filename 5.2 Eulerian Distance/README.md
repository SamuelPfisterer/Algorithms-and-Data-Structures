# Eulerian Distance

The town Zug consists of $n$ intersections and $m$ roads, modeled as an undirected graph with $n$ nodes (numbered from $0$ to $n-1$) and $m$ edges. The graph is connected.

You are in charge of an exciting new construction project: making Zug Eulerian. That is, you will choose a minimum number of new roads to construct such that Zug becomes Eulerian. In addition, to avoid the construction noise, you will move your home into an intersection that is as far as possible from the endpoints of the new roads.

In more technical terms:
1. You need to add to the graph a minimum number of new edges such that, after adding these edges, the graph is Eulerian. You are allowed to add a new edge between two nodes even if an identical edge already exists (in this case, the two edges count as different edges in the Eulerian cycle). Suppose that you choose to add edges $(a_1, b_1)$, ..., $(a_k, b_k)$ for some $k$, where $0 \leq a_i, b_i \leq n-1$.
2. You need to select a node $s$ in the graph such that the minimum distance **in the original graph** from $s$ to any of the nodes $a_1$, $b_1$, ..., $a_k$, $b_k$ is maximized.

Your program only needs to output the minimum distance in the original graph from $s$ to the nodes $\{a_1, b_1, ..., a_k, b_k\}$, i.e., $\min(\rm{dist}(s, a_1), \rm{dist}(s, b_1), ..., \rm{dist}(s, a_k), \rm{dist}(s, b_k))$. You do not need to output the edges $(a_1, b_1)$, ..., $(a_k, b_k)$ nor $s$. The value that you need to output is guaranteed to be unique (even if the selection of the edges and of $s$ is not unique).

For example, consider the following graph:

![graph_euler.png](/)

You can make the graph Eulerian by adding edges $(0, 5)$ and $(2, 4)$: in that case, an Eulerian cycle is composed of the nodes $0$, $5$, $4$, $2$, $1$, $4$, $3$, $1$, $0$. Then one optimal choice for your home is $s=1$, for which the minimum distance to one of $\{0, 5, 2, 4\}$ is $1$. Therefore, the correct output is $1$.

Implement the method "getMinDistance". You are free to create auxiliary methods.

You get one point for each passing test set. To pass both test sets correctly, your solution is expected to run in $O(n+m)$ time.
