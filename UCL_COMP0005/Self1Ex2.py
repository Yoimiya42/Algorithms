"""
Given a social network containing N members and a log file containing a sequence of friendships requests, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is temporally sorted, and that friendship is an equivalence relation. What is the running time of your algorithm?

Hint: Use Union-Find to model friendships
"""
"""
connected()
init()
union()
find()
root()
"""

from typing import Dict, List

class Node:
    def __init__(self, value: int):
        self.val: int = value
        self.prev:Node = None
        self.child:int = 1

class UF:
    def __init__(self, arr:List[int]):
        self.opCount:int = 0
        self.nodes:Dict[int, Node] = {}

        for val in arr:
            self.nodes[val] = Node(val)

    def connected(self):
        for node in self.nodes.values():
            if node.child == len(self.nodes):
                print(f"All members have been connected. OperationsCount: {self.opCount}")
                break;
            

    def union(self, u:int, v:int):
        uroot:Node = self.root(u)
        vroot:Node = self.root(v)

        if uroot == vroot: # They are already connected
            self.opCount += 1
            return
        
        if uroot.child > vroot.child:
            vroot.prev = uroot
            uroot.child += vroot.child

            self.opCount += 1
        else: 
            uroot.prev = vroot
            vroot.child += uroot.child

            self.opCount += 1 

        self.connected()

    def find(self, u:int, v:int) -> bool:
        return self.root(u) == self.root(v)

    def root(self, n:int) -> Node:

        cur:Node = self.nodes[n]
        while(cur.prev):
            cur = cur.prev
        
        return cur
    
    def show(self):
        for node in self.nodes.values():
            linkNode = f" -> {node.val}"
            while(node.prev):
                linkNode += f" -> {node.prev.val}"
                node = node.prev
            print(linkNode)

def test_uf():
    print("Testing UnionFind")
    print("1 - Union")
    print("2 - Find")
    print("3 - Show")

    while True:
        choice = input("Enter choice: ")
        if choice == "1":
            u = int(input("Enter first node: "))
            v = int(input("Enter second node: "))
            uf.union(u, v)
        elif choice == "2":
            u = int(input("Enter first node: "))
            v = int(input("Enter second node: "))
            print(uf.find(u, v))
        elif choice == "3":
            uf.show()
        else:
            break

if __name__ == "__main__":
    nodes = [10,20,30,40,50,60]
    uf = UF(nodes)
    test_uf()