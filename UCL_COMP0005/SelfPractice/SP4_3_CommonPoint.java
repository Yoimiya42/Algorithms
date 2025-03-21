
/*
 * Exercise3 – Set Intersection
    Given two arrays a[] and b[], each containing n distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array a[] and b[].
 */

 import java.util.Random;

public class SP4_3_CommonPoint {
    public static void main(String[] args){

        int pointsNum = 420;
        int max_value = 20;
        int min_value = 0;
        // Point[] a = {
        //             new Point(9,42),  // common3
        //             new Point(7,21),  // common1
        //             new Point(6,14),
        //             new Point(4,12), // common2
        //             new Point(36,9),
        //             new Point(9,9)
        // };

        // Point[] b = {
        //             new Point(7,16),
        //             new Point(9,42),  // common3
        //             new Point(8,32),
        //             new Point(42,10),
        //             new Point(4,12), // common2
        //             new Point(7,21), // common1
        // };
        Point[] a = new Point[pointsNum];
        Point[] b = new Point[pointsNum];

        Random random = new Random();
        for (int i = 0; i < pointsNum; i++)
        {
            a[i] = new Point(random.nextInt(max_value + 1),
                         (int)(Math.random() * (max_value - min_value + 1)));
            // System.out.println(a[i]);
        }

        System.out.println();

        for(int i = 0; i < pointsNum; i++)
        {
            b[i] = new Point(random.nextInt(max_value + 1),
                         (int)(Math.random() * (max_value - min_value + 1)));
            // System.out.println(b[i]);
        }

        CommonPoint cp = new CommonPoint();
        int res = cp.commonPoints(a, b);

        System.out.println();
        // for(Point p : b){
        //     System.out.println(p);
        // }
        
        System.out.println(res);

    }
}


class Point implements Comparable<Point>{
    int x; 
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p2)
    {
        if(this.x == p2.x && this.y == p2.y)
            return 0;
        if(this.x < p2.x)
            return -1;
        if(this.x == p2.x && this.y < p2.y)
            return -1;
            
        return 1;
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }

}

class CommonPoint{

    public void merge(Point[] p, int left, int mid, int right)
    {   
        int l = left;
        int r = mid + 1;

        Point[] temp = new Point[right - left + 1];
        int t = 0;

        while(l <= mid && r <= right)
        {
            if(p[l].compareTo(p[r]) == -1)
                temp[t++] = p[l++];
            else
                temp[t++] = p[r++];
        }

        while(l <= mid) 
            temp[t++] = p[l++];
        while(r <= right) 
            temp[t++] = p[r++];

        for(int i = 0; i < temp.length; i++)
            p[left+i] = temp[i];
    }


    public void mergeSort(Point[] p, int left, int right)
    {
        if(left >= right)
            return;

        int mid = left + (right - left) / 2;

        mergeSort(p, left, mid);
        mergeSort(p, mid + 1, right);
        merge(p, left, mid, right);
    }

    public boolean binarySearch(Point[] p, int left, int right, Point target){

        if(left >= right)
            return false;

        int mid = left + (right - left) / 2;

        if(p[mid].compareTo(target) == 0)
            return true;

        if(p[mid].compareTo(target) == -1)
            return binarySearch(p, mid + 1, right, target);
        else
            return binarySearch(p, left, mid - 1, target);
    }

    public int commonPoints(Point[] a, Point[] b)
    {   
        int res = 0;
        mergeSort(b, 0, b.length - 1);

        for(Point p : a)
        {
            if(binarySearch(b, 0, b.length - 1, p))
                res += 1;
        }
        
        return res;
    }

}

