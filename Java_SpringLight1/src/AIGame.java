import java.io.*;
import java.util.*;

class Node {
    int d;
    String s;
    Node left;
    Node right;

    public Node(int d_, String s_) {
        d = d_;
        s = s_;
        left = null;
        right = null;
    }
}
// 5 level full bt 31 nodes; 1 2 4 ... 16
public class AIGame {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new FileReader("game1.txt"));
        // create object scan of type class Scanner which takes input from the file[game1.txt] we're reading from
        String title = scan.nextLine();
        System.out.println("title: " + title);

        String help_info = scan.nextLine();
        System.out.println("help_info: " + help_info);

        Node[] node = new Node[31];  // [200 "Is it animated", 150 "Sea adventure"...]
        for (int k = 0; k < 31; k++) {
            String line = scan.nextLine();
            int d = Integer.parseInt(line.substring(0, 3));  // String to int
            String s = line.substring(5);  // double spaces between; question or movie name
            node[k] = new Node(d, s);
        }
        scan.close();
        // to build the tree
        Node root = node[0];
        // root.d = 200, root.s = "It is animated"
        for (int k = 1; k < 31; k++) {
            add(root, node[k]);
        }
        DFS(root);  // traverse tree by DFS
        System.out.println("");

        Scanner scan_user = new Scanner(System.in);  // user input
        String op = "init";
        while (!op.equals("X")) {
            if (op.equals("P")) {
                play_game(root, scan_user);
            } else if (op.equals("L")) {
                System.out.println("Please enter another game file: ");
                String fn = scan_user.nextLine();
                Scanner scan2 = new Scanner(new FileReader(fn));
                title = scan2.nextLine();
                help_info = scan2.nextLine();
                for (int k = 0; k < 31; k++) {
                    String line = scan2.nextLine();
                    int d = Integer.parseInt(line.substring(0, 3));
                    String s = line.substring(5);
                    node[k] = new Node(d, s);
                }
                scan2.close();
                // to build the tree
                root = node[0];
                for (int k = 1; k < 31; k++) {
                    add(root, node[k]);
                }
            } else if (op.equals("D")) {
                DFS(root);
            } else if (op.equals("H")) {
                System.out.println(help_info);
            }

            System.out.println("\n" + title);
            op = get_input_op(scan_user, true);
            // System.out.println("op=" + op);
        }
    }

    // to play the game
    static void play_game(Node c, Scanner scan_user) {
        while (c.left != null || c.right != null) {  // option to explore further
            System.out.print(c.s + " "); // a question to ask
            String ans = get_input_op(scan_user, false); // get answer from a user
            if (ans.equals("Y")) {  // move down a level
                c = c.left;  // update node c we're on
            } else {
                c = c.right;
            }
        }

        // now c is a leaf (a movie)
        System.out.println("\n" + c.s);
    }

    // c: current node in the tree
    // y: a new node to add to the tree
    static void add(Node c, Node y) {
        while (c != null) {
            if (y.d < c.d) { // ==> check number value and go to the left subtree
                if (c.left == null) {
                    c.left = y;  // connect nodes
                    return;
                } else {
                    c = c.left;  // traverse down a left by updating c node to be its left child node
                }
            } else { // y.d > c.d ==> go to the right subtree
                if (c.right == null) {  // node c doesn't have any right child node
                    c.right = y;  // sets c right child to y node
                    return;
                } else {
                    c = c.right;
                }
            }
        }
    }

    // to traverse the tree by inorder: L C R
    static void DFS(Node c) {
        if (c == null) {  // no more options left to explore
            return;
        }
        DFS(c.left);
        System.out.print(c.d + " ");
        DFS(c.right);
    }

    static String get_input_op(Scanner scan_user, boolean main_menu) {
        if (main_menu) {  // starting screen
            System.out.println("\nP  Play the game\nL  Load another game file\nD  Display the binary tree");
            System.out.print("H  Help information\nX  Exit the program\n... your choice: ");
        }

        String op = new String(scan_user.nextLine());  // reads input

        return op.toUpperCase();  // returns input in upper case to maintain format
    }

}