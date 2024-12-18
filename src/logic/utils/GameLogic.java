package logic.utils;

import UI.alert.*;
import UI.pane.IngameStage;
import logic.building.*;
import logic.building.base.Activatable;
import logic.building.base.Building;
import logic.building.base.Damageable;
import logic.building.misc.BuildingRuin;
import logic.building.misc.ConstructionSite;
import logic.building.misc.Demolishing;
import logic.building.misc.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameLogic {
    private int plagueCount;
    private int droughtCount;
    private boolean gameOver;
    private TownHall townHall;
    private int income;
    private int powerGrowth;
    private int incomeMultiplier;
    private int powerMultiplier;
    private int expense;
    private int faithGrowth;
    private Player player;
    private ArrayList<ArrayList<Tile>> map;
    private Map<Integer , Integer> buildingCost = new HashMap<>();
    private static GameLogic instance = null;

    private GameLogic(){
        plagueCount = 0;
        droughtCount = 0;
        gameOver = false;
        initBuildingCost();
        setMap();
        player = new Player();
        clearValue();
    }

    //Game Instance
    public static GameLogic getInstance() {
        if (instance == null){
            instance = new GameLogic();
        }
        return instance;
    }

    public static void newInstance(){
        instance = new GameLogic();
    }

    public static void killInstance(){
        instance = null;
    }

    private void setMap(){
        map = new ArrayList<ArrayList<Tile>>();
        ArrayList<Tile> first = new ArrayList<Tile>();
        ArrayList<Tile> second = new ArrayList<Tile>();
        ArrayList<Tile> third = new ArrayList<Tile>();
        ArrayList<Tile> forth = new ArrayList<Tile>();
        ArrayList<Tile> fifth = new ArrayList<Tile>();

        for(int i = 0; i < 5; i++){
            first.add(new Tile());
            fifth.add(new Tile());
            IngameStage.update(0,i, "MiscImage/Forest.png");
            IngameStage.update(4,i, "MiscImage/Forest.png");
            IngameStage.update(i,0, "MiscImage/Forest.png");
            IngameStage.update(i,4, "MiscImage/Forest.png");
        }

        forth.add(new Tile());
        second.add(new Tile());
        for(int i = 0; i < 3; i++){
            second.add(new Tile(true));
            forth.add(new Tile(true));
        }
        second.add(new Tile());
        forth.add(new Tile());

        third.add(new Tile());
        third.add(new Tile(true));
        townHall = new TownHall();
        third.add(new Tile(true, townHall));
        IngameStage.update(2, 2, "TownHallImage/TownHall1.png");

        third.add(new Tile(true));
        third.add(new Tile());

        map.add(first);
        map.add(second);
        map.add(third);
        map.add(forth);
        map.add(fifth);
    }


    public void dailyUpdate(){
        player.setAvailableBuilder(player.getMaxBuilder());
        if (!inDrought()){
            IngameStage.setDroughtStatus(false);
            player.addMoney(getIncome() * getIncomeMultiplier());
        }
        if (!inPlague()){
            IngameStage.setPlagueStatus(false);
        }
        player.addPower(getPowerGrowth() * getPowerMultiplier());
        player.subMoney(getExpense());
        if (getFaithGrowth() == 0){
            player.faithPercentageDecay();
        }
        else {
            player.gainFaith(getFaithGrowth());
        }
        clearValue();

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                Tile tmp = map.get(i).get(j);
                if (inPlague() && tmp.getBuilding() instanceof Activatable && ((Activatable) tmp.getBuilding()).isActive()){
                    ((Activatable) tmp.getBuilding()).setActive(false);
                }

                else if (tmp.getBuilding() instanceof ArmyCamp && ((ArmyCamp) tmp.getBuilding()).isActive()){
                    onArmyCamp();
                }

                else if (tmp.getBuilding() instanceof ArmySchool && ((ArmySchool) tmp.getBuilding()).isActive()){
                    onArmySchool();
                }

                else if (tmp.getBuilding() instanceof CampingSite ){
                    CampingSite cs = (CampingSite) tmp.getBuilding();
                    if (cs.countDown()){
                        GameEndAlert.display("The Explorer", "Your scout lead you to winning");
                        return ;
                    }
                    if (cs.isActive()){
                        onCampingSite();
                        cs.accumulate();
                    }
                    else{
                        cs.reset();
                    }

                }

                else if (tmp.getBuilding() instanceof Farm){
                    if (!inDrought() && !inPlague()) {
                        onFarm();
                    }
                }

                else if(tmp.getBuilding() instanceof FarmSchool && ((FarmSchool) tmp.getBuilding()).isActive()){
                    onFarmSchool();
                }

                else if(tmp.getBuilding() instanceof Temple && ((Temple) tmp.getBuilding()).isActive()){
                    onTemple();
                }

                else if (tmp.getBuilding() instanceof ConstructionSite){
                    Building b = ((ConstructionSite) tmp.getBuilding()).construct();
                    tmp.setBuilding(b);
                    IngameStage.update(i,j,b.getUrl());
                    if (tmp.getBuilding() instanceof Farm && (inPlague() || inDrought())){
                        offFarm();
                    }
                    else if (tmp.getBuilding() instanceof Activatable && inPlague()){
                        ((Activatable) tmp.getBuilding()).setActive(false);
                    }
                }
                else if (tmp.isClearingArea()){
                    tmp.setClearingArea(false);
                    tmp.setOwnByPlayer(true);
                    IngameStage.update(i,j,null);
                }
                else if (tmp.getBuilding() instanceof Demolishing){
                    tmp.removeBuilding();
                    IngameStage.update(i,j,null);
                }
                else if (tmp.getBuilding() instanceof  TownHall){
                    if (((TownHall) tmp.getBuilding()).isUpgrading()){
                        ((TownHall) tmp.getBuilding()).upgradeLevel();
                        IngameStage.update(2,2,tmp.getBuilding().getUrl());
                        player.setAvailableBuilder(player.getMaxBuilder());
                        if (getTownHall().getLevel() == 2){
                            IngameStage.update(i,j,((TownHall) tmp.getBuilding()).getUrl());
                        }
                        else {
                            IngameStage.update(i,j, ((TownHall) tmp.getBuilding()).getUrl());
                        }
                    }
                }

            }
            IngameStage.updateInfo();
        }
        if (inPlague()){
            plagueCount--;
        }
        if (inDrought()){
            droughtCount--;
        }

    }

    public static void selectTile(int i, int j){
        Tile tile = instance.getMap().get(i).get(j);
        if (!tile.isOwnByPlayer()){
            BuyTile.display(tile, i, j);
        }
        else if (tile.isOccupied()){
            if (tile.getBuilding() instanceof TownHall){
                if (((TownHall) tile.getBuilding()).getLevel() < 3){
                    TownHallUpgrade.display();
                }
            }
            else if (!(tile.getBuilding() instanceof ConstructionSite)){
                BuildingInteraction.display(tile, i, j);
            }
        }

        else if (tile.isOwnByPlayer()){
            BuyMenu.display(i, j);
        }
    }

    public void activateBuilding(Tile tile){
        Building building = tile.getBuilding();
        boolean active = ((Activatable) building).isActive();
        ((Activatable) building).turnOn(!active);
    }

    public void onCampingSite(){
        setExpense(getExpense() + CampingSite.maintainCost);
    }
    public void offCampingSite(){
        setExpense(getExpense() - CampingSite.maintainCost);
    }
    public void onArmyCamp(){
        setPowerGrowth(getPowerGrowth() + ArmyCamp.powerGen);
        setExpense(getExpense() + ArmyCamp.maintainCost);
    }

    public void offArmyCamp(){
        setPowerGrowth(getPowerGrowth() - ArmyCamp.powerGen);
        setExpense(getExpense() - ArmyCamp.maintainCost);
    }

    public void onArmySchool(){
        setPowerMultiplier(getPowerMultiplier() + ArmySchool.genMul);
        setExpense(getExpense() + ArmySchool.maintainCost);
    }

    public void offArmySchool(){
        setPowerMultiplier(getPowerMultiplier() - ArmySchool.genMul);
        setExpense(getExpense() - ArmySchool.maintainCost);
    }

    public void onFarm(){
        setIncome(getIncome() + Farm.moneyGen);
    }
    public void offFarm(){
        setIncome(getIncome() - Farm.moneyGen);
    }
    public void onFarmSchool(){
        setIncomeMultiplier(getIncomeMultiplier() + FarmSchool.genMul);
        setExpense(getExpense() + FarmSchool.maintainCost);
    }

    public void offFarmSchool(){
        setIncomeMultiplier(getIncomeMultiplier() - FarmSchool.genMul);
        setExpense(getExpense() - FarmSchool.maintainCost);
    }

    public void onTemple(){
        setFaithGrowth(getFaithGrowth() + Temple.faithGen);
        setExpense(getExpense() + Temple.maintainCost);
    }

    public void offTemple(){
        setFaithGrowth(getFaithGrowth() - Temple.faithGen);
        setExpense(getExpense() - Temple.maintainCost);
    }

    private void clearValue(){
        setIncome(0);
        setPowerGrowth(0);
        setFaithGrowth(0);
        setIncomeMultiplier(1);
        setPowerMultiplier(1);
        setExpense(50);
    }

    private void initBuildingCost(){
        buildingCost.put(1,200);    //Farm
        buildingCost.put(2,300);    //Army Camp
        buildingCost.put(3,500);    //Temple
        buildingCost.put(4,500);    //Camping Site
        buildingCost.put(5,700);    //Farm School
        buildingCost.put(6,700);    //Army School
        buildingCost.put(7,300);    //Clear Tile
        buildingCost.put(8,200);    //Demolish Building
    }

    public static boolean haveEnoughMoney(int price){
        return price <= instance.getPlayer().getMoney();
    }

    public static boolean haveEnoughBuilder(){
        return instance.getPlayer().getAvailableBuilder() > 0;
    }


    //Destroy Building
    public void destroyBuilding(int k){
        for (int i = 0; i < 5 ; i++){
            for (int j = 0; j < 5; j++){
                if (k == 0) return;
                Tile tile = map.get(i).get(j);
                if (tile.getBuilding() instanceof Damageable){
                    k--;
                    if (tile.getBuilding() instanceof Farm) {
                        offFarm();
                        tile.setBuilding(new BuildingRuin(1));
                    }
                    else if (tile.getBuilding() instanceof ArmyCamp) {
                        if (((ArmyCamp) tile.getBuilding()).isActive()){
                            ((ArmyCamp) tile.getBuilding()).turnOn(false);
                        }
                        tile.setBuilding(new BuildingRuin(2));
                    }
                    IngameStage.update(i,j, tile.getBuilding().getUrl());
                }
            }
        }
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void gameEnd(){
        gameOver = true;
    }

    public void effectDrought(int n){
        IngameStage.setDroughtStatus(true);
        droughtCount = Math.max(droughtCount, n);
    }

    public void effectPlague(int n){
        IngameStage.setPlagueStatus(true);
        plagueCount = Math.max(plagueCount, n);
    }

    public boolean inPlague(){
        return plagueCount != 0;
    }
    public boolean inDrought(){
        return droughtCount != 0;
    }

    // Getter Setter
    public Player getPlayer(){
        return player;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getPowerGrowth() {
        return powerGrowth;
    }

    public void setPowerGrowth(int powerGrowth) {
        this.powerGrowth = powerGrowth;
    }

    public int getIncomeMultiplier() {
        return incomeMultiplier;
    }

    public void setIncomeMultiplier(int incomeMultiplier) {
        this.incomeMultiplier = incomeMultiplier;
    }

    public int getPowerMultiplier() {
        return powerMultiplier;
    }

    public void setPowerMultiplier(int powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getFaithGrowth() {
        return faithGrowth;
    }

    public void setFaithGrowth(int faithGrowth) {
        this.faithGrowth = faithGrowth;
    }

    public ArrayList<ArrayList<Tile>> getMap() {
        return map;
    }

    public TownHall getTownHall() {
        return townHall;
    }

    public Map<Integer, Integer> getBuildingCost() {
        return buildingCost;
    }



}
