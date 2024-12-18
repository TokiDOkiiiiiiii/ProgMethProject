package logic.building;

import UI.alert.InPlague;
import logic.building.base.Activatable;
import logic.building.base.Building;
import logic.building.base.Demolishable;
import logic.utils.GameLogic;

import java.util.Random;


public class CampingSite extends Building implements Activatable, Demolishable {
    public String url1 = "BuildingImage/CampingSite.png";
    private int countDay = 0;
    private boolean active;
    private boolean success;
    private int successRate;
    public static int maintainCost = 50;
    private Random random;

    public CampingSite(){
        turnOn(true);
        success = false;
        random = new Random();
    }
    @Override
    public void turnOn(boolean status) {
        if (status){
            if (GameLogic.getInstance().inPlague()){
                return;
            }
            GameLogic.getInstance().onCampingSite();
        }
        else {
            GameLogic.getInstance().offCampingSite();
        }
        active = status;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void accumulate(){
        setSuccessRate(getSuccesRate() + 3);
    }
    public void reset(){
        setSuccessRate(0);
    }
    public void explore(){
        int chance = random.nextInt(100);
        if (getSuccesRate() > chance){
            success = true;
        }
        reset();
        countDay = 5;
    }
    public int getSuccesRate(){
        return successRate;
    }
    public void setSuccessRate(int rate){
        if (rate > 100){
            successRate = 100;
        }
        else successRate = rate;
    }
    public boolean isActive(){
        return active;
    }
    public int getCountDay(){
        return countDay;
    }
    public boolean countDown(){
        if (countDay == 0 && success){
            return true;
        }
        else if (countDay > 0){
            countDay--;
        }
        return false;
    }
    public boolean isExploring(){
        return countDay != 0;
    }

    public String toString(){
        return "Camping Site : " + getSuccesRate() + " %";
    }
    @Override
    public String getUrl() {
        return url1;
    }
}
