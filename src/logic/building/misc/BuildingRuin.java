package logic.building.misc;

import logic.building.base.Building;

public class BuildingRuin extends Building {

    public String url1 = "MiscImage/RuinBuilding.png";
    private int buildingId;

    public BuildingRuin(int prevBuilding){
        buildingId = prevBuilding;
    }

    public int getBuildingId() {
        return buildingId;
    }

    @Override
    public String toString() {
        String name;
        switch (buildingId) {
            case 1:
                name = "Farm";
                break;
            case 2:
                name = "Army Camp";
                break;
            case 3:
                name = "Temple";
                break;
            case 4:
                name = "Camping Site";
                break;
            case 5:
                name = "Army School";
                break;
            default:
                name = "Farm School";
        }
        return name + " (Ruin)";
    }

    @Override
    public String getUrl() {
        return url1;
    }
}
