package event.main;


import UI.alert.MercenaryAlert;
import event.base.Event;

public class Mercenary extends Event {
    @Override
    public void effect() {
        int n = random.nextInt(1, 4);
        n = Math.max(gameInstance.getPlayer().getPower(), 100) * n / 2;
        MercenaryAlert.display(n, n);
    }

    @Override
    public String getResult() {
        return "";
    }

    @Override
    public String toString() {
        return "Mercenary";
    }

    @Override
    public String getUrl() {
        return "EventIcon/MercenaryEventIcon.png";
    }
}
