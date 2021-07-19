package main.api.response;

import main.model.Region;

import java.util.List;

public class RegionResponse {

    private int count;
    private List<Region> regions;

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public List<Region> getRegions() { return regions; }

    public void setRegions(List<Region> regions) { this.regions = regions; }
}
