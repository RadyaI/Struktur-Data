package modul_4.Demo.Task2;

/**
 * A single node in the Binary Search Tree.
 * Holds an Employee object and references to left and right children.
 */
public class BSTNode {

    Employee data;   // the employee stored in this node
    BSTNode  left;   // child with smaller employeeId
    BSTNode  right;  // child with larger employeeId

    // ─── Constructor ───────────────────────────────────────────────────────────

    public BSTNode(Employee data) {
        this.data  = data;
        this.left  = null;
        this.right = null;
    }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "BSTNode{" + data + "}";
    }
}