import java.util.List;
import java.util.*;

class Delimiters {
    /* open and close delimiters. */
    // by default, variables in class are public.
    String openDel; // variable, field, feature, attribute of an object of class Delimiters
    String closeDel;

    /* Constructor never returns anything */
    // basically like __init__ in python
    public Delimiters(String open, String close) {
        openDel = open;  // everytime call on new object, will store new parameter
        closeDel = close;
    }

    /* To create an ArrayList of delimiters from the array tokens */
    public List<String> getDelimitersList(String[] tokens) {
        List<String> res = new ArrayList<>();
        for(String w : tokens) {  // w is token
            if (w.equals(openDel) || w.equals(closeDel)) {
                res.add(w);
            }
        }
        return res;
    }

    /*
     * To return true if the delimiters are balanced and false otherwise.
     *
     * Precondition: delimiters contains only valid open and close delimiters.
     */
    public boolean isBalanced(List<String> delimiters) {
        int c = 0;
        for(int k = 0; k < delimiters.size(); k++) {
            String d = delimiters.get(k);
            if (d.equals(openDel)) {
                c++;
            } else if (d.equals(closeDel)) {
                c--;
            }
            if (c < 0) {  // too many closed before open
                return false;
            }
        }
        if (c == 0) {
            return true;
        }
        return false;
    }
}

public class DelimitersTest {
    public static void main(String[] args) {
        // tokens array
        String[] t1 = { "(", "x + y", ")", "* 2" };
        String[] t2 = { "(", "(", "x + y", ")", " * 2", " + 1", ")" };
        String[] t3 = { "[", "7", "[", "]", "[", "5", "]", "18", "]", "[", "11", "]" };
        String[] t4 = { "(", "x + y", ")", "* 2", ")", "(" };
        String[] t5 = { "(", "(", "(", "x + y", ")", " * 2", " + 1", ")" };
        String[] t6 = { "[", "7", "[", "]", "[", "5", "[", "]", "18", "]", "[", "11", "]" };
        String[] t7 = { "<p>", "paragraph", "</p>", "<p>" };
        String[] t8 = { "<p>", "paragraph", "</p>" }; // tag in HTML

        Delimiters p1 = new Delimiters("(", ")");
        // System.out.print("openDel=" + p1.openDel + " closeDel=" + p1.closeDel);
        List<String> delim_list = p1.getDelimitersList(t1);
        System.out.print(delim_list);
        boolean flag = p1.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t2
        delim_list = p1.getDelimitersList(t2);
        System.out.print(delim_list);
        flag = p1.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t4
        delim_list = p1.getDelimitersList(t4);
        System.out.print(delim_list);
        flag = p1.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t5
        delim_list = p1.getDelimitersList(t5);
        System.out.print(delim_list);
        flag = p1.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t3
        Delimiters h = new Delimiters("[", "]");
        delim_list = h.getDelimitersList(t3);
        System.out.print(delim_list);
        flag = h.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t6
        delim_list = h.getDelimitersList(t6);
        System.out.print(delim_list);
        flag = h.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t7
        Delimiters y = new Delimiters("<p>", "</p>");
        delim_list = y.getDelimitersList(t7);
        System.out.print(delim_list);
        flag = y.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

        // to test t8
        delim_list = y.getDelimitersList(t8);
        System.out.print(delim_list);
        flag = y.isBalanced(delim_list);
        System.out.println(" ==> " + flag);

    }

}
