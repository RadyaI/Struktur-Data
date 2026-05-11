package modul_4.Demo.Task2;

public class EmployeeBST {

    private BSTNode root;

    public void insert(Employee emp) {
        if (emp == null) {
            System.out.println("[Warning] Cannot insert null employee.");
            return;
        }
        root = insertRecursive(root, emp);
        System.out.println("[Insert] " + emp);
    }

    private BSTNode insertRecursive(BSTNode current, Employee emp) {
        if (current == null) return new BSTNode(emp);

        int cmp = emp.compareTo(current.data);

        if (cmp < 0) {
            current.left = insertRecursive(current.left, emp);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, emp);
        } else {
            System.out.println("[Warning] Employee ID " + emp.getEmployeeId() + " already exists. Skipping.");
        }

        return current;
    }

    public Employee search(int employeeId) {
        BSTNode result = searchRecursive(root, employeeId);
        if (result == null) {
            System.out.println("[Search] Employee ID " + employeeId + " → NOT FOUND.");
            return null;
        }
        System.out.println("[Search] Employee ID " + employeeId + " → FOUND: " + result.data);
        return result.data;
    }

    private BSTNode searchRecursive(BSTNode current, int id) {
        if (current == null)                  return null;
        if (current.data.getEmployeeId() == id) return current;

        if (id < current.data.getEmployeeId())
            return searchRecursive(current.left, id);
        else
            return searchRecursive(current.right, id);
    }

    public void delete(int employeeId) {
        if (searchRecursive(root, employeeId) == null) {
            System.out.println("[Delete] Employee ID " + employeeId + " not found. Nothing to delete.");
            return;
        }
        root = deleteRecursive(root, employeeId);
        System.out.println("[Delete] Employee ID " + employeeId + " removed successfully.");
    }

    private BSTNode deleteRecursive(BSTNode current, int id) {
        if (current == null) return null;

        int currentId = current.data.getEmployeeId();

        if (id < currentId) {
            current.left = deleteRecursive(current.left, id);

        } else if (id > currentId) {
            current.right = deleteRecursive(current.right, id);

        } else {

            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.left == null)  return current.right;
            if (current.right == null) return current.left;

            BSTNode successorNode = findMinNode(current.right);
            current.data  = successorNode.data;
            current.right = deleteRecursive(current.right,
                    successorNode.data.getEmployeeId());
        }

        return current;
    }

    public Employee findMin() {
        if (root == null) {
            System.out.println("[Min] Tree is empty.");
            return null;
        }
        Employee min = findMinNode(root).data;
        System.out.println("[Min] Smallest ID → " + min);
        return min;
    }

    private BSTNode findMinNode(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public Employee findMax() {
        if (root == null) {
            System.out.println("[Max] Tree is empty.");
            return null;
        }
        Employee max = findMaxNode(root).data;
        System.out.println("[Max] Largest ID  → " + max);
        return max;
    }

    private BSTNode findMaxNode(BSTNode node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public void inorderTraversal() {
        System.out.println("\n=== In-Order Traversal (sorted by ID) ===");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(BSTNode node) {
        if (node == null) return;
        inorderRecursive(node.left);
        System.out.println("  " + node.data);
        inorderRecursive(node.right);
    }

    public void preorderTraversal() {
        System.out.println("\n=== Pre-Order Traversal (Root → Left → Right) ===");
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(BSTNode node) {
        if (node == null) return;
        System.out.println("  " + node.data);
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    public void postorderTraversal() {
        System.out.println("\n=== Post-Order Traversal (Left → Right → Root) ===");
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(BSTNode node) {
        if (node == null) return;
        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.println("  " + node.data);
    }

    public void printTree() {
        System.out.println("\n=== BST Visualization (rotated: right subtree on top) ===");
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        printTreeRecursive(root, "", true);
    }

    private void printTreeRecursive(BSTNode node, String prefix, boolean isRight) {
        if (node == null) return;

        printTreeRecursive(node.right, prefix + (isRight ? "        " : "│       "), true);

        System.out.println(prefix + (isRight ? "┌── " : "└── ")
                + "[" + node.data.getEmployeeId() + "] " + node.data.getName());

        printTreeRecursive(node.left, prefix + (isRight ? "│       " : "        "), false);
    }


    public int size() { return sizeRecursive(root); }
    private int sizeRecursive(BSTNode node) {
        if (node == null) return 0;
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    public int height() { return heightRecursive(root); }
    private int heightRecursive(BSTNode node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    public void printStats() {
        System.out.println("\n=== BST Statistics ===");
        System.out.println("Total Employees : " + size());
        System.out.println("Tree Height     : " + height());
    }


    public static void main(String[] args) {

        EmployeeBST bst = new EmployeeBST();

        System.out.println("========== INSERT ==========");
        bst.insert(new Employee(100, "Anton Wijaya",   "Operasional",  "Masinis",    "anton@kai.id"));
        bst.insert(new Employee(102, "Budi Santoso",   "Teknik",       "Teknisi",    "budi@kai.id"));
        bst.insert(new Employee(198, "Sari Dewi",      "Administrasi", "Staf Admin", "sari@kai.id"));
        bst.insert(new Employee(105, "Rina Kurnia",    "Operasional",  "Kondektur",  "rina@kai.id"));
        bst.insert(new Employee(101, "Ayu Lestari",    "Keuangan",     "Akuntan",    "ayu@kai.id"));
        bst.insert(new Employee(110, "Doni Prasetyo",  "Teknik",       "Insinyur",   "doni@kai.id"));
        bst.insert(new Employee(195, "Mega Pertiwi",   "SDM",          "HRD",        "mega@kai.id"));
        bst.insert(new Employee(102, "Duplikat",       "???",          "???",        "dup@kai.id")); // duplicate test

        bst.printTree();

        bst.inorderTraversal();
        bst.preorderTraversal();
        bst.postorderTraversal();

        System.out.println("\n========== MIN / MAX ==========");
        bst.findMin();
        bst.findMax();

        System.out.println("\n========== SEARCH ==========");
        bst.search(105);   // found
        bst.search(999);   // not found

        bst.printStats();

        System.out.println("\n========== DELETE ==========");

        System.out.println("--- Case 1: Delete leaf node [095] Mega ---");
        bst.delete(195);
        bst.printTree();

        System.out.println("--- Case 2: Delete node with one child [098] Sari ---");
        bst.delete(198);
        bst.printTree();

        System.out.println("--- Case 3: Delete node with two children [102] Budi ---");
        bst.delete(102);
        bst.printTree();

        System.out.println("--- Delete non-existent ID [777] ---");
        bst.delete(777);

        bst.inorderTraversal();
        bst.printStats();
    }
}