import java.util.*;

/*
   Message has the format nodeId:description and exactly one ":" appears in a message.
   nodeId is a computer (e.g., Server2) or a user (e.g., Client8).
   There are no spaces immediately before or after ":".
*/

class Message {
    String nodeId;  // object variable
    String description;

    public Message(String msg_str) {
        String[] wa = msg_str.split(":");  // split words by delimiter into String[]
        nodeId = wa[0];  // left side of what got separated by :
        description = wa[1];  // right side
    }

    /*
     * returns: true if description contains the keyword, false otherwise. The
     * keyword is either at the beginning or it is immediately preceded by a space.
     * The keyword is either at the end or it is immediately followed by a space.
     */
    public boolean contains_word(String keyword, boolean case_sensitive) {
        String[] wa = description.split("[ ]+");
        // inside [] list chars which is space, plus signifies one or more repeated sequence
        for(String w: wa) {
            boolean flag = str_eq(w, keyword, case_sensitive);  // returns true if success
            if (flag) {
                return true;  // found keyword successfully; exit loop and function
            }
        }
        return false;  // if finish loop and not find keyword; exit function
    }

    boolean str_eq(String w1, String w2, boolean case_sensitive) {
        // check our possible cases already here
        if (case_sensitive) {
            return w1.equals(w2);  // in case it is spelt weirdly
        }
        return w1.equalsIgnoreCase(w2);  // don't need to worry about spelt weirdly
    }

    public boolean contains_segment(String[] segment, boolean case_sensitive) {
        String[] wa = description.split("[ ]+");
        for(int k = 0; k + segment.length <= wa.length; k++) {
            // if the k + segment.length came at the end want to make sure to check that case too
            if (str_eq(wa[k], segment[0], case_sensitive)) {  // match first word
                boolean match = true;
                for(int j = 1; j < segment.length; j++) {  // check match all words in segment
                    if (!str_eq(wa[k + j], segment[j], case_sensitive)) {
                        // need to update with k + j because j is constantly updating
                        // and segment lengths can vary so updating like k + 1 is not reliable, too slow
                        match = false;  // str_eq returned false
                        break;  // this whole k index is a bad case so don't bother finishing its j loop
                    }
                }
                if (match) {
                    return true;  // found match, exit function
                }
            }
        }
        return false;  // no match found, exit function
    }
}

public class MessageLog {
    public static void main(String[] args) {
        // sample log
        String[] log = {
                "Server7:Client2 new account is saved",  // nodeId = "Server 7", Description = "Client7..."
                "Server6:Client8 new  account is saved",
                "Server8:Client2 login",
                "Server1:disk",
                "Server2:Disk",
                "Server3:error on disk",  // ["error", "on", "disk"]
                "Server4:error on disk3",  // right of disk is not space or end
                "Server9:error on /dev/disk",
                "Server5:error on /dev/disk disk",
                "Server6:error on disk DSK2",
                "Server1:disks",  // bad if part of another word; part of disks
                "Server4:Client3 submit order 1",
                "Server9:Client3 add product 2 to shopping cart",
                "Server9:Client3 add product 2 to shopping  cart",
                "Client5:security alert - repeated login failures",
                "Webserver:disk is available",
                "Server1:file X is not found",
                "Server4:DISK is full",
                "Server1:file Y is not  found",
                "Server3:read error on disk DSK1",
                "Server1:write Error on disk DSK2",
                "Server9:Write Error on DISK",
                "Webserver:error on /dev/disk" };
        int n = log.length;
        Message[] msg = new Message[n];  // object[] to null value; bool[] to false
        for (int k = 0; k < log.length; k++) {
            msg[k] = new Message(log[k]);  // save value from log by passing to constructor
            // msg[0] = new Message(log[0]); nodeId : Server 7, Description : Client2...
            // so Message[] saves object into group then we access object w/ [n] and .attribute
            /*
             * System.out.println("message " + k + " : nodeId=" + msg[k].nodeId +
             * "  description=" + msg[k].description);
             */
        }

        // here checks if a keyword matches
        List<String> res = search(msg, "Disk", true);
        display(res);
        List<String> res1 = search(msg, "error", false);
        display(res1);

        // here checks for a phrase
        List<String> res2 = search_segment(msg, "new account", false);
        display("new account", res2);
        List<String> res3 = search_segment(msg, "Error on disk", true);
        display("Error on disk", res3);
        List<String> res4 = search_segment(msg, "shopping cart", false);
        display("shopping cart", res4);
    }

    static List<String> search_segment(Message[] msg, String phrase, boolean case_sensitive) {
        String[] segment = phrase.split("[ ]+");  // delimiter is space
        List<String> res = new ArrayList<>();
        for (Message y : msg) {  // loop through our objects in msg Message[]
            if (y.contains_segment(segment, case_sensitive)) {
                res.add(y.description);  // res is an arraylist w/ descriptions if segment found
            }
        }

        return res;
    }

    static List<String> search(Message[] msg, String keyword, boolean case_sensitive) {
        List<String> res = new ArrayList<>();
        for (Message y : msg) {
            if (y.contains_word(keyword, case_sensitive)) {
                res.add(y.description);
            }
        }

        return res;
    }

    static void display(List<String> res) {
        for (String d : res) {
            System.out.println(d);
        }
        System.out.println();
    }
    static void display(String prompt, List<String> res) {  // function overload
        System.out.println(prompt + " : ");
        for (String d : res) {
            System.out.println(d);
        }
        System.out.println();
    }
}
