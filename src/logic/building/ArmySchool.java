package logic.building;

import logic.building.base.Activatable;
import logic.building.base.Demolishable;
import logic.building.base.School;
import logic.utils.GameLogic;

public class ArmySchool extends School implements Activatable, Demolishable {
    private boolean active;

    public String url1 = "BuildingImage/ArmySchool.png";
    public ArmySchool(){
        turnOn(true);
    }
    @Override
    public void turnOn(boolean status) {

        if (status){
            if (GameLogic.getInstance().inPlague()){
                return;
            }
            GameLogic.getInstance().onArmySchool();
        }
        else {
            GameLogic.getInstance().offArmySchool();
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
    public String toString() {
        return "Army School";
    }

    @Override
    public String getUrl() {
        return url1;
    }
}
