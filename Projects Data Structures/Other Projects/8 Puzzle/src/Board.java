
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class Board {

    private final int[][] brd;
    private int manhattan;
    private int hamming;
    private int zeroX;
    private int zeroY;

    public Board(int[][] blocks) {

        if (blocks == null) {
            throw new IllegalArgumentException();
        }

        brd = new int[blocks.length][blocks[0].length];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                brd[i][j] = blocks[i][j];
                if (brd[i][j] == 0) {
                    zeroX = j;
                    zeroY = i;
                }
            }
        }

        manhattan = fullManhattan();
        hamming = -1;
    }

    private Board(Board parent) {
        brd = new int[parent.brd.length][parent.brd.length];

        for (int i = 0; i < parent.brd.length; i++) {
            System.arraycopy(parent.brd[i], 0, brd[i], 0, parent.brd.length);
        }

        manhattan = -1;
        hamming = -1;
    }

    private static int dir(int d, int p) {
        return ((d & 0x0000_0001) != p) ? (((d & 0x0000_0002) == 0) ? 1 : -1) : 0;
    }

    public int dimension() {
        return brd.length;
    }

    private int fullHamming() {
        int sum = 0;
        int num = 1;
        for (int[] brd1 : brd) {
            for (int j = 0; j < brd.length; j++) {
                if (brd1[j] != num && brd1[j] != 0) {
                    sum++;
                }
                num++;
            }
        }
        return sum;
    }

    public int hamming() {
        if (hamming == -1) {
            hamming = fullHamming();
        }
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    private int fullManhattan() {
        int sum = 0;
        int num;
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                num = brd[i][j] - 1;
                if (num != -1) {
                    sum += Math.abs((num / brd.length) - i) + Math.abs((num % brd.length) - j);
                }
            }
        }
        return sum;
    }

    public boolean isGoal() {

        return manhattan == 0;

//        int num = 1;
//        for (int i = 0; i < brd.length; i++) {
//            for (int j = 0; j < brd.length; j++) {
//                if (brd[i][j] != num && !(i == brd.length - 1 && j == brd.length - 1)) {
//                    return false;
//                }
//                num++;
//            }
//        }
//        return true;
    }

    public Board twin() {
        Board retB = new Board(brd);

        if (retB.brd[0][0] == 0 || retB.brd[1][0] == 0) {
            retB.brd[0][1] = brd[1][1];
            retB.brd[1][1] = brd[0][1];
            retB.manhattan = retB.fullManhattan(); // += moveManhattan(1, 1, brd[1][1], 2) + moveManhattan(1, 0, brd[0][1], 0);
        } else {
            retB.brd[0][0] = brd[1][0];
            retB.brd[1][0] = brd[0][0];
            retB.manhattan = retB.fullManhattan(); // += moveManhattan(0, 1, brd[1][1], 2) + moveManhattan(0, 0, brd[0][1], 0);
        }

        return retB;
    }

    @Override
    public boolean equals(Object y) {
        if (!(this.getClass().isInstance(y))) {
            return false;
        }

        Board other = (Board) y;

        if (brd.length != other.brd.length) {
            return false;
        }

        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                if (brd[i][j] != other.brd[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int moveManhattan(int x, int y, int num, int d) {

        int ind;

        if ((d & 0x0000_0001) == 0) { // working in y
            ind = ((num - 1) / brd.length);
            if (y < ind) {
                return manhattan - dir(d, 1);
            } else if (y > ind) {
                return manhattan + dir(d, 1);
            }
        } else { // working in x
            ind = ((num - 1) % brd.length);
            if (x < ind) {
                return manhattan - dir(d, 0);
            } else if (x > ind) {
                return manhattan + dir(d, 0);
            }
        }

        return manhattan + 1;
    }
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 67 * hash + Arrays.deepHashCode(this.brd);
//        return hash;
//    }

    public Iterable<Board> neighbors() {
        List<Board> ret = new ArrayList<Board>();
        int x, y;
        Board work;

        for (int i = 0; i < 4; i++) {
            x = zeroX + dir(i, 0);
            y = zeroY + dir(i, 1);
            if (x < 0 || brd.length <= x || y < 0 || brd.length <= y) {
                continue;
            }
            work = new Board(this);
            work.manhattan = moveManhattan(x, y, brd[y][x], i ^ 0x0000_0002);
            work.brd[zeroY][zeroX] = work.brd[y][x];
            work.brd[y][x] = 0;
            work.zeroX = x;
            work.zeroY = y;
            ret.add(work);

        }

        return ret;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(brd.length).append("\n");
        for (int[] brd1 : brd) {
            for (int j = 0; j < brd.length; j++) {
                s.append(String.format("%2d ", brd1[j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] bbb = {{1, 2, 3},
        {0, 4, 6},
        {7, 5, 8}};

        Board b = new Board(bbb);
        System.out.println(b);
        System.out.println(b.manhattan());

        System.out.println(b.twin());

//        List<Board> bl = new ArrayList<Board>();
//        List<Board> bl2 = new ArrayList<Board>();
//
//        bl.add(b);
//        for (int i = 0; i < 4; i++) {
//            System.out.println(dir(i, 0) + ", " + dir(i, 1));
//        }
//        System.out.println(b.moveManhattan(1, 2, 5, 0));
//        System.out.println(b.moveManhattan(2, 1, 6, 1));
//        System.out.println(b.moveManhattan(0, 1, 2, 2));
//        System.out.println(b.moveManhattan(1, 0, 4, 3));
//        for (int i = 0; i < 3; i++) {
//            bl2.clear();
//            bl.forEach((b1) -> {
//                for (Board b2 : b1.neighbors()) {
//                    bl2.add(b2);
//                }
//            });
//            bl.addAll(bl2);
//        }
//
//        for (Board b1 : bl) {
//            System.out.println(b1 + " - " + b1.manhattan);
//        }
    }
}
