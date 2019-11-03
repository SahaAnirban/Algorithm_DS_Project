package com.anisaha.adt.disjoint_sets;

import java.util.HashMap;
import java.util.Map;

/**
 * Linked List representation of disjoint sets
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class DisjointSets {
    private Map<Long, Node> map = new HashMap<>();

    class Node {
        long data;
        Node parent;
        int rank;
    }

    // Create set with single element
    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node)
            return parent;

        node.parent = findSet(node.parent);
        return node.parent;
    }

    public long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    public boolean union(long a, long b) {
        Node nodeA = map.get(a);
        Node nodeB = map.get(b);

        Node parentA = findSet(nodeA);
        Node parentB = findSet(nodeB);

        // if part of same set do nothing
        if (parentA.data == parentB.data)
            return false;

        // else whoever's rank is higher becomes parent
        if (parentA.rank >= parentB.rank) {
            parentA.rank = (parentA.rank == parentB.rank) ? parentA.rank + 1 : parentB.rank;
            parentB.parent = parentA;
        } else {
            parentA.parent = parentB;
        }
        return true;
    }

    public static void main(String[] args) {
        DisjointSets ds = new DisjointSets();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println("The set of all the nodes is: ");
        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }
}
