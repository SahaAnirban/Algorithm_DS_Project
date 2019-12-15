package com.anisaha.string;

import java.util.*;

/**
 * Construct suffix tree using Ukkonen's algorithm
 *
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class SuffixTree {
    private String inpString;
    private STNode root;
    private ActivePoint activePoint;
    private int remainingNum, prefixCount;
    private int[] leafEnd;
    private int[] lcp;

    public SuffixTree() {
        this.root = new STNode(-1, new int[]{-1});
        this.activePoint = new ActivePoint(root, 0);
        this.remainingNum = 0;
        this.prefixCount = 0;
        this.leafEnd = new int[]{-1};
        this.lcp = new int[]{-1, -1};
    }

    private void phrase(int phrase) {
        STNode lastNewNode = null;
        char sChar = inpString.charAt(phrase);
        leafEnd[0] = phrase + 1;
        remainingNum++;

        // loop through extensions
        while (remainingNum > 0) {
            if (activePoint.activeLength == 0)
                activePoint.activeEdge = phrase;

            char activeChar = inpString.charAt(activePoint.activeEdge);
            if (activePoint.activeNode.children.containsKey(activeChar)) {
                if (activePoint.walkDown())
                    continue;

                STNode next = activePoint.activeNode.children.get(activeChar);
                int nextCurrentIdx = next.start + activePoint.activeLength;
                char nextChar = inpString.charAt(nextCurrentIdx);

                // Rule 3
                if (nextChar == sChar) {
                    activePoint.activeLength++;
                    prefixCount++;
                    if (lcp[0] == -1 || lcp[1] - lcp[0] < prefixCount) {
                        lcp[0] = nextCurrentIdx + 1 - prefixCount;
                        lcp[1] = nextCurrentIdx + 1;
                    }

                    // set the suffix link
                    if (lastNewNode != null)
                        lastNewNode.suffixLink = activePoint.activeNode;

                    break;
                }

                // Rule 2 create internal node
                STNode internal = new STNode(next.start, new int[]{nextCurrentIdx});
                activePoint.activeNode.children.put(activeChar, internal);
                if (lcp[0] == -1 || lcp[1] - lcp[0] < prefixCount) {
                    lcp[0] = internal.end[0] - prefixCount;
                    lcp[1] = internal.end[0];
                }

                // update the next node and put as the internal node children
                next.start = nextCurrentIdx;
                internal.children.put(nextChar, next);

                // create the leaf node
                STNode leaf = new STNode(phrase, leafEnd);
                internal.children.put(sChar, leaf);

                // set the suffix link
                if (lastNewNode != null)
                    lastNewNode.suffixLink = internal;

                lastNewNode = internal;
            } else {
                // Rule 2
                STNode leaf = new STNode(phrase, leafEnd);
                activePoint.activeNode.children.put(activeChar, leaf);
                // set the suffix link
                if (lastNewNode != null) {
                    lastNewNode.suffixLink = activePoint.activeNode;
                    lastNewNode = null;
                }
            }
            remainingNum--;

            //update the activePoints
            if (activePoint.activeNode == root && activePoint.activeLength > 0) {
                activePoint.activeEdge = phrase - remainingNum + 1;
                activePoint.activeLength--;
            } else if (activePoint.activeNode != root) {
                activePoint.activeNode = activePoint.activeNode.suffixLink;
            }

            if (prefixCount > 0)
                prefixCount--;
        }
    }

    public void buildSuffixTree() {
        for (int i = 0; i < inpString.length(); i++)
            phrase(i);
    }

    public String getLongestDuplicateSubString(String str) {
        inpString = str;
        buildSuffixTree();
        return lcp[0] == -1 ? "" : inpString.substring(lcp[0], lcp[1]);
    }

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree();
        System.out.println(suffixTree.getLongestDuplicateSubString("abcabxabcd$"));
    }

    /**
     * Active point is a triple (active_node,active_edge,active_length)
     */
    class ActivePoint {
        STNode activeNode;
        int activeEdge; // active char index
        int activeLength;

        public ActivePoint(STNode node, int length) {
            this.activeNode = node;
            activeLength = length;
        }

        public boolean walkDown() {
            STNode child = activeNode.children.get(inpString.charAt(activeEdge));
            if (activeLength >= child.edgeLength()) {
                activeEdge += child.edgeLength();
                activeLength -= child.edgeLength();
                activeNode = child;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Active [activeNode=" + activeNode +
                    ", activeIndex=" + activeEdge +
                    ", activeLength=" + activeLength +
                    "]";
        }
    }

    /**
     * Suffix Tree Node
     */
    class STNode {
        int start;
        int[] end;
        Map<Character, STNode> children;
        STNode suffixLink;

        public STNode(int start, int[] end) {
            this.start = start;
            this.end = end;
            children = new HashMap<>();
        }

        public int edgeLength() {
            return end[0] - start;
        }

    }
}
