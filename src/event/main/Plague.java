package event.main;

import event.base.Event;

public class Plague extends Event {
    private int n;
    @Override
    public void effect() {
        n = random.nextInt(2,5);
        gameInstance.effectPlague(n);
    }

    @Override
    public String getResult() {
        return "All worker should stop their work for " + n + " days";
    }

    @Override
    public String toString() {
        return "Plague is spreading";
    }

    @Override
    public String getUrl() {
        return "EventIcon/PlagueEventIcon.png";
    }
}
