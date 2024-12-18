package event.faith;

import event.base.Event;

public class Philanthropy extends Event {

    @Override
    public void effect() {
        gameInstance.getPlayer().addMoney(2000);
    }

    @Override
    public String getResult() {
        return "There is an rich disciple donated 2000 gold to support your religion";
    }

    @Override
    public String toString() {
        return "Philanthropy disciple";
    }

    @Override
    public String getUrl() {
        return "EventIcon/PhilantrophyEventIcon.png";
    }
}
