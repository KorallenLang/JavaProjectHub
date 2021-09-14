import java.io.*;
import java.util.*;
public class TreeSetTest1 {
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
        // Intervals : A[1, 4], B[3, 7], C[5, 9], D[2, 6]
        List<Interval> intv = new ArrayList<>();  // array list of type class Interval
        intv.add(new Interval("A", 1, 4));
        intv.add(new Interval("B", 3, 7));
        intv.add(new Interval("C", 5, 9));
        intv.add(new Interval("D", 2, 6));
        int n = intv.size();
        String[] User = new String[n];  // record user; n is amount of intv objects created
        TreeSet<int[]> p = new TreeSet<>((int[] d1, int[] d2) -> (d1[0] - d2[0]));
        // element is time event [0] : time, [1] : start/end [2] : user index

        for(int k = 0; k < n; k++) {  // go from 0 to n - 1 inclusive; essentially n
            Interval y = intv.get(k);  // create an interval object y
            User[k] = y.u;  // add values to User[] by accessing value of u in y object
            p.add(new int[] {y.start, 1, k});
            p.add(new int[] {y.end, -1, k});
            // add starting and ending values of each interval object
        }
        for(int[] r: p) {
            // System.out.println(r[0] + " " + r[1] + " " + r[2]);
            int start1 = r[0];
            String foo = User[r[2]];
            if (r[1] > 0) {
                System.out.println("[" + start1 + ", " + (start1 + 1) + "] : Users " + foo + " has entered the chat");
                /*
                Problem : Continued log of online status only shows 1 user, mess up sorted time values
                int s = (start1 - r[0] + 1);
                while(s < start1) {
                    System.out.println("[" + (r[0] + s) + ", " + (r[0] + s + 1) + "] : Users " + foo + " is online");
                    s++;
                    if (s == start1) {
                        break;
                    }
                }
                 */
            } else if (r[1] < 0) {
                System.out.println("[" + start1 + ", " + (start1 + 1) + "] : Users " + foo + " left");
            }
        }
        /*
        for(int[] i: p) {
            if (i[1] > 0) {
                for(int j = 0; j < n; j++) {
                    Interval q = intv.get(j);
                    int st = q.start;
                    int en = q.end;
                    int s = Math.abs(i[0] - st);
                    if (i[0] == st) {
                        System.out.println("[" + st + ", " + (st + 1) + "] : Users " + User[i[2]] + " start");
                    }
                    if ((i[0] < st) && (i[1] > 0)) {
                        while (s < en) {
                            System.out.println("[" + (s) + ", " + (s + 1) + "] : Users " + User[i[2]] + " present");
                            s++;
                            if (i[1] < 0) {
                                break;
                            }
                        }
                    }
                }
            } else if (i[1] < 0) {
                int en = i[0];
                System.out.println("[" + en + ", " + (en + 1) + "] : Users " + User[i[2]] + " left");
            } else {
                System.out.println("An error has occurred. Please try again");
            }
        }
        */
    }
}
