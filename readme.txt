The Public Transport Routing  I designed as Dijkstra algorithm
(I assumed it is mainly represents a "shortest Path in a directed weighted graph")

I evalueted A*  and breadth-first search as well. BFS seemed not to fit, because in our case edges are weighted.
For A* I lacked the heuristic (i.e LatLong).

I know that dijkstra can be implemented with priority queue, but then nearby queries was harder to realize.


Dijkstra description
--------------------
There is a set of newly discovered, but jet unvisited nodes.
At the start it contains just "A", the source node.
The algorithm iterates until all nodes of this set are processed.
From that set it takes the node nearest to the source node.
In the first iteration this is just A with distance 0.

For this node it finds all neighbours where the distance over the current path is smaller
than the distance/path this neighbour node already knows.
At beginning, when the node is first time discovered, that distance is infinity.

previousNode and distance of this neighbour nodes get updated and they get added to the list
of "unvisitedNodes" for further path extention.

On the next iteration the algorithm picks the most near of that new nodes again and goes ahead like described.
So it might meet a node several times over different pathes with different distances.

At the end it will not find any neigbours any more which are still unvisited, the above mentioned list will get empty and iteration stops
Now every node will have assigned the shortest distance to source node. The path (reversed) can be seen by reading "previousNode"
until "previousNode" is source node.

