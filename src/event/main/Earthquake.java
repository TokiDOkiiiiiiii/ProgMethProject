package event.main;

import event.base.Event;

public class Earthquake extends Event {
    @Override
    public void effect() {
        int n = random.nextInt(1,3);
        gameInstance.destroyBuilding(n);
    }

    @Override
    public String getResult() {
        return "Some of the building is destroyed due to the earthquake";
    }

    @Override
    public String toString() {
        return "Earthquake is happening";
    }

    @Override
    public String getUrl() {
        return "EventIcon/EarthquakeEventIcon.png";
    }
}
