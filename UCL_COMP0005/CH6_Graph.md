

```python 
def DFS(graph):

    visited = set()
    edgeTo = {v : None for v in G.vertices()}

    # for topological sort
    post_order = []

    # for connected components
    cc = {v : -1 for v in G.vertices()} 
    count = 0

    # check for cycle
    call_stack = set()

    def dfs(v):
      visited.add(v)
      cc[v] = count
      call_stack.add(v)
      for adj in v.adjacent():

          if adj in call_stack:
              raise Exception("Cycle detected")

          if adj not in visited:
              dfs(adj)
              edgeTo[adj] = v
        
        call_stack.remove(v)
        post_order.append(v) 

    for v in G.vertices():
        if v not in visited:
            dfs(v)
            count += 1 

    return post_order, cc, edgeTo
```
```python
def BFS(graph, source):
    edgeTo = {v : None for v in G.vertices()}
    distTo = {v : -1 for v in G.vertices()}
    distTo[source] = 0

    q.enqueue(source)

    while not q.is_empty():
        v = q.dequeue()
        for adj in v.adjacent():
            if distTo[adj] ==  -1:
                q.enqueue(adj)
                distTo[adj] = distTo[v] + 1
                edgeTo[adj] = v

def shortest_path(dest):
  path = []

  while dest != None:
    path.append(dest)
    dest = edgeTo[dest]

  return path[::-1]

def topological_sort(graph):

    DFS(graph) # run DFS to check for cycle
    post_order = DFS(graph)

    topological_order = post_order[::-1]

    return topological_order

def strong_cc(graph):
    topological_order = DFS(graph_reverse)

    scc = {v : -1 for v in G.vertices()}
    count = 0
    for v in topological_order:
        if v not in visited:
            dfs(v)
            count += 1
```

### Dijkstra's Algorithm
```python
def dijkstra(graph, source):
    distTo = {v : for v in graph.V()}
    distTo[source] = 0
    edgeTo = {v : None for v in graph.V()}

    pq = MinPQ()
    pq.enqueue(source, 0) # sorted by distance

    while not pq.empty():
        v = pq.dequeue() # vertex with the smallest distance
        for edge in graph.adj(v):
            relax(edge)

def relax(edge):
    v = edge.from()
    w = edge.to()
    if distTo[v] + edge.weight < distTo[w]:
        distTo[w] = distTo[v] + edge.weight
        edgeTo[w] = v
        if w in pq:
            # delete the old key and insert the new one
            pq.decreaseKey(w, distTo[w]) 
        else:
            pq.enqueue(w, distTo[w])           
```

```python
def acyclicSP(graph, source):
    distTo = {v : for v in graph.V()}
    distTo[source] = 0
    edgeTo = {v : None for v in graph.V()}

    post_order = DFS(graph)
    topological_order = post_order.reverse()
    for v in topological_order:
        for edge in graph.adj(v):
            relax(edge)
```

### Bellman-Ford Algorithm
Prerequisite: 
- Negative weight OK
- Cycles OK 
- No negative cycles !!!

Implementation:
- Traverse all edges `V-1` times.
- Stop if no distance is updated
- Traverse once more time to check for negative cycles.

```python
def bellman_ford(graph):

    distTo = {v : float('int') for v in graph.V()}
    distTo[source] = 0

    precursor = {v : None for v in graph.V()} # edgeTo[]

    for _ in range(graph.V() - 1):
        updated = False # flag to check if any distance is updated in this iteration

        for e in graph.edges():
            v = e.from()
            w = e.to()
            if distTo[v] + e.weight() < distTo[w]:
                distTo[w] = distTo[v] + e.weight()
                precursor[w] = v

                updated = True

        if not updated:
            break

    # check for negative cycles
    for e in graph.edges():
        v = e.from()
        w = e.to()
        if distTo[v] + e.weight() < distTo[w]:
            raise Exception("Negative cycle detected")  
```
