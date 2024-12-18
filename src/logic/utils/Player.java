package logic.utils;

import UI.alert.GameEndAlert;

public class Player {
    private int money;
    private int power;
    private int faithPercentage;
    private int maxBuilder;
    private int availableBuilder;

    public Player(){
        setMoney(1000);
        setPower(0);
        setFaithPercentage(0);
        setMaxBuilder(1);
        setAvailableBuilder(1);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int amount){
         setMoney(getMoney() + amount);
    }

    public void subMoney(int amount){
        if (getMoney() < amount){
            GameEndAlert.display("The Failure", "Your economy have been collapse!");
            return;
        }
        else {
            setMoney(getMoney() - amount);
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void addPower(int amount){
        setPower(getPower() + amount);
    }

    public int getFaithPercentage() {
        return faithPercentage;
    }

    public void setFaithPercentage(int faithPercentage) {
        if (faithPercentage >= 100){
            this.faithPercentage = 100;
        }
        else if (faithPercentage <= 0){
            this.faithPercentage = 0;
        }
        else {
            this.faithPercentage = faithPercentage;
        }
    }

    public void gainFaith(int amount){
        setFaithPercentage(getFaithPercentage() + amount);
    }

    public void faithPercentageDecay(){
        setFaithPercentage(getFaithPercentage() - 2);
    }

    public int getAvailableBuilder() {
        return availableBuilder;
    }

    public void setAvailableBuilder(int availableBuilder) {
        this.availableBuilder = availableBuilder;
    }

    public int getMaxBuilder() {
        return maxBuilder;
    }

    public void setMaxBuilder(int maxBuilder){
        this.maxBuilder = maxBuilder;
    }
    public void upgradeTownHall(){
        setMaxBuilder(getMaxBuilder() + 1);
    }

    public void useBuilder(){
        setAvailableBuilder(getAvailableBuilder() - 1);
    }
}
