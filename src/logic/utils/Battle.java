package logic.utils;

import UI.alert.GameEndAlert;

import java.util.Random;

public class Battle {
    private GameLogic gameInstance;
    private int raidPower;
    private boolean win;
    private Random random;
    private int playerPower;
    public Battle(int raidPower){
        gameInstance = GameLogic.getInstance();
        playerPower = gameInstance.getPlayer().getPower();
        random = new Random();
        setRaidPower(raidPower);
        win = playerPower >= getRaidPower();
    }

    public void punish(){
        if (win){
            int n = random.nextInt(1,5);

            gameInstance.getPlayer().setPower(gameInstance.getPlayer().getPower() - (raidPower / n));
        }
        else{
            int losingChance = random.nextInt(100);
            int survive = playerPower * 100 / Math.max(getRaidPower(), 1);
            if (losingChance > survive){
                GameLogic.getInstance().gameEnd();
                return ;
            }
            int n = random.nextInt(8,10);
            gameInstance.getPlayer().setPower(gameInstance.getPlayer().getPower() / n);
            gameInstance.getPlayer().setMoney(gameInstance.getPlayer().getMoney() * 4 / 5);
            gameInstance.destroyBuilding(1);
        }
    }

    public boolean isWinning(){
        return win;
    }

    public void setRaidPower(int raidPower){
        this.raidPower = raidPower;
    }

    public int getRaidPower() {
        return raidPower;
    }
}
