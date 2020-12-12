package ua.cours.classes.task;

import ua.cours.classes.Scene.CoreInfo;
import ua.cours.classes.Configuration;
import ua.cours.classes.utils.Utils;
import ua.cours.classes.utils.TimerListener;
import ua.cours.Main;
import ua.cours.classes.utils.Timer;

import java.util.ArrayList;

public class GlobalScheduler implements TimerListener {
     static Queue queue;

    CPU cpu;
    MemoryScheduler memoryScheduler;
    Timer timer;

    public GlobalScheduler() {
        queue = new Queue();

        this.cpu = new CPU(Configuration.PC_CORE);
        this.memoryScheduler = new MemoryScheduler();
        this.timer = new Timer();

        this.timer.addListener(cpu);
        this.timer.addListener(this);
    }

    public void Start()
    {
        Init();
        this.timer.run();
    }

    private void Init()
    {
        MemoryScheduler.add(new MemoryBlock(0,250,null));
        queue.Add(Configuration.PROCESSES_ON_START);
    }

    private void addJob()
    {
        if(Utils.getRandBool()) {
            queue.Add(Utils.getRandInt(10));
        }
        updateTable();
    }


    public static void PDone(Process process)
    {
        if(Utils.getRandBool()) {
            process.setStatus(Status.Finished);
            queue.finishProcess(process);
        }
        else
        {
            process.setStatus(Status.Waiting);
            queue.addProcess(process);
        }
    }

    private void clearOutdated()
    {
        if(Timer.getTick()% Configuration.REMOVE_OLD_PROCESS_EVERY_N_TICKS == 0)
        {
            queue.cancelOutdated();
        }
    }

    private void setJobToCPU()
    {
        for (int i = 0; i< Configuration.PC_CORE; i++)
        {
            int _tmpInt = cpu.getFreeCore();
            if (_tmpInt >= 0) {
                cpu.setCoreJob(_tmpInt, queue.getHighestPriorityProcess());
            }
        }
    }

    public void updateTable()
    {
        //TODO: FIXIT!!
        Main.controller.TableRowSetter(queue,genCoreInfo());
    }

    private ArrayList<CoreInfo> genCoreInfo()
    {
        ArrayList<CoreInfo> _tmp = new ArrayList<CoreInfo>();
        for (int i =0; i<cpu.getCores().length;i++ )
            _tmp.add(new CoreInfo(String.valueOf(i),cpu.getCores()[i].getCurrentProcess()));

        return _tmp;
    }

    @Override
    public void tickEvent()
    {
        queue.getReadyQueue().sort(Process.byTime);
        clearOutdated();
        setJobToCPU();
        addJob();
    }
}
