package modul_4.Demo.Task1;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class StationHierarchy {

    private StationNode root;

    public void setRoot(StationNode station) {
        this.root = station;
        System.out.println("[Info] Root station set: " + station.getStationCode()
                + " - " + station.getStationName());
    }

    public StationNode getRoot() {
        return root;
    }

    public boolean addStation(String parentCode, StationNode newStation) {
        if (root == null) {
            System.out.println("[Error] Tree is empty. Set a root station first.");
            return false;
        }

        StationNode parent = findStation(parentCode);
        if (parent == null) {
            System.out.println("[Error] Parent station not found: " + parentCode);
            return false;
        }

        parent.addChild(newStation);
        System.out.println("[Info] Added station [" + newStation.getStationCode()
                + "] under parent [" + parentCode + "]");
        return true;
    }

    public StationNode findStation(String code) {
        return findStationRecursive(root, code);
    }

    private StationNode findStationRecursive(StationNode current, String code) {
        if (current == null) return null;
        if (current.getStationCode().equals(code)) return current;

        for (StationNode child : current.getChildren()) {
            StationNode result = findStationRecursive(child, code);
            if (result != null) return result;
        }
        return null;
    }

    public boolean removeStation(String code) {
        if (root == null) {
            System.out.println("[Error] Tree is empty.");
            return false;
        }

        if (root.getStationCode().equals(code)) {
            System.out.println("[Warning] Removing root clears the entire tree.");
            root = null;
            return true;
        }

        StationNode target = findStation(code);
        if (target == null) {
            System.out.println("[Error] Station not found: " + code);
            return false;
        }

        StationNode parent = target.getParent();

        for (StationNode child : target.getChildren()) {
            child.setParent(parent);
            parent.getChildren().add(child);
        }

        parent.removeChild(target);

        System.out.println("[Info] Station [" + code + "] removed. "
                + target.getChildren().size() + " child(ren) reassigned to ["
                + parent.getStationCode() + "]");
        return true;
    }

    public void preOrderTraversal() {
        System.out.println("\n=== Pre-Order Traversal (Root → Children) ===");
        preOrderRecursive(root, 0);
    }

    private void preOrderRecursive(StationNode node, int depth) {
        if (node == null) return;

        String indent = "  ".repeat(depth);
        System.out.println(indent + (depth == 0 ? "► " : "└─ ")
                + "[" + node.getStationCode() + "] " + node.getStationName()
                + " (" + node.getRegion() + ")");

        for (StationNode child : node.getChildren()) {
            preOrderRecursive(child, depth + 1);
        }
    }

    public void postOrderTraversal() {
        System.out.println("\n=== Post-Order Traversal (Children → Root) ===");
        postOrderRecursive(root, 0);
    }

    private void postOrderRecursive(StationNode node, int depth) {
        if (node == null) return;

        for (StationNode child : node.getChildren()) {
            postOrderRecursive(child, depth + 1);
        }

        String indent = "  ".repeat(depth);
        System.out.println(indent + (depth == 0 ? "► " : "└─ ")
                + "[" + node.getStationCode() + "] " + node.getStationName()
                + " (" + node.getRegion() + ")");
    }

    public void levelOrderTraversal() {
        System.out.println("\n=== Level-Order Traversal (BFS) ===");
        if (root == null) {
            System.out.println("[Info] Tree is empty.");
            return;
        }

        Queue<StationNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                StationNode current = queue.poll();
                System.out.print("[" + current.getStationCode() + "] "
                        + current.getStationName());
                if (i < levelSize - 1) System.out.print("  |  ");

                for (StationNode child : current.getChildren()) {
                    queue.offer(child);
                }
            }

            System.out.println();
            level++;
        }
    }

    public int getTotalStations() {
        return countNodes(root);
    }

    private int countNodes(StationNode node) {
        if (node == null) return 0;
        int count = 1;
        for (StationNode child : node.getChildren()) {
            count += countNodes(child);
        }
        return count;
    }

    public int getTreeHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(StationNode node) {
        if (node == null) return -1;
        if (node.isLeaf()) return 0;

        int maxChildHeight = -1;
        for (StationNode child : node.getChildren()) {
            int childHeight = calculateHeight(child);
            if (childHeight > maxChildHeight) {
                maxChildHeight = childHeight;
            }
        }
        return maxChildHeight + 1;
    }

    public void printStats() {
        System.out.println("\n=== Tree Statistics ===");
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.println("Root Station  : [" + root.getStationCode() + "] " + root.getStationName());
        System.out.println("Total Stations: " + getTotalStations());
        System.out.println("Tree Height   : " + getTreeHeight());
    }

    public void printTree() {
        System.out.println("\n=== Tree Visualization ===");
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        printTreeRecursive(root, "", true);
    }

    private void printTreeRecursive(StationNode node, String prefix, boolean isLast) {
        String connector = isLast ? "└── " : "├── ";
        String label = (prefix.isEmpty()) ? "" : connector;
        System.out.println(prefix + label + "[" + node.getStationCode() + "] " + node.getStationName());

        List<StationNode> children = node.getChildren();
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        for (int i = 0; i < children.size(); i++) {
            printTreeRecursive(children.get(i), childPrefix, i == children.size() - 1);
        }
    }

    public static void main(String[] args) {
        StationHierarchy hierarchy = new StationHierarchy();

        //
        //              GMRI (Gambir)
        //             /      |      \
        //          JKTK    JKTB    PSRN
        //          /  \              \
        //        BKSI MDND          TNJP
        //

        StationNode gambir   = new StationNode("GMRI", "Gambir",          "Jakarta Pusat");
        StationNode jakartaK = new StationNode("JKTK", "Jakarta Kota",    "Jakarta Utara");
        StationNode jakartaB = new StationNode("JKTB", "Jakarta Barat",   "Jakarta Barat");
        StationNode pasar    = new StationNode("PSRN", "Pasar Senen",     "Jakarta Pusat");
        StationNode bekasi   = new StationNode("BKSI", "Bekasi",          "Bekasi");
        StationNode mangga   = new StationNode("MDND", "Mangga Dua",      "Jakarta Utara");
        StationNode tanjung  = new StationNode("TNJP", "Tanjung Priok",   "Jakarta Utara");

        hierarchy.setRoot(gambir);

        hierarchy.addStation("GMRI", jakartaK);
        hierarchy.addStation("GMRI", jakartaB);
        hierarchy.addStation("GMRI", pasar);

        hierarchy.addStation("JKTK", bekasi);
        hierarchy.addStation("JKTK", mangga);
        hierarchy.addStation("PSRN", tanjung);

        hierarchy.printTree();

        hierarchy.preOrderTraversal();
        hierarchy.postOrderTraversal();
        hierarchy.levelOrderTraversal();

        hierarchy.printStats();

        System.out.println("\n=== Find Station ===");
        StationNode found = hierarchy.findStation("JKTK");
        System.out.println(found != null ? "Found: " + found : "Not found.");

        StationNode notFound = hierarchy.findStation("XYZ");
        System.out.println(notFound != null ? "Found: " + notFound : "Not found: XYZ");

        System.out.println("\n=== Remove Station [JKTK] ===");
        hierarchy.removeStation("JKTK");

        // Verify children (BKSI, MDND) were reassigned to GMRI
        System.out.println("Children of GMRI after removal:");
        for (StationNode c : hierarchy.getRoot().getChildren()) {
            System.out.println("  " + c);
        }

        // Visual tree & stats after removal
        hierarchy.printTree();
        hierarchy.printStats();
    }
}