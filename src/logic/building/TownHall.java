package logic.building;

import logic.building.base.Building;
import logic.utils.GameLogic;

public class TownHall extends Building {
    private int level;

    public String url1 = "TownHallImage/TownHall1.png";
    public String url2 = "TownHallImage/TownHall2.png";
    public String url3 = "TownHallImage/TownHall3.png";
    private boolean upgrading;

    public TownHall(){
        level = 1;
    }

    public void upgradeLevel(){
        level += 1;
        GameLogic.getInstance().getPlayer().upgradeTownHall();
        upgrading = false;
    }

    public String buyUpgrade(){
        upgrading = true;
        if (level == 1){
            return "MiscImage/TownHallConstruction1.png";
        }
        else {
            return "MiscImage/TownHallConstruction2.png";
        }
    }

    public boolean isUpgrading(){
        return upgrading;
    }
    public int getLevel(){
        return level;
    }

    @Override
    public String getUrl() {
        if (level == 1){
            return url1;
        }
        else if (level == 2){
            return url2;
        }
        else{
            return url3;
        }
    }

    @Override
    public String toString() {
        return "Town Hall : " + getLevel();
    }
}
