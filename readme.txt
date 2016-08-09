The Public Transport Routing  I designed as Dijkstra algorithm
(I assumed it is mainly represents a "shortest Path in a directed weighted graph")

I evalueted A*  and breadth-first search as well. BFS seemed not to fit, because in our case edges are weighted.
For A* I lacked the heuristic (i.e LatLong).


Dijkstra description
--------------------
See the Graph*.pdf

We have a #PrioQueue of newly discovered nodes #nodesToCheck.
At the start it contains just "A", the source node.
The algorithm iterates until all nodes of #nodesToCheck are processed.

From #nodesToCheck it takes the node whith least distance to the source node, #currentNode.
In the first iteration this is just the source node A with distance 0 to A.
#currentNode will be removed #nodesToCheck and marked #visited.
Visited Nodes will not be considerd when searching for neigbours.

For #currentNode it finds all neighbours where the distance over the current path is lower
than the distance/path this neighbour has already from other path.
At beginning, when the neighbour is first time discovered, that distance is infinity.

#previousNode and #distance of this neighbour nodes get updated and they get added to the #nodesToCheck
for further path extention.

On the next iteration the algorithm picks node (most near to source) again from #nodesToCheck and goes ahead like described.
So it might meet a node several times over different pathes with different distances.

At the end it will not find any neigbours any more which are still unvisited, the above mentioned list will get empty
and iteration will stop.

Now every node has the shortest #distance to source node and #previousNode assigned.
The path (reversed) can be seen by repeatingly reading "previousNode" until "previousNode" is source node.

