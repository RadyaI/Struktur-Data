package modul_4.Demo.Task2;

public class BSTNode {

    Employee data;
    BSTNode  left;
    BSTNode  right;

    public BSTNode(Employee data) {
        this.data  = data;
        this.left  = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "BSTNode{" + data + "}";
    }
}