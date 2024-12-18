package logic.building;

import logic.building.base.Activatable;
import logic.building.base.Building;
import logic.building.base.Demolishable;
import logic.utils.GameLogic;

public class Temple extends Building implements Activatable, Demolishable {
    private boolean active;
    public static int faithGen = 1;
    public static int maintainCost = 50;
    public String url1 = "BuildingImage/Temple.png";

    public Temple(){
        turnOn(true);
    }
    @Override
    public void turnOn(boolean status) {
        if (status){
            if (GameLogic.getInstance().inPlague()){
                //InPlague.display();
                //UserInterface.plagueWindow();
                return;
            }
            GameLogic.getInstance().onTemple();
        }
        else {
            GameLogic.getInstance().offTemple();
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
        return "Temple";
    }
}
