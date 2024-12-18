package logic.building;

import logic.building.base.Building;
import logic.building.base.Damageable;
import logic.building.base.Demolishable;
import logic.utils.GameLogic;

public class Farm extends Building implements Demolishable, Damageable {
    public static int moneyGen = 100;
    public String url1 = "BuildingImage/Farm.png";

    public Farm(){
        GameLogic.getInstance().onFarm();
    }
    @Override
    public String getUrl() {
        return url1;
    }

    @Override
    public String toString() {
        return "Farm";
    }
}
