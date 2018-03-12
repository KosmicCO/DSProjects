/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NSA;

/**
 *
 * @author Kosmic
 */
public class Finder {
    // 0 -abcd
    // 4 -efgh
    // 8 -ijkl
    // 12 m-n-op
    // 16 -qr-s-t
    // 20 -uvwx
    // 24 -yz
    private static final int[] NUMS = {0, 4, 6, 13, 14, 16, 18, 19, 20, 24};

    public static void main(String[] args) {
        long sett = 0x0; // boolean array
        long array = 0x0;
        int start = 0;

        for (int i = 0; i < NUMS.length; i++) {
            if (NUMS[i] >= NUMS.length) {
                start = i;
                break;
            }
        }

        for (int i = 0; i < NUMS.length; i++) {
            sett |= 0x1 << NUMS[i];
        }

        for (int i = NUMS.length; i < 64; i++) {
            if (start >= NUMS.length) {
                break;
            }
            array = sett;
            if (NUMS[start] < i) {
                start++;
            }
            boolean succ = true;
            for (int j = start; j < NUMS.length; j++) {
                int hash = NUMS[j] % i;
                if (((array >> hash) & 0x1) == 1) {
                    succ = false;
                    break;
                }
                array |= 0x1 << hash;
            }
            if (succ) {
                System.out.println(i);
            }
        }
    }
}
