package logic.building.misc;

import logic.building.*;
import logic.building.base.Building;

public class ConstructionSite extends Building {
    private int targetBuildingCode;
    public String url1 = "MiscImage/ConstructionSite.png";

    public ConstructionSite(int buildingCode){
        targetBuildingCode = buildingCode;
    }

    public Building construct(){
        Building targetBuilding;

        switch (targetBuildingCode) {
            case 1:
                targetBuilding =  new Farm();
                break;
            case 2:
                targetBuilding =  new ArmyCamp();
                break;
            case 3:
                targetBuilding =  new Temple();
                break;
            case 4:
                targetBuilding =  new CampingSite();
                break;
            case 5:
                targetBuilding =  new ArmySchool();
                break;
            default:
                targetBuilding = new FarmSchool();
        }
        return targetBuilding;
    }

    @Override
    public String toString() {
        return "Construction Site";
    }

    @Override
    public String getUrl() {
        return url1;
    }
}
