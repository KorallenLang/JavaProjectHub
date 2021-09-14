import java.util.*;
import java.io.*;

class WordPair {
    String first;
    String second;

    public WordPair(String p, String q) {
        // new WordPair(word_arr[k], word_arr[j]) first = word_arr[k]; second = word_arr[j]
        first = p;
        second = q;
    }

    public String toString() {
        return first + " " + second;
    }
    // WordPair look like "first second"
}

// pairs = ["word_arr[0] word_arr[1]", ... , "word_arr[word_arr.length - 2] word_arr{word_arr.length - 1]"]
class WPList {
    ArrayList<WordPair> pairs;  // Declare arraylist, pairs, of objects of class WordPair

    public WPList(String[] word_arr) {
        pairs = new ArrayList<>();  // initialize
        for(int i = 0; i < word_arr.length - 1; i++) {  // first i; (2, 3) position
            for(int j = i + 1; j < word_arr.length; j++) {
                pairs.add(new WordPair(word_arr[i], word_arr[j]));
            }
        }
    }

    // a match: in a word pair, first equals second string
    // "D D" D's match, c = 1
    public int num_matches() {
        int c = 0;
        for(WordPair wp: pairs) {
            if (wp.first.equals(wp.second)) {
                c += 1;
            }
        }
        return c;
    }
}

public class WordPairTest {

    public static void main(String[] args) {
        String wa1[] = { "1", "2", "3", "4" };  // String[] wa1
        WPList list1 = new WPList(wa1);
        for (WordPair wp : list1.pairs) {  // access the pairs attribute of object list1
            System.out.println(wp);  // print our objects of class WordPair
        }
        String[][] res = { { "1", "2" }, { "1", "3" }, { "1", "4" }, { "2", "3" }, { "2", "4" }, { "3", "4" } };
        if (list1.pairs.size() == res.length) {  // check if result is same
            for (int k = 0; k < 6; k++) {
                WordPair pair = list1.pairs.get(k);
                if (!pair.first.equals(res[k][0]) || !pair.second.equals(res[k][1])) {
                    System.out.println("ERROR: pair=(" + pair.first + " " + pair.second + ")  res[k]=(" + res[k][0]
                            + " " + res[k][1] + ")");  // if our pair doesn't match result
                }
            }
        } else {
            System.out.println(
                    "ERROR: list1.pairs.size() = " + list1.pairs.size() + " != " + res.length + " = res.length");
        }

        String wa2[] = { "D", "A", "H", "A", "D", "H", "A", "A" };
        WPList list2 = new WPList(wa2);
        for (WordPair wp : list2.pairs) {
            System.out.println(wp);
        }
        if (list2.num_matches() != 8) {
            System.out.println("ERROR: list2.num_matches() = " + list2.num_matches() + " != 8");
        }
    }
}