package com.anisaha.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, 
                        int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static List<List<Integer>> subsetsWithDuplicates(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); 
        backtrackWithDuplicates(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrackWithDuplicates(List<List<Integer>> list, 
                        List<Integer> tempList, int[] nums,
                        int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) // skip duplicates
                continue;

            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        int[] arrDup = { 2, 1, 2 };
        System.out.println(subsets(arr));
        System.out.println(subsetsWithDuplicates(arrDup));
    }

}