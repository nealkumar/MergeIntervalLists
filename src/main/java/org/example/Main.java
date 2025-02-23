package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Given A and B two interval lists, A has no overlap inside A and B has no overlap inside B.
 * Write the function to merge two interval lists, output the result with no overlap.
 *
 * Examples:
 * A: [1,5], [10,14], [16,18]
 * B: [2,6], [8,10], [11,20]
 * output [1,6], [8, 20]
 */

public class Main {

    private int[][] mergeIntervals(int[][] a, int[][] b) {
        if(a == null || b == null) {
            return new int[0][0];
        }

        List<int[]> result = new ArrayList<>();
        while()
    }

    public static void main(String[] args) {
        int[] list1 = new int[]{1,6};
        int[] list1_5 = new int[]{9,15};
        int[] list1_7 = new int[]{20,25};
        int[][] bigList1 = new int[][]{list1,list1_5,list1_7};
        int[] list2 = new int[]{2,8};
        int[] list2_5 = new int[]{10,17};
        int[] list2_7 = new int[]{18,19};
        int[] list2_8 = new int[]{27,30};
        int[][] bigList2 = new int[][]{list2,list2_5,list2_7,list2_8};

        int[][] merged = new Main().mergeIntervals(bigList1, bigList2);
        for(int[] interval : merged) {
            System.out.println(interval[0] + ", " + interval[1]);
        }
    }
}