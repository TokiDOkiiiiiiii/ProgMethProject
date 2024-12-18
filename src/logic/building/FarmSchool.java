package logic.building;

import logic.building.base.Activatable;
import logic.building.base.Demolishable;
import logic.building.base.School;
import logic.utils.GameLogic;

public class FarmSchool extends School implements Activatable, Demolishable {
    private boolean active;
    public String url1 = "BuildingImage/FarmSchool.png";

    public FarmSchool(){
        turnOn(true);
    }
    @Override
    public void turnOn(boolean status) {
        if (status){
            if (GameLogic.getInstance().inPlague()){
                return;
            }
            GameLogic.getInstance().onFarmSchool();
        }
        else {
            GameLogic.getInstance().offFarmSchool();
        }
        active = status;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive(){
        return active;
    }

    @Override
    public String getUrl() {
        return url1;
    }

    @Override
    public String toString() {
        return "Farm School";
    }
}
