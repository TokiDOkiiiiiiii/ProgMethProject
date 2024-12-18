package logic.building.misc;

import logic.building.base.Building;

public class Demolishing extends Building {
    public String url1 = "MiscImage/Demolishing.png";

    @Override
    public String toString() {
        return "Demolishing";
    }

    @Override
    public String getUrl() {
        return url1;
    }
}
