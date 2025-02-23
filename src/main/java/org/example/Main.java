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


/*
 * Merge Two Interval Lists Solution using Two Pointers
 *
 * The Two Pointers approach works by maintaining two pointers, one for each input array,
 * and comparing elements at these pointers to merge intervals without overlap.
 *
 * Algorithm walkthrough:
 * 1. Initialize two pointers at the start of both arrays
 * 2. Compare intervals at current pointers and add non-overlapping intervals to result
 * 3. Move pointers based on end values of intervals
 * 4. Continue until all intervals are processed
 * Runtime: O(a + b) --> iterate both lists
 * Space:   O(a + b) --> storing both in result list
 *
 * Edge cases:
 * - Null input arrays
 * - Empty input arrays
 * - Single interval in arrays
 * - No overlapping intervals
 */
public class Main {

    private int[][] mergeIntervals(int[][] a, int[][] b) {
        // Handle null inputs
        if (a == null || b == null) {
            return new int[0][0];
        }

        int aPointer = 0;
        int bPointer = 0;
        List<int[]> result = new ArrayList<>();

        // Process intervals while both arrays have elements to compare
        while (aPointer < a.length && bPointer < b.length) {
            // Get current intervals from both arrays
            int[] currentA = a[aPointer];
            int[] currentB = b[bPointer];

            // Add the interval with smaller start value
            if (currentA[0] <= currentB[0]) {
                addNonOverlappingInterval(result, currentA);
                aPointer++;
            } else {
                addNonOverlappingInterval(result, currentB);
                bPointer++;
            }
        }

        // Process remaining intervals in array A
        while (aPointer < a.length) {
            addNonOverlappingInterval(result, a[aPointer]);
            aPointer++;
        }

        // Process remaining intervals in array B
        while (bPointer < b.length) {
            addNonOverlappingInterval(result, b[bPointer]);
            bPointer++;
        }

        // Convert result list to array
        return result.toArray(new int[result.size()][]);
    }

    private void addNonOverlappingInterval(List<int[]> result, int[] interval) {
        // If result is empty or no overlap with last interval, add new interval
        if (result.isEmpty() || result.get(result.size() - 1)[1] < interval[0]) {
            result.add(new int[]{interval[0], interval[1]});
        } else {
            // Update end time of last interval if current interval overlaps
            result.get(result.size() - 1)[1] =
                    Math.max(result.get(result.size() - 1)[1], interval[1]);
        }
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

/**
 * Algorithm Walkthrough:
 *
 * 1. Initialize two pointers at the start of both arrays
 *    - Set `aPointer = 0` and `bPointer = 0`
 *    - Initialize an empty list `result` to store merged intervals
 *
 * 2. Compare intervals at current pointers and add non-overlapping intervals to result
 *    - While `aPointer < a.length` and `bPointer < b.length`:
 *      - Get `currentA = a[aPointer]` and `currentB = b[bPointer]`
 *      - If `currentA[0] <= currentB[0]`, call `addNonOverlappingInterval(result, currentA)` and increment `aPointer`
 *      - Else, call `addNonOverlappingInterval(result, currentB)` and increment `bPointer`
 *
 * 3. Move pointers based on end values of intervals
 *    - This is handled within the loop by incrementing `aPointer` or `bPointer` based on the comparison
 *
 * 4. Continue until all intervals are processed
 *    - After the loop, process any remaining intervals in array `a`:
 *      - While `aPointer < a.length`, call `addNonOverlappingInterval(result, a[aPointer])` and increment `aPointer`
 *    - Process any remaining intervals in array `b`:
 *      - While `bPointer < b.length`, call `addNonOverlappingInterval(result, b[bPointer])` and increment `bPointer`
 *
 * 5. Convert result list to array
 *    - Return `result.toArray(new int[result.size()][])`
 *
 * Pseudocode for `addNonOverlappingInterval` function:
 * - If `result` is empty or `result.get(result.size() - 1)[1] < interval[0]`:
 *   - Add `interval` to `result`
 * - Else:
 *   - Update the end time of the last interval in `result` to `max(result.get(result.size() - 1)[1], interval[1])`
 *
 * Time Complexity Analysis:
 * - The algorithm processes each interval from both arrays exactly once, resulting in a time complexity of O(n + m), where n is the number of intervals in array `a` and m is the number of intervals in array `b`.
 * - The `addNonOverlappingInterval` function operates in constant time O(1) for each call, as it only checks the last interval in the `result` list.
 */

/**
 * Test Cases:
 *
 * // Test 1: Both arrays are null
 * mergeIntervals(null, null);
 *
 * // Test 2: One array is null, the other is empty
 * mergeIntervals(null, new int[0][0]);
 *
 * // Test 3: Both arrays are empty
 * mergeIntervals(new int[0][0], new int[0][0]);
 *
 * // Test 4: Single interval in each array, no overlap
 * mergeIntervals(new int[][]{{1, 2}}, new int[][]{{3, 4}});
 *
 * // Test 5: Single interval in each array, with overlap
 * mergeIntervals(new int[][]{{1, 5}}, new int[][]{{4, 6}});
 *
 * // Test 6: Multiple intervals, no overlap
 * mergeIntervals(new int[][]{{1, 2}, {5, 6}}, new int[][]{{3, 4}, {7, 8}});
 *
 * // Test 7: Multiple intervals, with overlap
 * mergeIntervals(new int[][]{{1, 3}, {5, 7}}, new int[][]{{2, 6}, {8, 10}});
 *
 * // Test 8: One array is empty, the other has multiple intervals
 * mergeIntervals(new int[0][0], new int[][]{{1, 2}, {3, 4}});
 *
 */