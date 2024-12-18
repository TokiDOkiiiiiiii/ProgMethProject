package event.main;

import event.base.Event;
import logic.utils.Battle;

public class Raid extends Event {
    private int raidPower;
    private boolean result;

    public Raid(int power){
        raidPower = power;
    }
    @Override
    public void effect() {
        Battle current = new Battle(raidPower);
        result = current.isWinning();
        current.punish();
    }
    @Override
    public String getUrl() {
        return "EventIcon/RaidEventIcon.png";
    }

    @Override
    public String toString() {
        return "Raid";
    }

    public String getResult(){
        if (result){
            return "You have been raid with " + raidPower + " power\nYou won, some power have been used";
        }
        else {
            return "You have been raid with " + raidPower + " power\nYou lose, some money have been taken and a building is damaged";
        }
    }
}
