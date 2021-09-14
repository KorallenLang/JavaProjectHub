import java.io.*;
import java.util.*;

public class Knight {
    static int[][] board; // Declare 8x8 chess board

    public static void main(String[] args) {
        board = new int[8][8];

        // start position (k, j) of the knight in chess
        // int k = ((int) (Math.random() * 8)); // row k = 0, 1, 2, ..., 7 (equal
        // chance)
        // int j = ((int) (Math.random() * 8)); // col j = 0, 1, 2, ..., 7 (equal
        // chance)
        int k = 1;
        int j = 2;

        int[] dy = { -2, 2, -2, 2, -1, 1, -1, 1 }; // row change
        int[] dx = { -1, -1, 1, 1, -2, -2, 2, 2 }; // col change
        int step = 1;
        move(k, j, step, dy, dx);

        display(board);
    }

    static boolean move(int k, int j, int step, int[] dy, int[] dx) {
        board[k][j] = step;  // assign step to this value so don't use again
        if (step == 64) {
            return true;
        }

        ArrayList<int[]> p = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int k2 = k + dy[i];
            int j2 = j + dx[i];
            if (k2 < 0 || k2 >= 8 || j2 < 0 || j2 >= 8) { // out of bound
                continue;
            }
            // new potential position (row, col) = (k2, j2) is valid
            // to check no repetition
            if (board[k2][j2] > 0) { // visited
                continue;
            }

            // new position (k2, j2) is valid and not visited
            // to collect such positions in ArrayList
            p.add(new int[] { k2, j2 });
        }

        while (p.size() > 0) {
            int t = get_peri_pos(p);
            int[] c = p.get(t);  // access array t in arraylist p
            int k2 = c[0];  // row is stored first
            int j2 = c[1];  // col is second element
            boolean flag = move(k2, j2, step + 1, dy, dx); // recursive call

            if (flag) { // success; the knight completes a sequence of 64 positions
                return true;
            }
            p.remove(t);
        }

        // to backtrack, restore the state
        board[k][j] = 0;
        return false;
    }

    static int get_peri_pos(ArrayList<int[]> p) {
        double d = 0.0;
        int tg = -1; // target index
        for (int k = 0; k < p.size(); k++) {
            int[] c = p.get(k);
            double d2 = Math.abs(((double) c[0]) - 3.5) + Math.abs(((double) c[1]) - 3.5);  // make sure on outside
            if (d < d2) {
                d = d2;
                tg = k;
            }
        }
        return tg;
    }

    static void display(int[][] A) {
        for (int k = 0; k < A.length; k++) { // row k
            for (int j = 0; j < A[0].length; j++) { // col j
                System.out.printf("%5d", A[k][j]); // %3d : field of width 3 for int
            }
            System.out.println();
        }
    }
}

/*
public static void main (String [] args) throws IOException {
		board = new int[8][8];
		int k = 0; // row k
		int j = 2; // col j

		int[] dy = {-1, -1, 1, 1, -2, -2, 2, 2};
		int[] dx = {-2, 2, -2, 2, -1, 1, -1, 1};

		int step = 1;
		move(k, j, step, dy, dx);

		display(board);
	}
static boolean move(int k, int j, int step, int[] dy, int[] dx) {
		board[k][j] = step;
		if (step == 64) {
			return true;
		}

		for (int i = 0; i < 8; i++) {
			int k2 = k + dy[i];
			int j2 = j + dx[i];
			if (k2 < 0 || k2 >= 8 || j2 < 0 || j2 >= 8) { // out of board
				continue;
			}
			// next position (k2, j2) is valid
			if (board[k2][j2] > 0) { // visited
				continue;
			}

			// (k2, j2) is availabe
			boolean flag = move(k2, j2, step + 1, dy, dx);
			if (flag) { // the sequence of 64 positions is complete
				return true;
			}
		}

		// to restore the state for backtracking
		board[k][j] = 0;
		return false;
	}


 */