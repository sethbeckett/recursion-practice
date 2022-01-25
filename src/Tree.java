
// BinarySearchTree class

import java.util.Random;
import java.util.*;

// In order to build a binary search tree, we need to declare the contents as Comparable.
public class Tree<E extends Comparable<? super E>> {
    final static String END_LINE = "\n";

    /**
     * Public method to create an empty tree
     *
     * @param label Name of tree (for printing)
     */
    public Tree(String label) {
        treeName = label;
        root = null;
        currentNode = null;
    }

    /**
     * Create a bst tree from ArrayList of elements
     *
     * @param elementList list of items to add to the tree
     * @param label       Name of tree (for printing)
     */
    public Tree(ArrayList<E> elementList, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < elementList.size(); i++) {
            insert(elementList.get(i));
        }
    }

    /**
     * Public method to count leaf nodes
     * @return number of leaf nodes
     */
    public int countFringe() {
        int leafCount = countFringe(root);
        return leafCount;
    }

    /**
     * *O(n) Complexity
     * Counts leaves of a tree
     * @param n node to start at
     * @return the number of leaves from that node
     */
    private int countFringe(BinaryNode<E> n) {
        if (n == null) {
            return 0; // return base case of 0 if not a valid node
        }

        if (n.left == null && n.right == null){
            return 1; //increase the count if it's a leaf
        }

        return countFringe(n.left) + countFringe(n.right); // return the count from the right and left
    }

    /**
     * @param element1 element in tree
     * @param element2 second element in tree
     * @return least common ancestor of element1 and element2
     */
    public E lca(E element1, E element2) {
        if (element1.compareTo(element2) < 0) return lca(root, element1, element2);
        return lca(root, element2, element1);
    }

    /**
     * O(logn)
     * @param t        root of subtree
     * @param element1 smaller node value
     * @param element2 larger node value
     * @return the least common ancestor of nodes with values element1 and element2
     */
    private E lca(BinaryNode<E> t, E element1, E element2) {

        int compare1 = t.element.compareTo(element1); //compares t to element1
        int compare2 = t.element.compareTo(element2); //compares t to element2

        //check if on same side of node
        if (compare1 > 0 && compare2 > 0) {
            if (t.left != null) return lca(t.left, element1, element2);
            return t.element;
        }
        if (compare1 < 0 && compare2 < 0) {
            if (t.right != null) return lca(t.right, element1, element2);
        }
        return t.element;
    }

    /**
     * Find predecessor of the currentNode node.
     * Uses currentNode, a local variable set by contains.
     * SIDE EFFECT: Sets local variable currentNode to be the predecessor that is found
     *
     * @return String representation of predecessor
     */
    public String predecessor() {
        //TODO OPTIONAL Write predecessor
        return " ";
    }

    /**
     * Insert into the bst tree;
     *
     * @param newElement the item to insert.
     */
    public void insert(E newElement) {
        root = insert(newElement, root, null);
    }

    /**
     * Find an item in the tree.
     *
     * @param lookFor the item to search for.
     * @return true if lookFor is found.
     */
    public boolean contains(E lookFor) {
        currentNode = null;
        return contains(lookFor, root);
    }

    /**
     * Return a string displaying the tree contents as an indented tree.
     * @return tree in indented string form
     */
    public String toString() {
        String builtString = this.treeName + "\n";
        return toString(root, builtString, "");
    }
    /** O(n)
     * Internal method used to convert a tree to a sideways tree string
     * @param n node to print tree from
     * @param builtString String that gets built up with each recursive call
     * @return builtString completed containing tree
     */
    private String toString(BinaryNode<E> n, String builtString, String indent) {
        if (n != null) {
            indent += "    "; //increase indent each level of recursion
            builtString = toString(n.right, builtString, indent); //go to highest value node in subtree n
            builtString += indent + n.toString() + "\n"; //add node to the string
            builtString = toString(n.left, builtString, indent);// go to lowest value node in subtree m
        }
        return builtString;
    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String listTree() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + listTree(root);
    }

    /**
     * @param level in tree (root starts at 0)
     * @return number of nodes at level
     */
    public int nodesInLevel(int level) {
        //call helper function on the root node
        return nodesInLevel(root, level);
    }

    /** O(n)
     * private method returning the sum of node count for a level
     * @param n the relative node from  which level is checked
     * @param level what level you are checking the node count on
     * @return the total number of nodes for a level
     */
    private int nodesInLevel(BinaryNode<E> n, int level) {
        if (n == null) { //return nothing if you aren't at a node
            return 0;
        }
        if (level == 0) { //if you've hit the level you aimed for, return a 1 for that node
            return 1;
        }
        //create sum to keep track of level count
        int nodeLevelSum = 0;
        if (n.left != null) { //add left level sum if left exists
            nodeLevelSum = nodesInLevel(n.left, level - 1); //left total count
        }
        if (n.right != null) { //add right level sum if exists
            nodeLevelSum += nodesInLevel(n.right, level - 1); //right total count
        }
        return nodeLevelSum;
    }

    /** O(logn)
     * @param n the ordinal number of the node desired
     * @return return the string representing the nth node in a bst
     */
    public String findNthInOrder(int n) {
        //TODO need to write
//        BinaryNode<E> nth = findNthInOrder(root, n);
//        if (nth == null) return "NONE";
//        else return nth.toString();
        return findNthInOrder(root, n);
    }
    private String findNthInOrder(BinaryNode<E> node, int n) {
//        System.out.println("node is" + node );
        if (n > node.leftCt + node.rightCt + 1) { // check if n is in possible range of nodes
            return "None";
        }
        if (n == node.leftCt + 1) { //return the node if n is found
            return node.element.toString();
        }
        if (n <= node.leftCt) { //check if n is on left
            return findNthInOrder(node.left, n);
        }
        else { //n is on right
//            System.out.println("n went right, n is " + n + "\n");
            return findNthInOrder(node.right, n - (node.leftCt + 1));
        }
    }

    /** Calculates the width of a tree by calling a function which calculates the maximum width of its subtrees
     *
     * @return width of the tree
     */
    public int width() {
        //call the width function on the two subtrees
        return width(root);
    }

    /** O(n^2)
     * Return a tree's width, and updates height while calculating width
     * @param n node of which you're calculating width
     * @return int of width
     */
    private int width(BinaryNode<E> n) {
        if (n == null) return 0;
        int leftWidth = width(n.left);
        int rightWidth = width(n.right);
        int rootWidth = getHeight(n.left) + getHeight(n.right) + 1;
        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
        return Math.max(rootWidth, Math.max(leftWidth,rightWidth));
    }

    /** O(1)
     * return the height of a node
     * @param n the node of interest
     * @return height of node
     */
    private int getHeight(BinaryNode<E> n) {
        if (n == null) return 0;
        return n.height;
    }

    /** O(n
     * @param t2 tree to compare to
     * @return true if this tree and t2 are isomorphic (same shape)
     */
    public boolean isIsomorphic(Tree<E> t2) {
        //call helper function
        return isIsomorphic(this.root, t2.root);
    }

    /**
     * O(n)
     * @param n1 first node to compare (root of subtree)
     * @param n2 second node to compare (root of subtree)
     * @return boolean of isomorphism
     */
    private boolean isIsomorphic(BinaryNode<E> n1, BinaryNode<E> n2) {
        if (n1 == null && n2 == null) return true; //base case where you reached the end of a branch
        if (n1 == null && n2 != null) return false; //not isomorphic base case
        if (n1 != null && n2 == null) return false; // other not isomorphic base case

        return isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right, n2.right);
    }

    /** O(n^2)
     * @param t2 tree to compare to
     * @return true if this tree and t2 are quasi isomorphic
     */
    public boolean isQuasiIsomorphic(Tree<E> t2) {
        return isQuasiIsomorphic(this.root, t2.root);
    }

    private boolean isQuasiIsomorphic(BinaryNode<E> n1, BinaryNode<E> n2) {
        if (n1 == null && n2 == null) return true; //base case where you reached the end of a branch
        if (n1 == null && n2 != null) return false; //not isomorphic base case (adding n2 != null is redundant)
        if (n1 != null && n2 == null) return false; // other not isomorphic base case (adding n1 != null is redundant)

        // cases that separate the iso from quasi is everything after the or
        return (isQuasiIsomorphic(n1.left, n2.left) && isQuasiIsomorphic(n1.right, n2.right)) ||
                (isQuasiIsomorphic(n1.left, n2.right) || isQuasiIsomorphic(n1.right, n2.left));
    }

    /**
     * Print all paths from root to each leaf
     */
    public void printAllPaths() {
        stringAllPaths(root, "");
    }

    /** O(n)
     * Print a string with the paths from a node to its children
     * @param n parent node from which the check for children paths is made
     * @param subString string keeping track of the path to a leaf node
     */
    private void stringAllPaths(BinaryNode n, String subString) {
        subString += n.element + " ";
        if (n.left == null && n.right == null) {
            System.out.println(subString);
        }
        if (n.left != null) {
            stringAllPaths(n.left, subString);
        }
        if (n.right != null) {
            stringAllPaths(n.right, subString);
        }
    }
    //my crappy first attempt that I got stuck on for a long time...
//    private String stringAllPaths(BinaryNode<E> n, String subString, String metaString) {
////        set substring
//        subString += n.element + " ";
////        if the node is null return the sub+meta
//        if (n.left == null && n.right == null) {
//            metaString = subString + "\n";
//            return metaString;
//        }
//        //set metaString += to all of the left paths if n.left exists
//        if (n.left != null) {
//            metaString += stringAllPaths(n.left, subString, metaString);
//        }
//        //set metaString += to all the right paths too if right child exists
//        if (n.right != null) {
//            metaString += stringAllPaths(n.right, subString, metaString);
//        }
        //return the metaString
//        return metaString;
//    }


    /** O(logn)
     * Internal method to insert into a bst subtree.
     * This routine runs in O(??)
     *
     * @param newItem     the item to insert.
     * @param subtreeRoot root of the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> insert(E newItem, BinaryNode<E> subtreeRoot, BinaryNode<E> parent) {
        if (subtreeRoot == null)
            return new BinaryNode<>(newItem, null, null, parent, 0, 0);

        int compareResult = newItem.compareTo(subtreeRoot.element);
        if (compareResult <= 0) {
            subtreeRoot.left = insert(newItem, subtreeRoot.left, subtreeRoot);
            subtreeRoot.leftCt++; //add to the left tree count to account if it goes to left
        } else {
            subtreeRoot.right = insert(newItem, subtreeRoot.right, subtreeRoot);
            subtreeRoot.rightCt++; //add to right tree count, accounts for new node going right
        }

        return subtreeRoot;
    }

    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) on a balanced tree as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree:
     * a=1, b=2, k=0
     *
     * @param element     is item to search for.
     * @param subtreeRoot root of subtree.
     *                    SIDE EFFECT: Sets local variable currentNode to be the node that is found
     * @return true if item is found
     */
    private boolean contains(E element, BinaryNode<E> subtreeRoot) {
        if (subtreeRoot == null)
            return false;

        int compareResult = element.compareTo(subtreeRoot.element);

        if (compareResult < 0)
            return contains(element, subtreeRoot.left);
        else if (compareResult > 0)
            return contains(element, subtreeRoot.right);
        else {
            currentNode = subtreeRoot;
            return true;    // Match
        }
    }


    /** O(n)
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     *
     * @param t the node that roots the subtree.
     */
    private String listTree(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(listTree(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(listTree(t.right));
        return sb.toString();
    }


    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null, 0, 0);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt, int leftCt, int rightCt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
            this.leftCt = leftCt;
            this.rightCt = rightCt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node
        int leftCt;  // Count of nodes in left subtree
        int rightCt; // Count of nodes in right subtree
        int height = 0; // Height of node

        /**
         * Create a printable version of Binary Node
         * Note the toString for Node can be used to print the tree
         *
         * @return String representation of Binary Node
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("[");
            } else {
                sb.append("[");
                sb.append(parent.element);
            }
            sb.append("] ");
            sb.append("<" + leftCt + "," + rightCt + ">");
            return sb.toString();
        }


    }


    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> currentNode;  // Last node accessed in tree
    private String treeName;     // Name of tree for printing


    // Main is a driver program for the various tests.
    // Critical logic to solve the problem needs to be elsewhere
    public static void main(String[] args) {
        long SEED = 5435;
        Random rand = new Random(SEED);  // Don't use a seed if you want the numbers to be different each time
        final String END_LINE = "\n";

        ArrayList<Integer> v1 = new ArrayList<>(Arrays.asList(25, 10, 60, 55, 58, 56, 14, 75, 80, 20, 5, 7, 61, 62, 63));
        ArrayList<Integer> v2 = new ArrayList<>();
        ArrayList<Integer> v3 = new ArrayList<>();
        ArrayList<Integer> v4 = new ArrayList<>();
        ArrayList<Integer> v5 = new ArrayList<>();
        ArrayList<Integer> v6 = new ArrayList<>();
        ArrayList<Integer> v7 = new ArrayList<>();

        final int SIZE = 8;
        // Create ArrayLists to facilitate testing
        for (int i = 0; i < SIZE; i++) {
            int t = rand.nextInt(100);
            //System.out.println( " t is " + t );
            v2.add(t);
            v3.add(t + rand.nextInt(5));
            v4.add(t + 18);
            v5.add(100 - t);
            v7.add(200 - t);
        }
        for (int i = 0; i < 3 * SIZE; i++) {
            int t = rand.nextInt(100);
            v6.add(t);
        }
        int val = 37;
        v6.add(val);
        Tree<Integer> tree1 = new Tree<>(v1, "Tree1:");
        Tree<Integer> tree2 = new Tree<>(v2, "Tree2:");
        Tree<Integer> tree3 = new Tree<>(v3, "Tree3:");
        Tree<Integer> tree4 = new Tree<>(v4, "Tree4:");
        Tree<Integer> tree5 = new Tree<>(v5, "Tree5:");
        Tree<Integer> tree6 = new Tree<>(v6, "Tree6:");
        Tree<Integer> tree7 = new Tree<>(v7, "Tree7:");


// Test Item 1 and 2
//        System.out.println(tree1);
//        System.out.println(tree1.listTree());
//        System.out.println(tree2.listTree());
//        System.out.println(tree3.listTree());
//        System.out.println(tree4.listTree());
//        System.out.println(tree5.listTree());
//        System.out.println(tree6.listTree());
//        System.out.println(tree7.listTree());

        //TODO uncomment and test each item separately
// Test Item 3
//        tree1.printAllPaths();


 // Test Item 4
//        System.out.println("Fringe count=" + tree1.countFringe());

// // Test Item 5
//        for (int level = 0; level <= 6; level += 2) {
//            System.out.println("Number nodes in tree1 at level " + level + " is " + tree1.nodesInLevel(level));
//        }
//
//  // Test Item 6
//        System.out.println(tree6.toString());
//        for (int k = 2; k < 20; k += 5) {
//            System.out.println("In tree6, the " + k + "th smallest value is  " + tree6.findNthInOrder(k));
//            //System.out.println(tree6.listTree());
//        }
//
//  // Test Item 7
//        System.out.println("The width of  tree1 is " + tree1.width() + END_LINE);
//        System.out.println(tree2.toString());
//        System.out.println("The width of  tree2 is " + tree2.width() + END_LINE);
 // Test Item 7
//        System.out.println("The width of  tree1 is " + tree1.width() + END_LINE);
//        System.out.println(tree2.toString());
//        System.out.println("The width of  tree2 is " + tree2.width() + END_LINE);

//        System.out.println(tree1);
//        System.out.println(tree2);
//        System.out.println(tree3);
//        System.out.println(tree4);
//        System.out.println(tree5);
//        System.out.println(tree7);
//
 // Test Item 8
//        if (tree2.isIsomorphic(tree3)) System.out.println("Trees 2 and 3 are Isomorphic");
//        if (tree2.isIsomorphic(tree4)) System.out.println("Trees 2 and 4 are Isomorphic");
//        if (tree3.isIsomorphic(tree4)) System.out.println("Trees 3 and 4 are Isomorphic");
//        if (tree5.isIsomorphic(tree7)) System.out.println("Trees 5 and 7 Are Isomorphic");

 // Test Item 9
//        if (tree2.isQuasiIsomorphic(tree3)) System.out.println("Trees 2 and 3 Are Quasi-Isomorphic");
//        if (tree2.isQuasiIsomorphic(tree5)) System.out.println("Trees 2 and 5 Are Quasi-Isomorphic");
//        if (tree2.isQuasiIsomorphic(tree4)) System.out.println("Trees 2 and 4 Are Quasi-Isomorphic");
//        if (tree1.isQuasiIsomorphic(tree7)) System.out.println("Trees 1 and 7 Are Quasi-Isomorphic");
 // Test Item 10
        System.out.println( tree1.toString( ) );
        System.out.println( "Least Common Ancestor of (56,61) " + tree1.lca( 56, 61 ) + END_LINE );
        System.out.println( "Least Common Ancestor (58,55) " + tree1.lca( 58, 55 ) + END_LINE );
        System.out.println( "Least Common Ancestor of (8,61) " + tree1.lca( 8, 61 ) + END_LINE );
        System.out.println( "Least Common Ancestor (1,10) " + tree1.lca( 1, 10 ) + END_LINE );
//
//  //Test Bonus 1
//        System.out.println( tree6.toString(  ) );
//        tree6.contains( val );  //Sets the currentNode node inside the tree6 class.
//        System.out.println( "In Tree6, starting at " + val + END_LINE );
//        System.out.println( tree6.toString( ) );
//        int predCount=8;  // how many predecessors do you want to see?
//        for (int i = 0; i < predCount; i++) {
//            System.out.println( "The next predecessor is " + tree6.predecessor() );
//        }
//  // Test Bonus 2
//        Tree<Integer> tree8 = new Tree<>("Tree8:");
//        Integer[] inorder = { 4, 2, 1, 7, 5, 8, 3, 6};
//        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};
//        tree8.buildTreeTraversals(inorder, preorder, " From Traversals 1");
//        System.out.println(tree8);
//
//        Integer[] inorder2 = { 2,3,6,7,10,15,20,29};
//        Integer [] preorder2 = {10,6,2,3,7,20,15,29};
//        tree8.buildTreeTraversals(inorder2, preorder2, " From Traversals 2");
//        System.out.println(tree8);


    }
}
