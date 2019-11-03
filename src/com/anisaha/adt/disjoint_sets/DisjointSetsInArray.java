package com.anisaha.adt.disjoint_sets;

import java.util.Arrays;

/**
 * Array representation of disjoint sets
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class DisjointSetsInArray {
    private int[] array;

    /**
     * Construct a disjoint sets object.
     *
     * @param numOfElements the initial number of elements also the initial number
     *                      of disjoint sets, since every element is initially in
     *                      its own set.
     **/
    public DisjointSetsInArray(int numOfElements) {
        array = new int[numOfElements];
        Arrays.fill(array, 0, array.length, -1);
    }

    /**
     * union() unites two disjoint sets into a single set. A union-by-rank heuristic
     * is used to choose the new root.
     *
     * @param elem1 the element 1 of either set.
     * @param elem2 the element 2 of either set.
     **/
    public void union(int elem1, int elem2) {
        int root1 = findSet(elem1);
        int root2 = findSet(elem2);

        if (root1 == root2)
            return;

        if (Math.abs(array[root1]) < Math.abs(array[root2]))
            array[root1] = root2;
        else if (Math.abs(array[root2]) < Math.abs(array[root1]))
            array[root2] = root1;
        else {
            // Both trees same height; new one is taller
            array[root1]--;
            array[root2] = root1; // root1 equal or taller; make root1 new root
        }
    }

    /**
     * findSet() finds the (int) name of the set containing a given element.
     * Performs path compression along the way.
     *
     * @param element the element sought.
     * @return the set containing element.
     **/
    public int findSet(int element) {
        // if element is the root of the tree; return it
        if (array[element] < 0)
            return element;
        else {
            array[element] = findSet(array[element]);
            return array[element];
        }
    }

    public static void main(String[] args) {
        int numElements = 8;

        DisjointSetsInArray dSet = new DisjointSetsInArray(numElements);
        dSet.union(1, 2);
        dSet.union(2, 3);
        dSet.union(4, 5);
        dSet.union(6, 7);
        dSet.union(5, 6);
        dSet.union(3, 7);

        System.out.println(dSet.findSet(1));
        System.out.println(dSet.findSet(2));
        System.out.println(dSet.findSet(3));
        System.out.println(dSet.findSet(4));
        System.out.println(dSet.findSet(5));
        System.out.println(dSet.findSet(6));
        System.out.println(dSet.findSet(7));
    }
}
