package event.main;

import event.base.Event;

public class Burglar extends Event {
    private int stolenMoney;

    @Override
    public void effect() {
        stolenMoney = random.nextInt(gameInstance.getPlayer().getMoney() / 2);
        gameInstance.getPlayer().subMoney(stolenMoney);
    }

    @Override
    public String getResult() {
        return "You lost " + stolenMoney;
    }

    @Override
    public String toString() {
        return "Burglary";
    }

    @Override
    public String getUrl() {
        return "EventIcon/BurglarEventIcon.png";
    }
}
