import java.util.Random;
import java.util.Stack;

/**
 * Treap class with generics, implemeting comparable @SmitaRath
 * @param <E>
 */
public class Treap<E extends Comparable<E>> {
    /**
     * Declaring private data fields priority generator and root of the treap @SmitaRath
     */
    private Random priorityGenerator;
    private Node<E> root;

    /**
     * Default Constructor for initializing root and priority generator @SmitaRath
     */
    public Treap() {
        root = null;
        priorityGenerator = new Random();
    }

    /**
     * Parameterized Constructor for initializing root and priority generator @SmitaRath
     */
    public Treap(long seed) {
        root = null;
        priorityGenerator = new Random(seed);
    }

    /**
     * Private Static inner class node with the data fields data,priority,reference to left,and right child @SmitaRath
     * @param <E> Generics
     */
    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        /**
         * Constructor for initialization data and priority sent in the parameters @SmitaRath
         * @param data
         * @param priority
         */
        public Node(E data, int priority) {
            if (data == null)
                throw new NullPointerException();
            this.data = data;
            this.priority = priority;
            left = null;
            right = null;
        }

        /**
         * Method rotateRight() performs a right rotation @SmitaRath
         * @return a reference to the root of the result
         */
        Node<E> rotateRight() {
            if (this.left == null)
                return this;
            Node<E> root1 = this.left;
            Node<E> oldRoot = this;
            oldRoot.left = root1.right;
            root1.right = oldRoot;
            return root1;
        }

        /**
         * Method rotateLeft() performs a left rotation @SmitaRath
         * @return a reference to the root of the result
         */
        Node<E> rotateLeft() {
            if (this.right == null)
                return this;
            Node<E> root1 = this.right;
            Node<E> oldRoot = this;
            oldRoot.right = root1.left;
            root1.left = oldRoot;
            return root1;
        }
        @Override
        /**
         * Overriding toString method for displaying key and priority @SmitaRath
         */
        public String toString(){
            return "(key =" + data + " , priority ="+ priority + ")";
        }
    }

    /**
     * Add method to add node to the treap @SmitaRath
     * @param key - keyvalue sent in the parameter
     * @return - boolean- true if adds successfully, false if already present
     * calls add(key,priority) method to perform the add opeartion
     */
    boolean add(E key) {
        int priority = priorityGenerator.nextInt();
        boolean addResult = add(key, priority);
        return addResult;
    }

    /**
     * Add methos with key and priority to add the new node @SmitaRath
     * @param key - sent in the paramenter
     * @param priority - sent in the parameter
     * @return - boolean- true if adds successfully, false if already present
     */
    boolean add(E key, int priority) {
        /**
         * creates a new node with key and priority
         * creates a stack for storing the visitng nodes
         */
        Node<E> node = new Node(key, priority);
        Stack<Node> stackNode = new Stack<>();
        int compareResult;
        /**
         * if the root is null then adding the new node as root and return true
         */
        if (root == null) {
            root = node;
            return true;
        }
        /**
         *stroing root node in curr variable
         */
        Node<E> curr = root;
        while (true) {
            /**
             * while visiting all the node pushing each node to stack
             */
            stackNode.push(curr);
            /**
             * comapring the node value with the key value
             */
            compareResult = curr.data.compareTo(key);
            /**
             * if node value is smaller than key value and does not has any right child adding new node as right child
             * else setting the curr to curr.right
             */
            if (compareResult < 0) {
                if (curr.right == null) {
                    curr.right = node;
                    break;
                }
                curr = curr.right;
            }
            /**
             * if node value is greater than key value and does not has any left child adding new node as left child
             * else setting the curr to curr.left
             */
            else if (compareResult > 0) {
                if (curr.left == null) {
                    curr.left = node;
                    break;
                }
                curr = curr.left;
            }
            /**
             * if the key is already present return false;
             */
            else {
                return false;
            }
        }

        /**
         * after adding new node to the treap perform reheap function to maintain the max heap structure
         */
        Node<E> newRoot=null;
        /**
         * checking if stack has any nodes and new node priority is greater than parent priority
         */
        while (!(stackNode.size()==0) && stackNode.peek().priority < priority) {
            /**
             * popping the parent node from stack
             */
            curr = stackNode.pop();
            /**
             * if new node is the left child of the parent perform right rotation
             */
            if (curr.left == node) {
                newRoot=curr.rotateRight();
                /**
                 * after rotation changing the parent of the new node in the tree with parent's parent node
                 */
                if(!stackNode.isEmpty()) {
                    if (stackNode.peek().left == curr) stackNode.peek().left = newRoot;
                    if (stackNode.peek().right == curr) stackNode.peek().right = newRoot;
                }

            }
            /**
             * if new node is the right child of the parent perform left rotation
             */
            else if (curr.right == node) {
                newRoot=curr.rotateLeft();
                /**
                 * after rotation changing the parent of the new node in the tree with parent's parent node
                 */
                if(!stackNode.isEmpty()) {
                    if (stackNode.peek().left == curr) stackNode.peek().left = newRoot;
                    if (stackNode.peek().right == curr) stackNode.peek().right = newRoot;
                }
            }
        }
        /**
         * after reheap if new node becomes the root of the treap setting the root as new node
         */
        if(stackNode.isEmpty() && newRoot!=null)
            root=newRoot;
        /**
         * returning true after all the steps.
         */
        return true;
    }

    /**
     * delete method to delete node from the given treap @SmitaRath
     * @param key - sent in the parameter
     * @return - boolean if succesfully deleted true, else false if not present.
     */
    boolean delete(E key){
        /**
         * stroing the root in the curr value, creating stack variable
         */
        Node<E> curr = root;
        int compareResult;
        Stack<Node> stacknodes = new Stack<>();
        Node<E> parentNode =null;
        Node<E> newRoot;
        //checking if the treap has any node
        while(curr!=null) {
            /**
             * pushing all the nodes to stack while visiting
             */
            stacknodes.push(curr);
            /**
             * comapring the key value with node value
             */
            compareResult = curr.data.compareTo(key);
            /**
             * if the compare result is less ,setting the curr to curr.right
             */
            if (compareResult < 0) {
                curr = curr.right;
            }
            /**
             * if the compare result is greater ,setting the curr to curr.left
             */
            else if (compareResult > 0) {
                curr = curr.left;
            }
            //if the compare reuslt is zero, found the node break the loop.
            else {
                stacknodes.pop();
                break;
            }
        }
        /**
         * to delete make the found node as leaf node by performing rotation
         */
        /**
         * is node is found
         */
        if(curr!=null){
            /**
             * if found node is the root and the only node in the treap, setting the root to null returning true.
             */
            if(root==curr && root.right==null && root.left==null) {
                root = null;
                return true;
            }

            /**
             * otherwise getting the parent from the stack.
             */
            if(root!=curr){
                parentNode=stacknodes.pop();
            }
            /**
             * checking if the found node has right child or left child
             */
            while(curr.left!=null || curr.right!=null){
                /**
                 * if the left child is null, rotating left
                 */
                if(curr.left==null) {
                    newRoot = curr.rotateLeft();
                }
                /**
                 * if the right child is null, rotating right
                 */
                else if(curr.right==null) {
                    newRoot = curr.rotateRight();
                }
                /**
                 * if the both childs are present, checking priority, if left priority is greater rotating right
                 */
                else if(curr.left.priority>curr.right.priority) {
                    newRoot = curr.rotateRight();
                }
                /**
                 * if the both childs are present, checking priority, if right priority is greater rotating left
                 */
                else {
                    newRoot = curr.rotateLeft();

                }
                /**
                 * if deleted node is root then setting the new root
                 */
                if(root==curr) {
                    root=newRoot;
                }

                /**
                 * updating the left and right child of the parent node.
                 */
                else{
                    if (parentNode.left == curr) parentNode.left = newRoot;
                    if (parentNode.right == curr) parentNode.right = newRoot;
                }
                /**
                 * setting the new parent as new root returned by rotation
                 */
                parentNode = newRoot;
            }
            /**
             * if found node is left child, deleting left
             */
            if(parentNode.left==curr) parentNode.left=null;
            /**
             * if found node is right child, deleting right
             */
            if(parentNode.right==curr) parentNode.right=null;
            /**
             * returning true
             */
            return true;
        }
        /**
         * returning false if node is not found
         */
        else{
            return false;
        }
    }

    /**
     * private find method to check if key is present or not @SmitaRath
     * @param root - parameter sent root
     * @param key - parameter sent key to find
     * @return - returning true if finds otherwise false
     */
    private boolean find(Node<E> root, E key){
        Node<E> curr = root;
        int compareResult;
        /**
         * checking if treap has any nodes
         */
        while(curr!=null) {
            /**
             * comparing if node value with key value
             */
            compareResult = curr.data.compareTo(key);
            /**
             * if node value is less then search right
             */
            if (compareResult < 0) {
                curr = curr.right;
            }
            /**
             * if node value is greater then search left
             */
            else if (compareResult > 0) {
                curr = curr.left;
            }
            /**
             * if node value is equal then return true
             */
            else {
                return true;
            }
        }
        /**
         * if could not find return false
         */
        return false;
    }

    /**
     * public find method to find the key value @SmitaRath
     * calls helper find method
     * @param key sent in the parameter
     * @return returning true if finds otherwise false
     */
    public boolean find(E key){
        boolean result = find(root,key);
        return result;
    }

    @Override
    /**
     * Overriding toString method to print the treap @SmitaRath
     * calls helper method preOrderTraversal
     * @return treap in string format
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * helper method preOrderTraversal to print the treap @SmitaRath
     * @param node - roof of the treap/subtreap
     * @param depth - depth of the treap/subtreap
     * @param sb - stringbuilder
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {

        /**
         * depending on the depth it will append the space
         */
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        /**
         * if node is null then appending null to the sb
         */
        if (node == null) {
            sb.append("null\n");
        }
        /**
         * appending node tostring format to sb
         * calling preordertraversal for left subtree
         * calling preordertraversal for right subtree
         */
        else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }


    /**
     * Main Method for testing the treap @SmitaRath
     * Cases Test
     * Empty Treap - find,add,delete,toStriing
     * Treap with one Element - find,delete,add,toString
     * Treap with more than one element - add,find,delete,toString
     */
    public static void main(String[] args) {

        System.out.println("Empty Treap--------------------------");
        /**
         * Empty treap adding,deleting, and finding
         */
        Treap<Integer> t1 = new Treap<>();
        /**
         * should return false
         */
        System.out.println(t1.find(7));
        /**
         * should return false
         */
        System.out.println(t1.delete(5));
        /**
         * should return true
         */
        System.out.println(t1.add(4 ,19));
        /**
         * should display treap with one element
         */
        System.out.println(t1.toString());

        System.out.println("Treap with one element-----------------------------");

        /**
         * Treap with one Element deleting,finding, adding
         */
        /**
         * should return false
         */
        System.out.println(t1.find(7));
        /**
         * should return false
         */
        System.out.println(t1.delete(5));
        /**
         * should return ture
         */
        System.out.println(t1.find(4));
        /**
         * should return true
         */
        System.out.println(t1.delete(4));

        /**
         * should return true
         */
        System.out.println(t1.add(2 ,31));
        /**
         * should display treap for one elements
         */
        System.out.println(t1.toString());


        System.out.println("Treap with multiple elements-------------------------");

        t1.add(2 ,31);
        t1.add(6 ,70);
        t1.add (1 ,84);
        t1.add(3 ,12);
        t1.add (5 ,83);
        t1.add(7 ,26);

        /**
         * should return true
         */
        System.out.println(t1.find(7));
        /**
         * should return true
         */
        System.out.println(t1.delete(5));
        /**
         * should return false
         */
        System.out.println(t1.find(25));
        /**
         * should return false
         */
        System.out.println(t1.delete(31));
        /**
         * should return true
         */
        System.out.println(t1.add(11 ,100));
        /**
         * should display treap for all the elements
         */
        System.out.println(t1.toString());

    }


}
