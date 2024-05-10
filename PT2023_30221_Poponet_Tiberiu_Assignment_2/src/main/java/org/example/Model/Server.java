package org.example.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server  implements Runnable, Comparable<Server> {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int id;
    public Object obj = new Object();

    private static int i;

    public Server() {
        this.tasks = new ArrayBlockingQueue<>(1000);
        this.waitingPeriod = new AtomicInteger(0); //timp de asteptare la casa de marcat
        i++;
        this.id = i;
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        this.waitingPeriod = new AtomicInteger(this.waitingPeriod.get() + newTask.getRemainedTime());

    }

    public void run() {
        while (true) {

            synchronized (obj) { //semafor
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (this.tasks.size()!=0) {
                Task t1 = this.tasks.peek();
                if (t1.getRemainedTime() == 1) {
                    this.tasks.remove(t1);
                } else {
                    t1.setRemainedTime(t1.getRemainedTime() - 1);
                    this.waitingPeriod = new AtomicInteger(this.waitingPeriod.get() - 1);
                }

            }

        }
    }

    public Task[] getTasks() {

            Task[] aux=new Task[1000];
            int contor = 0;
            for(Task i:this.tasks){
                aux[contor]=i;
                contor++;
            }
            return aux;
        }

        public BlockingQueue<Task > getBlockTasks() {
        return this.tasks;
        }


    @Override
    public int compareTo(Server otherServer) {
        return Integer.compare(this.waitingPeriod.get(), otherServer.waitingPeriod.get());
    }

    public static void orderByWaitingPeriod(List<Server> servers) {
        Collections.sort(servers);
    }

    public Object getObj() {
        return obj;
    }

    public int getId() {
        return id;
    }
}
