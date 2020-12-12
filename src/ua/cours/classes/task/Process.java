package ua.cours.classes.task;

import ua.cours.classes.Configuration;
import ua.cours.classes.utils.Utils;
import ua.cours.classes.utils.Timer;

import java.util.Comparator;

public class Process {
    private final int id;        //after create
    private final String name;    //rand
    private int priority;
    private Status status;    //rand + on work
    private  int tickWorks;       //rand
    private final int memory;     //rand
    private final int timeIn;     //after create
    private int bursTime;   //on work


    public Process(int id)
    {
        this.id = id;
        this.name = +this.id + Utils.getRandString(10) + ".exe";
        this.priority = Utils.getRandInt(Configuration.maxPriority - 1) + 1;
        this.memory = Utils.getRandInt(Configuration.MIN_PROCESS_MEMORY_REQUIRED, Configuration.MAX_PROCESS_MEMORY_REQUIRED);
        this.tickWorks = Utils.getRandInt(Configuration.MIN_PROCESS_TIME_REQUIRED, Configuration.MAX_PROCESS_TIME_REQUIRED);
        this.timeIn = Timer.getTick();
        this.bursTime=0;
        this.status = Status.Ready;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBursTime(int bursTime) {
        this.bursTime = bursTime;
    }

    public int getId()
    {
        return id;
    }

    public int getPriority()
    {
        return priority;
    }


    public String getName()
    {
        return name;
    }

    public int getTickWorks()
    {
        return tickWorks;
    }

    public int getMemory()
    {
        return memory;
    }

    public int getTimeIn()
    {
        return timeIn;
    }

    public int getBursTime()
    {
        return bursTime;
    }

    public Status getStatus()
    {
        return status;
    }

    public static Comparator<Process> byTime = Comparator.comparingInt(o -> o.tickWorks);

}
