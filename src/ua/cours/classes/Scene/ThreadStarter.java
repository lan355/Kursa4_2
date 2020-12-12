package ua.cours.classes.Scene;

import ua.cours.classes.task.GlobalScheduler;

public class ThreadStarter implements Runnable {
    public static GlobalScheduler globalScheduler;

    @Override
    public void run()
    {
        globalScheduler = new GlobalScheduler();
        globalScheduler.Start();

    }

}
