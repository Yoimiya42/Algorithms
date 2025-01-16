public class UF {
    public static void main(String[] args) {

    }

    private int[] id;
    private int[] size;
    private int count;

    public UF(int n) {
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return id[p];
    }

    public void union(int p, int q) {

        int i = find(p);
        int j = find(q);

        if (i == j) {return;}

        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];

        } else {  // size[i] >= size[j]

            
            id[j] = i;
            size[i] += size[j];
        }

        count--;
    }

}
