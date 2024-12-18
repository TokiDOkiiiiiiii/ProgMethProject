package event.faith;

import event.base.Event;

public class Holywarrior extends Event {
    @Override
    public void effect() {
        gameInstance.getPlayer().setPower(gameInstance.getPlayer().getPower() * 13 / 10);
    }

    @Override
    public String getResult() {
        return "Your army have been promoted to Crusader";
    }

    @Override
    public String toString() {
        return "Holy Warrior";
    }

    @Override
    public String getUrl() {
        return "EventIcon/CrusaderEventIcon.png";
    }
}
