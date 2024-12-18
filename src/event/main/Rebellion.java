package event.main;

import event.base.Event;
import logic.utils.Battle;

public class Rebellion extends Event {
    @Override
    public void effect() {
        int fraction = random.nextInt(4,10);
        int total = gameInstance.getPlayer().getPower();
        int converted = total / fraction;
        int left = total - converted;
        gameInstance.getPlayer().setPower(left);
        Battle rebel = new Battle(converted);
        rebel.punish();
    }

    @Override
    public String getResult() {
        return "Your army divided into two group and attacking each other";
    }

    @Override
    public String toString() {
        return "Rebellion";
    }

    @Override
    public String getUrl() {
        return "EventIcon/RebellionEventIcon.png";
    }
}
