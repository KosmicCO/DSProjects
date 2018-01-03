
import edu.princeton.cs.algs4.MaxPQ;

/**
 *
 * @author Kosmic
 */
public class CubeSum implements Comparable<CubeSum> {

    private final long sum;
    private final int x, y;

    private CubeSum(long sum, int x, int y) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }

    public static CubeSum[] initList(int n) {
        CubeSum[] csList = new CubeSum[n];
        for (int i = 0; i < n; i++) {
            csList[i] = new CubeSum((long) i * i * i, i, 0);
        }
        return csList;
    }

    public CubeSum nextSumY() {
        return new CubeSum(sum + 3 * y * (y + 1) + 1, x, y + 1);
    }

    @Override
    public int compareTo(CubeSum o) {
        return o.sum > sum ? 1 : (o.sum == sum ? 0 : -1);
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getSum() {
        return sum;
    }

    public String getAdded() {
        return "" + x + " + " + y;
    }

    @Override
    public String toString() {
        return "" + sum + ": " + x + " + " + y;
    }

    public static void main(String[] args) {
        int n = 100;
        boolean multMode = true;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            multMode = args[1].equals("true");
        }

        MaxPQ<CubeSum> pq = new MaxPQ(initList(n));

        CubeSum ret;
        CubeSum prev = null;
        boolean prevSame = false;
        while (!pq.isEmpty()) {
            ret = pq.delMax();
            if (ret.getY() < ret.getX()) {
                pq.insert(ret.nextSumY());
            }
            if (multMode) {
                if (prev != null) {
                    if (prev.compareTo(ret) == 0) {
                        if (prevSame) {
                            System.out.print(" and " + ret.getAdded());
                        } else {
                            System.out.print("" + ret.sum + ": " + prev.getAdded() + " and " + ret.getAdded());
                        }
                        prevSame = true;
                    } else {
                        if (prevSame) {
                            System.out.println();
                            prevSame = false;
                        }
                    }
                }
                prev = ret;
            } else {
                System.out.println(ret);
            }
        }
    }
}

//Output:
//
//n = 10, multMode = false
//
//run:
//0: 0 + 0
//1: 1 + 0
//2: 1 + 1
//8: 2 + 0
//9: 2 + 1
//16: 2 + 2
//27: 3 + 0
//28: 3 + 1
//35: 3 + 2
//54: 3 + 3
//64: 4 + 0
//65: 4 + 1
//72: 4 + 2
//91: 4 + 3
//125: 5 + 0
//126: 5 + 1
//128: 4 + 4
//133: 5 + 2
//152: 5 + 3
//189: 5 + 4
//216: 6 + 0
//217: 6 + 1
//224: 6 + 2
//243: 6 + 3
//250: 5 + 5
//280: 6 + 4
//341: 6 + 5
//343: 7 + 0
//344: 7 + 1
//351: 7 + 2
//370: 7 + 3
//407: 7 + 4
//432: 6 + 6
//468: 7 + 5
//512: 8 + 0
//513: 8 + 1
//520: 8 + 2
//539: 8 + 3
//559: 7 + 6
//576: 8 + 4
//637: 8 + 5
//686: 7 + 7
//728: 8 + 6
//729: 9 + 0
//730: 9 + 1
//737: 9 + 2
//756: 9 + 3
//793: 9 + 4
//854: 9 + 5
//855: 8 + 7
//945: 9 + 6
//1024: 8 + 8
//1072: 9 + 7
//1241: 9 + 8
//1458: 9 + 9
//BUILD SUCCESSFUL (total time: 0 seconds)
//
//n = 100, multMode = true
//
//run:
//1729: 10 + 9 and 12 + 1
//4104: 15 + 9 and 16 + 2
//13832: 20 + 18 and 24 + 2
//20683: 24 + 19 and 27 + 10
//32832: 30 + 18 and 32 + 4
//39312: 33 + 15 and 34 + 2
//40033: 33 + 16 and 34 + 9
//46683: 30 + 27 and 36 + 3
//64232: 36 + 26 and 39 + 17
//65728: 40 + 12 and 33 + 31
//110656: 40 + 36 and 48 + 4
//110808: 45 + 27 and 48 + 6
//134379: 43 + 38 and 51 + 12
//149389: 50 + 29 and 53 + 8
//165464: 48 + 38 and 54 + 20
//171288: 54 + 24 and 55 + 17
//195841: 57 + 22 and 58 + 9
//216027: 59 + 22 and 60 + 3
//216125: 50 + 45 and 60 + 5
//262656: 60 + 36 and 64 + 8
//314496: 66 + 30 and 68 + 4
//320264: 66 + 32 and 68 + 18
//327763: 58 + 51 and 67 + 30
//373464: 60 + 54 and 72 + 6
//402597: 69 + 42 and 61 + 56
//439101: 69 + 48 and 76 + 5
//443889: 76 + 17 and 73 + 38
//513000: 75 + 45 and 80 + 10
//513856: 78 + 34 and 72 + 52
//515375: 71 + 54 and 80 + 15
//525824: 80 + 24 and 66 + 62
//558441: 81 + 30 and 72 + 57
//593047: 70 + 63 and 84 + 7
//684019: 82 + 51 and 75 + 64
//704977: 86 + 41 and 89 + 2
//805688: 92 + 30 and 93 + 11
//842751: 84 + 63 and 94 + 23
//885248: 80 + 72 and 96 + 8
//886464: 96 + 12 and 90 + 54
//920673: 97 + 20 and 96 + 33
//955016: 98 + 24 and 89 + 63
//984067: 92 + 59 and 98 + 35
//994688: 92 + 60 and 99 + 29
//1009736: 96 + 50 and 93 + 59
//1016496: 97 + 47 and 90 + 66
//BUILD SUCCESSFUL (total time: 0 seconds)
