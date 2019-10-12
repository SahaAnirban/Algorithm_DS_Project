package com.anisaha.algorithm.greedy;

import java.util.Arrays;

public class ActivitySelection {

    public static void printActivitySelections(Activity[] activities) {
        Arrays.sort(activities, (Activity a1, Activity a2) -> a1.finish.compareTo(a2.finish));

        System.out.println("Following activities are selected from list:");
        
        int i = 0; // previous task index
        System.out.println(activities[i]); // first activity is always selected

        // rest of the activites
        for (int j = 1; j < activities.length; j++) {
            if (activities[j].start >= activities[i].finish) {
                System.out.println(activities[j]);
                i = j;
            }
        }
    }

    public static void main(String[] args) {
        Activity activityArr[] = { new Activity(5, 9), new Activity(1, 2), 
                new Activity(3, 4), new Activity(0, 6),
                new Activity(5, 7), new Activity(8, 9) };

        printActivitySelections(activityArr);
    }

    static class Activity {
        Integer start, finish;

        public Activity(int startSeq, int finishSeq) {
            this.start = startSeq;
            this.finish = finishSeq;
        }

        @Override
        public String toString() {
            return "( start at: " + start + ", finish at: " + finish + " )";
        }
    }
}