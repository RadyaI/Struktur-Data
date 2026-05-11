package modul_4.Demo.Task1;

import java.util.ArrayList;
import java.util.List;

public class StationNode {

    private String stationCode;
    private String stationName;
    private String region;
    private List<StationNode> children;
    private StationNode parent;

    public StationNode(String stationCode, String stationName, String region) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.region      = region;
        this.children    = new ArrayList<>();
        this.parent      = null;
    }

    public void addChild(StationNode child) {
        if (child == null) {
            System.out.println("[Warning] Cannot add null child to " + stationCode);
            return;
        }
        child.parent = this;
        this.children.add(child);
    }

    public boolean removeChild(StationNode child) {
        if (child == null) return false;
        boolean removed = this.children.remove(child);
        if (removed) {
            child.parent = null;
        }
        return removed;
    }

    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    public String getStationCode() { return stationCode; }
    public String getStationName() { return stationName; }
    public String getRegion()      { return region; }
    public List<StationNode> getChildren() { return children; }
    public StationNode getParent() { return parent; }

    public void setStationCode(String stationCode) { this.stationCode = stationCode; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public void setRegion(String region)           { this.region = region; }
    public void setParent(StationNode parent)      { this.parent = parent; }

    @Override
    public String toString() {
        String parentCode = (parent != null) ? parent.getStationCode() : "none (root)";
        return String.format("Station[code=%s, name=%s, region=%s, parent=%s, children=%d, isLeaf=%b]",
                stationCode, stationName, region, parentCode, children.size(), isLeaf());
    }
}