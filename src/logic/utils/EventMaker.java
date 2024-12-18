package logic.utils;

import event.main.Raid;
import event.faith.Holywarrior;
import event.faith.Philanthropy;
import event.base.Event;
import event.main.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EventMaker {
    private ArrayList<Event> mainEvents;
    private ArrayList<Event> faithEvents;
    private ArrayList<Event> raidEvents;
    private int seqCounter;
    private int raidCounter;
    private int faithCounter;
    private Random random;
    public EventMaker(){
        raidCounter = 0;
        faithCounter = 0;
        seqCounter = 0;
        initiateFaithEvents();
        initiateMainEvents();
        initiateRaidEvents();
        random = new Random();
    }


    //Passing event on chance
    public Event getMainEvent(int day){
        int n = Math.min(day, 15);
        //Test
        //End
        if (n > random.nextInt(100)){
            int jump = random.nextInt(1,2);
            seqCounter = (seqCounter + jump) % 6;
            Event main = mainEvents.get(seqCounter);
            main.effect();
            return main;
        }
        return null;
    }

    //Passing event on chance
    public Event getFaithEvent(){
        int n = GameLogic.getInstance().getPlayer().getFaithPercentage() / 2;
        if (n > random.nextInt(100)){
            Event faith = faithEvents.get(faithCounter % 2);
            faith.effect();
            faithCounter++;
            return faith;
        }
        return null;
    }

    //Passing event every time called
    public Event getRaidEvent(){
        Event raid = raidEvents.get(raidCounter);
        raid.effect();
        raidCounter++;
        return raid;
    }


    private void initiateMainEvents(){
        mainEvents = new ArrayList<>();
        mainEvents.add(new Burglar());
        mainEvents.add(new Drought());
        mainEvents.add(new Earthquake());
        mainEvents.add(new Mercenary());
        mainEvents.add(new Plague());
        mainEvents.add(new Rebellion());
        Collections.shuffle(mainEvents);
    }

    private void initiateRaidEvents(){
        raidEvents = new ArrayList<>();
        raidEvents.add(new Raid(300));
        raidEvents.add(new Raid(500));
        raidEvents.add(new Raid(1000));
        raidEvents.add(new Raid(2000));
        raidEvents.add(new Raid(5000));
        raidEvents.add(new Raid(10000));
        raidEvents.add(new Raid(15000));
        raidEvents.add(new Raid(30000));
        raidEvents.add(new Raid(50000));
        raidEvents.add(new Raid(100000));
    }

    private void initiateFaithEvents(){
        faithEvents = new ArrayList<>();
        faithEvents.add(new Holywarrior());
        faithEvents.add(new Philanthropy());
    }
}
