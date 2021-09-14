import java.util.*;
import java.io.*;
public class Noice_Code_CodeBat {
    public static void main(String[] args) {

    }
    public List<String> scaredyCat(List<String> petsList) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < petsList.size(); i++) {
            if ((i > 0) && petsList.get(i).equals("cat") && petsList.get(i-1).equals("dog")) {
                continue;
            }
            else {
                res.add(petsList.get(i));
            }
        }
        return res;
    }
/*
public List<String> scaredyCat(List<String> petsList) {
  for (int i = 0; i < petsList.size(); i++) {
    if (petsList.get(i).equals("cat")) {
      if ((i > 0) && (petsList.get(i - 1).equals("dog"))) {
        petsList.remove(i);
        i--;
      }
    }
  }
  return petsList;
}
*/
}

/*
public String golfingBrothers(List<Integer> adamList, List<Integer> bobList, List<Integer> craigList) {
  double a = Average(adamList);
  double b = Average(bobList);
  double c = Average(craigList);
  if (a == -1) {  // a is not valid
    if (b == -1) {  // c is only valid
      return "craig";
    }
    else {  // b is valid
      if (c == -1) {
        return "bob";
      }
      else {  // c is valid, b is valid
        if (b < c) {
          return "bob";
        }
        return "craig";
      }
    }
  }
  // a is valid
  if (b == -1) {
    if (c < 0) {
      return "adam";
    }
    else {  // c is valid
      if (c < a) {
        return "craig";
      }
      else {
        return "adam";
      }
    }
  }
  // a, b is valid
  if (c == -1) {  // c is not valid
    if (a < b) {
      return "adam";
    }
    return "bob";
  }
  // a, b, c valid
  if (a < b && a < c) {
    return "adam";
  }
  if (b < a && b < c) {
    return "bob";
  }
  if (c < a && c < b) {
    return "craig";
  }
  return null;
}

public double Average(List<Integer> p) {
  if (p.size() < 2) {
    return -1;
  }
  int total = 0;
  for (int d: p) {
    total += d;
  }
  return ((double) total/(double) p.size());
}
 */
/*
public List<Integer> classRanks(List<Integer> rankings) {
  List<Integer> res = new ArrayList<>(rankings);  // copy
  int c = 0;
  for(int num: res) {
    if (num < 10) {
      c += 1;
    }
  }
   if (c >= 2) {
    // Collections.sort(res);  // default small to large
    Collections.sort(res, ((Integer d1, Integer d2) -> (d1 - d2)));
  } else {
    // Collections.sort(res, Collections.reverseOrder());
    Collections.sort(res, ((Integer d1, Integer d2) -> (d2 - d1)));
  }
  return res;
}
 */