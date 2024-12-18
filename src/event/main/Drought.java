package event.main;

import event.base.Event;

public class Drought extends Event {
    private int n;
    @Override
    public void effect() {
        n = random.nextInt(2,5);
        gameInstance.effectDrought(n);
    }

    @Override
    public String getResult() {
        return "All of your farm cannot produce for " + n + "days";
    }

    @Override
    public String toString() {
        return "Drought season";
    }

    @Override
    public String getUrl() {
        return "EventIcon/DroughtEventIcon.png";
    }
}
