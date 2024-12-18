package logic.building.misc;

import logic.building.base.Building;

public class Tile {
    private Building building;
    private boolean ownByPlayer;
    private boolean clearingArea;
    public Tile(){
        setOwnByPlayer(false);
        setBuilding(null);
        setClearingArea(false);
    }

    public Tile(boolean ownBy){
        setOwnByPlayer(ownBy);
        setBuilding(null);
        setClearingArea(false);
    }

    public Tile(boolean ownBy, Building building){
        setOwnByPlayer(ownBy);
        setBuilding(building);
        setClearingArea(false);
    }

    public boolean isOccupied(){
        return getBuilding() != null;
    }
    public void setBuilding(Building building){
        this.building = building;
    }

    public void removeBuilding(){
        setBuilding(null);
    }

    public Building getBuilding(){
        return this.building;
    }
    public boolean isOwnByPlayer() {
        return ownByPlayer;
    }

    public void setOwnByPlayer(boolean ownByPlayer) {
        this.ownByPlayer = ownByPlayer;
    }

    public void setClearingArea(boolean clearingArea) {
        this.clearingArea = clearingArea;
    }

    public boolean isClearingArea() {
        return clearingArea;
    }
}
