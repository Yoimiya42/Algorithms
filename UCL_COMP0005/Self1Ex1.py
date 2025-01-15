import random

class Node:
    def __init__(self, value):
        self.val = value
        self.next = None
        self.prev = None
        self.child = 1
        self.root = 0

class UnionFind:
    def __init__(self, arr):
        self.nodes = {}
        for num in arr:
            self.nodes[num] = Node(num)

        self.time = 1

    def all_connected(self):
        guard = random.choice(list(self.nodes.values())).root
        for node in self.nodes.values():
            if node.root != guard:
                return False

        return True


        
    def union(self, u, v):
        u_cur = self.nodes[u]
        v_cur = self.nodes[v]

        u_root = self.root(u)
        v_root = self.root(v)
        
        if u_root == v_root:

            return
    
        if u_root.child > v_root.child:
            v_root.prev = u_root

            u_cur.root = u_root.val
            v_cur.root = u_root.val
            
            u_root.child += v_root.child

            self.time += 1
        else:
            u_root.prev = v_root

            u_cur.root = v_root.val
            v_cur.root = v_root.val

            v_root.child += u_root.child

            self.time += 1

        if self.all_connected():
            print("All members have been connected.Time: {self.time}")

    def find(self, u, v):
        u_root = self.root(u)
        v_root = self.root(v)

        return u_root == v_root
    
    def root(self, n):
        temp = self.nodes[n]
        while(temp.prev):
            temp = temp.prev
        
        self.nodes[n].root = temp.val

        return temp
    
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
    nodes = [10,20,30,40,50,60,70]
    uf = UnionFind(nodes)
    test_uf()



