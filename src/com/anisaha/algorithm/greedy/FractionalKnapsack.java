package com.anisaha.algorithm.greedy;

import java.util.Arrays;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class FractionalKnapsack {

    private static double getKnapSackMaxValue(int[] wt, int[] value, int capacity) {
        Item[] itemArr = new Item[wt.length];
        for (int i = 0; i < wt.length; i++)
            itemArr[i] = new Item(wt[i], value[i]);

        // sorting desc by costRatio
        Arrays.sort(itemArr, (Item i1, Item i2) -> i2.costRatio.compareTo(i1.costRatio));

        double totalValue = 0d;
        for (Item i : itemArr) {
            int currWt = (int) i.weight;
            int currValue = (int) i.value;

            if (capacity - currWt >= 0) {
                capacity -= currWt;
                totalValue += currValue;
            } else {
                double allowedFraction = ((double) capacity / currWt);
                totalValue += currValue * allowedFraction;
                capacity = (int) (capacity - (currWt * allowedFraction));
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[] wt = { 10, 40, 20, 30 };
        int[] value = { 60, 40, 100, 120 };
        int capacity = 50;

        System.out.println("The maximum value we can take :" + getKnapSackMaxValue(wt, value, capacity));
    }

    static class Item {
        Double costRatio;
        double weight, value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            costRatio = (double) (value / weight);
        }
    }
}