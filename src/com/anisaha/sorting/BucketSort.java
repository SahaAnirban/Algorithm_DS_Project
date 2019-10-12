package com.anisaha.sorting;

import java.util.*;

public class BucketSort {

    public static List<Double> bucket_sort(double[] arr , int n){
        List<Double>[] buckets = new List[n];
        Arrays.fill(buckets, new ArrayList<Double>());

        for(double ele: arr){
            buckets[(int)Math.floor(n*ele)].add(ele);
        }

        for(int i = 0; i < n; i++)
            Collections.sort(buckets[i]);


        //Append results
        List<Double> result = new ArrayList<>();
        for(int i = buckets.length - 1; i >= 0; i--) {
            result.addAll(buckets[i]);

            if(result.size() >= n)
                break;
        }

        return result;
    }

    public static void main(String[] args){
        double arr[] = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434, 0.322};

        List<Double> result = bucket_sort(arr, arr.length);
        System.out.println(result);
    }

}
