import java.io.*;
import java.util.*;
public class TreeSetTest {
    static class Interval {
        String u;
        int start;
        int end;
        public Interval(String u_, int start_, int end_) {
            end = end_;
            start = start_;
            u = u_;
        }
    }
    public static void main (String [] args) throws IOException {
        /*
        TreeSet<Integer> p = new TreeSet<>((Integer d1, Integer d2) -> (d2 - d1));
        // default is increasing order
        p.add(7);
        p.add(6);
        p.add(8);
        p.add(14);
        p.add(27);
        p.add(26);
        p.add(28);
        p.add(13);
        p.add(19);
        for(int d: p) {
            System.out.print(d + " ");
        }
        System.out.println();
        TreeSet<String> q = new TreeSet<>();
        // Capital before little; ASCII table rule
        q.add("today");
        q.add("foo");
        q.add("bar");
        q.add("Java");
        q.add("July");
        // q.remove("bar");
        q.pollLast();
        for(String w: q) {
            System.out.println(w);
        }
         */
        // Intervals : A[1, 4], B[3, 7], C[5, 9], D[2, 6]
        List<Interval> intv = new ArrayList<>();
        intv.add(new Interval("A", 1, 4));
        intv.add(new Interval("B", 3, 7));
        intv.add(new Interval("C", 5, 9));
        intv.add(new Interval("D", 2, 6));
        int n = intv.size();
        String[] User = new String[n];  // record user; n is amount of intv objects created
        TreeSet<int[]> p = new TreeSet<>((int[] d1, int[] d2) -> (d1[0] - d2[0]));
        // element is time event [0] : time, [1] : start/end [2] : user index
        for(int k = 0; k < n; k++) {  // go from 0 to n - 1 inclusive; essentially n
            Interval y = intv.get(k);  // get an interval object y
            User[k] = y.u;  // add values to User[] by accessing value of u in y object
            p.add(new int[] {y.start, 1, k});
            p.add(new int[] {y.end, -1, k});
            // add starting and ending values of each interval object
        }
        for(int[] d: p) {
            System.out.println(d[0] + " : " + User[d[2]]);  // accessing time and user values from p
            if (d[1] > 0) {
                System.out.println(" starts");
            } else {
                System.out.println(" ends");
            }
        }
    }
}
