package modul_4.Demo.Task1;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Manages the General Tree structure of railway station hierarchy.
 */
public class StationHierarchy {

    private StationNode root;

    // ─── Set Root ──────────────────────────────────────────────────────────────

    /**
     * Define the main/central station of the network.
     */
    public void setRoot(StationNode station) {
        this.root = station;
        System.out.println("[Info] Root station set: " + station.getStationCode()
                + " - " + station.getStationName());
    }

    public StationNode getRoot() {
        return root;
    }

    // ─── Add Station ───────────────────────────────────────────────────────────

    /**
     * Add a new station under a specific parent, identified by parentCode.
     * Returns true if the parent was found and the child added successfully.
     */
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

    // ─── Find Station ──────────────────────────────────────────────────────────

    /**
     * Public wrapper to search a station by its station code.
     */
    public StationNode findStation(String code) {
        return findStationRecursive(root, code);
    }

    /**
     * Recursive DFS search — looks through subtree rooted at 'current'.
     */
    private StationNode findStationRecursive(StationNode current, String code) {
        if (current == null) return null;
        if (current.getStationCode().equals(code)) return current;

        for (StationNode child : current.getChildren()) {
            StationNode result = findStationRecursive(child, code);
            if (result != null) return result;
        }
        return null;
    }

    // ─── Remove Station ────────────────────────────────────────────────────────

    /**
     * Remove a station by its code.
     *
     * Strategy for children:
     *   - If the removed station has children, they are REASSIGNED to the grandparent
     *     (the removed station's parent). This avoids data loss.
     *   - If the removed station IS the root, the tree is cleared entirely.
     *
     * Returns true if the station was found and removed.
     */
    public boolean removeStation(String code) {
        if (root == null) {
            System.out.println("[Error] Tree is empty.");
            return false;
        }

        // Special case: removing root
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

        // Reassign target's children to grandparent
        for (StationNode child : target.getChildren()) {
            child.setParent(parent);
            parent.getChildren().add(child);
        }

        // Remove target from parent's children list
        parent.removeChild(target);

        System.out.println("[Info] Station [" + code + "] removed. "
                + target.getChildren().size() + " child(ren) reassigned to ["
                + parent.getStationCode() + "]");
        return true;
    }

    // ─── Traversals ────────────────────────────────────────────────────────────

    /**
     * Pre-Order: Root → Children (depth-first, useful for copying structure).
     */
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

    /**
     * Post-Order: Children → Root (depth-first, useful for deletion logic).
     */
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

    /**
     * Level-Order (BFS): Traverse level by level.
     */
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

    // ─── Tree Statistics ───────────────────────────────────────────────────────

    /**
     * Returns the total number of stations in the tree.
     */
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

    /**
     * Returns the height of the tree.
     * Height = number of edges on the longest root-to-leaf path.
     * A single node (root only) has height 0.
     */
    public int getTreeHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(StationNode node) {
        if (node == null) return -1;       // empty tree
        if (node.isLeaf()) return 0;       // leaf node

        int maxChildHeight = -1;
        for (StationNode child : node.getChildren()) {
            int childHeight = calculateHeight(child);
            if (childHeight > maxChildHeight) {
                maxChildHeight = childHeight;
            }
        }
        return maxChildHeight + 1;
    }

    /**
     * Print a summary of tree statistics.
     */
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

    // ─── Visual Tree Print ─────────────────────────────────────────────────────

    /**
     * Print the tree in a visual diagram style, e.g.:
     *
     *  [GMRI] Gambir
     *  ├── [JKTK] Jakarta Kota
     *  │   ├── [BKSI] Bekasi
     *  │   └── [MDND] Mangga Dua
     *  ├── [JKTB] Jakarta Barat
     *  └── [PSRN] Pasar Senen
     *      └── [TNJP] Tanjung Priok
     */
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

    // ─── Main (Demo) ───────────────────────────────────────────────────────────

    public static void main(String[] args) {
        StationHierarchy hierarchy = new StationHierarchy();

        // ── Build the tree ──────────────────────────────────────────────────────
        //
        //              GMRI (Gambir)
        //             /      |      \
        //          JKTK    JKTB    PSRN
        //          /  \              \
        //        BKSI MDND          TNJP
        //
        // ───────────────────────────────────────────────────────────────────────

        StationNode gambir   = new StationNode("GMRI", "Gambir",          "Jakarta Pusat");
        StationNode jakartaK = new StationNode("JKTK", "Jakarta Kota",    "Jakarta Utara");
        StationNode jakartaB = new StationNode("JKTB", "Jakarta Barat",   "Jakarta Barat");
        StationNode pasar    = new StationNode("PSRN", "Pasar Senen",     "Jakarta Pusat");
        StationNode bekasi   = new StationNode("BKSI", "Bekasi",          "Bekasi");
        StationNode mangga   = new StationNode("MDND", "Mangga Dua",      "Jakarta Utara");
        StationNode tanjung  = new StationNode("TNJP", "Tanjung Priok",   "Jakarta Utara");

        // Set root
        hierarchy.setRoot(gambir);

        // Add children to root
        hierarchy.addStation("GMRI", jakartaK);
        hierarchy.addStation("GMRI", jakartaB);
        hierarchy.addStation("GMRI", pasar);

        // Add grandchildren
        hierarchy.addStation("JKTK", bekasi);
        hierarchy.addStation("JKTK", mangga);
        hierarchy.addStation("PSRN", tanjung);

        // ── Visual Tree ─────────────────────────────────────────────────────────
        hierarchy.printTree();

        // ── Traversals ──────────────────────────────────────────────────────────
        hierarchy.preOrderTraversal();
        hierarchy.postOrderTraversal();
        hierarchy.levelOrderTraversal();

        // ── Stats ───────────────────────────────────────────────────────────────
        hierarchy.printStats();

        // ── Find ────────────────────────────────────────────────────────────────
        System.out.println("\n=== Find Station ===");
        StationNode found = hierarchy.findStation("JKTK");
        System.out.println(found != null ? "Found: " + found : "Not found.");

        StationNode notFound = hierarchy.findStation("XYZ");
        System.out.println(notFound != null ? "Found: " + notFound : "Not found: XYZ");

        // ── Remove ──────────────────────────────────────────────────────────────
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