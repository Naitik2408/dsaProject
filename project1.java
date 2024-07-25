import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void inOrder() {
        inOrderRec(root);
    }

    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.key + " ");
            inOrderRec(root.right);
        }
    }

    void preOrder() {
        preOrderRec(root);
    }

    void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    void postOrder() {
        postOrderRec(root);
    }

    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    int sumOfNodes(Node root) {
        if (root == null)
            return 0;

        return root.key + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null)
            return false;

        if (root.key == key)
            return true;

        if (root.key < key)
            return searchRec(root.right, key);

        return searchRec(root.left, key);
    }

    Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    void remove(int key) {
        root = removeRec(root, key);
    }

    Node removeRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = removeRec(root.left, key);
        else if (key > root.key)
            root.right = removeRec(root.right, key);
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = findMin(root.right).key;

            // Delete the inorder successor
            root.right = removeRec(root.right, root.key);
        }

        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements to insert: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            tree.insert(key);
        }

        System.out.print("Inorder Traversal gives: ");
        tree.inOrder();
        System.out.println();

        System.out.print("PostOrder Traversal gives: ");
        tree.postOrder();
        System.out.println();

        System.out.print("PreOrder Traversal gives: ");
        tree.preOrder();
        System.out.println();

        // Searching
        System.out.print("Enter the key you want to search: ");
        int key = scanner.nextInt();
        if (tree.search(key)) {
            System.out.println("Key is present in Tree");
        } else {
            System.out.println("Key is not present in Tree");
        }
        System.out.println();

        // Deletion
        System.out.print("Enter the key you want to delete: ");
        key = scanner.nextInt();
        tree.remove(key);

        System.out.print("After deletion, Inorder Traversal gives: ");
        tree.inOrder();
        System.out.println();

        scanner.close();
    }
}
