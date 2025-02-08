
"""
Extend Union-Find with a method find so that find(i) returns the largest element in the connected component containing i. For example, if one of the connected components is {1,2,6,9}, then the find method should return 9 for each of the four elements in the connected components. The operations union(), connected(), and find() should all take logarithmic time or better.

Hint: Maintain an extra array that stores, for each i, the largest element in the connected component containing i.
"""

from typing import Dict, List

class Node:
    def __init__(self, value: int):
        self.val: int = value
        self.prev:Node = None
        self.child:int = 1
        self.elder:int = self.val

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
    
    def findElder(self, n:int) -> int:
        return self.root(n).elder

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

            uroot.elder = max(uroot.elder, vroot.elder)
        else:  # vroot.child >= uroot.child
            uroot.prev = vroot
            vroot.child += uroot.child

            self.opCount += 1 

            vroot.elder = max(vroot.elder, uroot.elder)

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
    print("4 - Find Elder")

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
        elif choice == "4":
            n = int(input("Enter node: "))
            print(uf.findElder(n))
        else:
            break

if __name__ == "__main__":
    nodes = [10,20,30,40,50,60,70]
    uf = UF(nodes)
    test_uf()