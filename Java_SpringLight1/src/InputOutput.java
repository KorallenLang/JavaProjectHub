import java.io.*;
import java.util.*;
public class InputOutput {

    public static void main(String[] args) throws IOException {
        int N = 30;  // number of lines
        int UB = 20; // to generate random numbers: 1 <= d <= UB
        int M = 35; // There are m random numbers per line (ArrayList)
        long start = System.currentTimeMillis();
        // USACO output use line 16, line 17 for testing
        // USACO input use line 12, line 13 test complexity
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("rnd_num2.txt"));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        // st load line faster from input file by making delimiters, readLine reads input
        // PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rnd_num2.txt")));
        // PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        // out.close();
        // line 18 close output stream so output file is completed; temporay buffer memory releashed
        // array of ArrayList look like [ArrayList[0], ArrayList[1]..., ArrayList[N - 1]]
        ArrayList<Integer>[] p = new ArrayList[N]; // each ArrayList holds a sequence of m random numbers
        for (int k = 0; k < N; k++) {
            p[k] = new ArrayList<>();
        }
        for (int k = 0; k < N; k++) {
            int m = ((int) (Math.random() * M)) + 1;
            // 0 <= x < 1 get 0 to 34 then add 1, 1 <= m < 35
            for(int j = 0; j < m; j++) {
                int d = ((int) (Math.random() * UB)) + 1;
                p[k].add(d);
            }
        }
        /*
        for (int k = 0; k < N; k++) {
            System.out.print(p[k].size() + " ");
            for (int d: p[k]) { System.out.print(d + " "); }
            System.out.println();  // number of random numbers + random numbers
        }
        */
        FileWriter out = new FileWriter("rnd_num.txt");
        out.write(N + "\n");  // \n for new line
        for (int k = 0; k < N; k++) {
            out.write(p[k].size() + " ");
            for (int d : p[k]) {
                out.write(d + " ");
            }
            out.write("\n");
        }
        out.close();

        long end = System.currentTimeMillis(); // ==> time from Jan 1, 1970 in ms
        System.out.println("FileWriter output: " + (end - start) + " ms");

        long start2 = end;
        File in_file = new File("rnd_num.txt");
        Scanner sc = new Scanner(in_file);
        N = sc.nextInt();
        ArrayList<Integer>[] q = new ArrayList[N]; // each ArrayList holds a sequence of m random numbers
        for (int k = 0; k < N; k++) {
            q[k] = new ArrayList<>();
        }
        for (int k = 0; k < N; k++) { // to load k lines of data int
            int y = sc.nextInt();  // read one integer from input file
            for (int j = 0; j < y; j++) {
                int d = sc.nextInt();
                q[k].add(d);
            }
        }
        sc.close();

        long end2 = System.currentTimeMillis(); // ==> time from Jan 1, 1970 in ms
        System.out.println("Scanner input: " + (end2 - start2) + " ms");

        if (p.length != q.length) {
            System.out.println("Error: p length = " + p.length + " not equal q length =" + q.length);
        }
        for (int k = 0; k < N; k++) { // to check whether p[] and q[] hold the same content
            if (p[k].size() != q[k].size()) {
                System.out.println("ERROR: p[k].size() = " + p[k].size() + " != " + q[k].size() + " = q[k].size()");
            }
            for (int j = 0; j < q[k].size(); j++) {
                if (p[k].get(j) != q[k].get(j)) {
                    System.out.println("ERROR: k=" + k + " j=" + j + " p[k].get(j) != q[k].get(j)");
                }
            }
        }

        long start3 = System.currentTimeMillis();  // Total time in ms from Jan 1 1970
        PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("rnd_num2.txt")));
        out2.println(N);
        for (int k = 0; k < N; k++) {
            out2.print(p[k].size() + " ");
            for (int d : p[k]) {
                out2.print(d + " ");
            }
            out2.println();
        }
        out2.close();
        long end3 = System.currentTimeMillis(); // ==> time from Jan 1, 1970 in ms
        System.out.println("BufferedWriter output: " + (end3 - start3) + " ms");


        long start4 = end3;
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("rnd_num2.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        q = new ArrayList[N]; // each ArrayList holds a sequence of m random number
        for (int k = 0; k < N; k++) {
            q[k] = new ArrayList<>();
        }
        for (int k = 0; k < N; k++) { // to load k lines of data
            st = new StringTokenizer(br.readLine());  // load one line
            int m = Integer.parseInt(st.nextToken());
            // st.nextToken like .split("[ ]+") parseInt take string create int
            for (int j = 0; j < m; j++) {
                int d = Integer.parseInt(st.nextToken());
                q[k].add(d);
            }
        }
        br.close();

        long end4 = System.currentTimeMillis(); // ==> time from Jan 1, 1970 in ms
        System.out.println("BufferedReader input: " + (end4 - start4) + " ms");
        System.out.printf("BufferedReader and Scanner input: %.3f times faster\n",
                (((double) (end2 - start2)) / ((double) (end4 - start4))));
        System.out.printf("BufferedWriter and FileWriter output: %.3f times faster\n",
                (((double) (end - start)) / ((double) (end3 - start3))));

        if (p.length != q.length) {
            System.out.println("Error: p length = " + p.length + " not equal q length =" + q.length);
        }
        for (int k = 0; k < N; k++) { // to check whether p[] and q[] hold the same content
            if (p[k].size() != q[k].size()) {
                System.out.println("ERROR: p[k].size() = " + p[k].size() + " != " + q[k].size() + " = q[k].size()");
            }
            for (int j = 0; j < q[k].size(); j++) {
                if (p[k].get(j) != q[k].get(j)) {
                    System.out.println("ERROR: k=" + k + " j=" + j + " p[k].get(j) != q[k].get(j)");
                }
            }
        }
        /*
        buffer allocates some computer memory then save data to memory otherwise small piece has to
        be continuously requested and continue loading new stuff
        */
    }
}