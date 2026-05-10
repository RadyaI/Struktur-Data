package modul_4.Demo.Task2;

/**
 * Binary Search Tree (BST) for managing railway employees.
 * Sorted by employeeId → Left < Root < Right.
 *
 * Operations:
 *   insert, search, delete (all 3 cases), inorder, preorder, postorder,
 *   findMin, findMax, printTree (visual)
 */
public class EmployeeBST {

    private BSTNode root;

    // ─── Insert ────────────────────────────────────────────────────────────────

    /**
     * Public insert: add a new employee while maintaining BST property.
     */
    public void insert(Employee emp) {
        if (emp == null) {
            System.out.println("[Warning] Cannot insert null employee.");
            return;
        }
        root = insertRecursive(root, emp);
        System.out.println("[Insert] " + emp);
    }

    private BSTNode insertRecursive(BSTNode current, Employee emp) {
        // Base case: found an empty spot → create new node here
        if (current == null) return new BSTNode(emp);

        int cmp = emp.compareTo(current.data);

        if (cmp < 0) {
            // emp.id < current.id → go left
            current.left = insertRecursive(current.left, emp);
        } else if (cmp > 0) {
            // emp.id > current.id → go right
            current.right = insertRecursive(current.right, emp);
        } else {
            // Duplicate ID → reject
            System.out.println("[Warning] Employee ID " + emp.getEmployeeId() + " already exists. Skipping.");
        }

        return current;
    }

    // ─── Search ────────────────────────────────────────────────────────────────

    /**
     * Search for an employee by ID.
     * Returns the Employee object if found, null otherwise.
     */
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
        // Base cases: not found or exact match
        if (current == null)                  return null;
        if (current.data.getEmployeeId() == id) return current;

        // BST property: go left if smaller, right if larger
        if (id < current.data.getEmployeeId())
            return searchRecursive(current.left, id);
        else
            return searchRecursive(current.right, id);
    }

    // ─── Delete ────────────────────────────────────────────────────────────────

    /**
     * Public delete: remove an employee by ID.
     * Handles all 3 cases:
     *   Case 1 → Leaf node (no children)
     *   Case 2 → One child
     *   Case 3 → Two children (uses in-order successor)
     */
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
            // Target is in the left subtree
            current.left = deleteRecursive(current.left, id);

        } else if (id > currentId) {
            // Target is in the right subtree
            current.right = deleteRecursive(current.right, id);

        } else {
            // ── Found the node to delete ────────────────────────────────────

            // Case 1: Leaf node (no children) → simply remove
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: One child → replace node with its only child
            if (current.left == null)  return current.right;
            if (current.right == null) return current.left;

            // Case 3: Two children → find in-order successor (smallest in right subtree)
            //         Copy successor's data into current node, then delete the successor
            BSTNode successorNode = findMinNode(current.right);
            current.data  = successorNode.data;                          // overwrite data
            current.right = deleteRecursive(current.right,               // delete successor
                    successorNode.data.getEmployeeId());
        }

        return current;
    }

    // ─── Min / Max ─────────────────────────────────────────────────────────────

    /**
     * Find the employee with the smallest ID (leftmost node).
     */
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

    /**
     * Find the employee with the largest ID (rightmost node).
     */
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

    // ─── Traversals ────────────────────────────────────────────────────────────

    /**
     * In-Order: Left → Root → Right
     * Result: employees sorted ascending by ID.
     */
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

    /**
     * Pre-Order: Root → Left → Right
     */
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

    /**
     * Post-Order: Left → Right → Root
     */
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

    // ─── Visual Tree Print ─────────────────────────────────────────────────────

    /**
     * Print the BST as a visual sideways tree (right subtree on top).
     *
     * Example output (rotated 90° counter-clockwise):
     *
     *         [105] Rina
     *     [102] Budi
     *         [101] Ayu
     * [100] Anton        ← root
     *     [098] Sari
     */
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

        // Print right subtree first (so tree reads left→right when viewed sideways)
        printTreeRecursive(node.right, prefix + (isRight ? "        " : "│       "), true);

        System.out.println(prefix + (isRight ? "┌── " : "└── ")
                + "[" + node.data.getEmployeeId() + "] " + node.data.getName());

        // Then print left subtree
        printTreeRecursive(node.left, prefix + (isRight ? "│       " : "        "), false);
    }

    // ─── Stats ─────────────────────────────────────────────────────────────────

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

    // ─── Main (Demo) ───────────────────────────────────────────────────────────

    public static void main(String[] args) {

        EmployeeBST bst = new EmployeeBST();

        System.out.println("========== INSERT ==========");
        // Sengaja diinsert tidak urut untuk membuktikan BST tetap terurut saat inorder
        bst.insert(new Employee(100, "Anton Wijaya",   "Operasional",  "Masinis",    "anton@kai.id"));
        bst.insert(new Employee(102, "Budi Santoso",   "Teknik",       "Teknisi",    "budi@kai.id"));
        bst.insert(new Employee(198, "Sari Dewi",      "Administrasi", "Staf Admin", "sari@kai.id"));
        bst.insert(new Employee(105, "Rina Kurnia",    "Operasional",  "Kondektur",  "rina@kai.id"));
        bst.insert(new Employee(101, "Ayu Lestari",    "Keuangan",     "Akuntan",    "ayu@kai.id"));
        bst.insert(new Employee(110, "Doni Prasetyo",  "Teknik",       "Insinyur",   "doni@kai.id"));
        bst.insert(new Employee(195, "Mega Pertiwi",   "SDM",          "HRD",        "mega@kai.id"));
        bst.insert(new Employee(102, "Duplikat",       "???",          "???",        "dup@kai.id")); // duplicate test

        // ── Visual Tree ─────────────────────────────────────────────────────────
        bst.printTree();

        // ── Traversals ──────────────────────────────────────────────────────────
        bst.inorderTraversal();    // harus muncul urut: 95, 98, 100, 101, 102, 105, 110
        bst.preorderTraversal();
        bst.postorderTraversal();

        // ── Min / Max ────────────────────────────────────────────────────────────
        System.out.println("\n========== MIN / MAX ==========");
        bst.findMin();
        bst.findMax();

        // ── Search ───────────────────────────────────────────────────────────────
        System.out.println("\n========== SEARCH ==========");
        bst.search(105);   // found
        bst.search(999);   // not found

        // ── Stats ────────────────────────────────────────────────────────────────
        bst.printStats();

        // ── Delete ───────────────────────────────────────────────────────────────
        System.out.println("\n========== DELETE ==========");

        // Case 1: Leaf node
        System.out.println("--- Case 1: Delete leaf node [095] Mega ---");
        bst.delete(195);
        bst.printTree();

        // Case 2: Node with one child
        System.out.println("--- Case 2: Delete node with one child [098] Sari ---");
        bst.delete(198);
        bst.printTree();

        // Case 3: Node with two children
        System.out.println("--- Case 3: Delete node with two children [102] Budi ---");
        bst.delete(102);
        bst.printTree();

        // Delete non-existent
        System.out.println("--- Delete non-existent ID [777] ---");
        bst.delete(777);

        // Final inorder (still sorted?)
        bst.inorderTraversal();
        bst.printStats();
    }
}