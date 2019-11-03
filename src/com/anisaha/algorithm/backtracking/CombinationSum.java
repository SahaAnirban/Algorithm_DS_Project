package com.anisaha.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList,
                                  int[] nums, int remain, int start) {
        if (remain < 0)
            return;
        else if (remain == 0)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }

    }

    // Combination Sum (can't reuse same element)
    public static List<List<Integer>> combinationSumWithoutReUse(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackWithoutDuplicates(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrackWithoutDuplicates(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0)
            return;
        else if (remain == 0)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) // skip duplicates
                    continue;

                tempList.add(nums[i]);
                backtrackWithoutDuplicates(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 2,3,5 };
        int[] arrDup = { 2,5,2,1,2 };
        System.out.println(combinationSum(arr, 8));
        System.out.println(combinationSumWithoutReUse(arrDup, 5));
    }


}