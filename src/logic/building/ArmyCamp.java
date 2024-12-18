package logic.building;

import logic.building.base.Activatable;
import logic.building.base.*;
import logic.utils.GameLogic;

public class ArmyCamp extends Building implements Activatable, Demolishable, Damageable {
    private boolean active;
    public static int powerGen = 100;
    public static int maintainCost = 50;

    public String url1 = "BuildingImage/ArmyCamp.png";

    public ArmyCamp(){
        turnOn(true);
    }


    @Override
    public void turnOn(boolean status) {
        if (status){
            if (GameLogic.getInstance().inPlague()){
                return;
            }
            GameLogic.getInstance().onArmyCamp();
        }
        else {
            GameLogic.getInstance().offArmyCamp();
        }
        active = status;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Army Camp";
    }

    public boolean isActive(){
        return active;
    }
    @Override
    public String getUrl() {
        return url1;
    }
}
