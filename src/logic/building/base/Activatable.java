package logic.building.base;

public interface Activatable {
    public boolean isActive();
    public void setActive(boolean active);
    public void turnOn(boolean status);
}
