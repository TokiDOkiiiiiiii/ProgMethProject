package event.base;

import logic.utils.GameLogic;

import java.util.Random;

public abstract class Event {
    protected GameLogic gameInstance;
    protected Random random;

    public Event(){
        gameInstance = GameLogic.getInstance();
        random = new Random();
    }
    public abstract void effect();
    public abstract String getResult();
    public abstract String toString();
    public abstract String getUrl();
}
