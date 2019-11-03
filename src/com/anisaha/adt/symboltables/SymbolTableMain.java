package com.anisaha.adt.symboltables;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class SymbolTableMain {
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        st.put("a", 12);
        st.put("b", 13);
        st.put("c", 14);
        st.put("d", 15);
        st.put("e", 16);

        System.out.println("Value associated with c: " + st.get("c"));
        System.out.println("size of the hashMap: " + st.size());
        st.remove("c");
        st.remove("e");
        System.out.println("size of the hashMap after remove: " + st.size());
        System.out.println();

        UnorderedArrayST<String, Integer> st2 = new UnorderedArrayST<>();
        st2.put("ab", 12);
        st2.put("bc", 13);
        st2.put("cd", 14);
        st2.put("de", 15);

        System.out.println("Value associated with bc: " + st2.get("bc"));
        st2.remove("cd");
        System.out.println("size of the unordered Array ST after remove: " + st2.size());
        System.out.println();

        OrderedArrayST<String, Integer> st3 = new OrderedArrayST<>();
        st3.put("ab", 22);
        st3.put("bc", 33);
        st3.put("b", 44); // insert in-between
        st3.put("de", 35);
        st3.put("b", 55); // duplicate key

        System.out.println("Value associated with bc: " + st3.get("bc"));
        System.out.println("size of the ordered Array ST " + st3.size());
        System.out.println();

        LinearProbingHashST<String, Integer> st4 = new LinearProbingHashST<>();
        st4.put("ac", 4);
        st4.put("cb", 5);
        st4.put("ca", 6);
        st4.put("ce", 7);

        System.out.println("Value associated with ac: " + st4.get("ac"));
        st4.delete("cb");
        System.out.println("size of the Linear Probing Hash ST " + st4.size());
        System.out.println();

        BinarySearchTreeST<String, Integer> st5 = new BinarySearchTreeST<>(); // ordered ST
        st5.put("a", 22);
        st5.put("bc", 33);
        st5.put("b", 44); // insert in-between
        st5.put("de", 35);
        st5.put("d", 5);
        st5.put("gh", 67);
        st5.put("a", 55); // duplicate key
        System.out.println("Value associated with b: " + st5.get("b"));
        st5.delete("bc");
        st5.deleteMin();
        System.out.println("min and max Value associated in ST: " + st5.max() + " " + st5.min());
        System.out.println("floor and ceiling Value associated in ST: " + st5.floor("da") + " " + st5.ceiling("a"));
        System.out.println("selecting kth smallest element in ST: " + st5.select(3));
        System.out.println(st5.keys());
    }
}
