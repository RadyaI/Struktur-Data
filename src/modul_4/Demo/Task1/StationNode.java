package modul_4.Demo.Task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single station node in the general tree hierarchy.
 */
public class StationNode {

    private String stationCode;
    private String stationName;
    private String region;
    private List<StationNode> children;
    private StationNode parent;

    // ─── Constructor ───────────────────────────────────────────────────────────

    public StationNode(String stationCode, String stationName, String region) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.region      = region;
        this.children    = new ArrayList<>();
        this.parent      = null;
    }

    // ─── Core Methods ──────────────────────────────────────────────────────────

    /**
     * Add a sub-station (child) under this station.
     */
    public void addChild(StationNode child) {
        if (child == null) {
            System.out.println("[Warning] Cannot add null child to " + stationCode);
            return;
        }
        child.parent = this;
        this.children.add(child);
    }

    /**
     * Remove a direct sub-station from this station.
     * Returns true if the child was found and removed, false otherwise.
     */
    public boolean removeChild(StationNode child) {
        if (child == null) return false;
        boolean removed = this.children.remove(child);
        if (removed) {
            child.parent = null;
        }
        return removed;
    }

    /**
     * Returns true if this station has no sub-stations.
     */
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    // ─── Getters & Setters ─────────────────────────────────────────────────────

    public String getStationCode() { return stationCode; }
    public String getStationName() { return stationName; }
    public String getRegion()      { return region; }
    public List<StationNode> getChildren() { return children; }
    public StationNode getParent() { return parent; }

    public void setStationCode(String stationCode) { this.stationCode = stationCode; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public void setRegion(String region)           { this.region = region; }
    public void setParent(StationNode parent)      { this.parent = parent; }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        String parentCode = (parent != null) ? parent.getStationCode() : "none (root)";
        return String.format("Station[code=%s, name=%s, region=%s, parent=%s, children=%d, isLeaf=%b]",
                stationCode, stationName, region, parentCode, children.size(), isLeaf());
    }
}