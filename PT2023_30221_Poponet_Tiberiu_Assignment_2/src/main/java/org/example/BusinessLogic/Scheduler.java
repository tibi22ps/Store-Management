package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private List<Thread> threads;
    private  int maxNoServers;
    //private int maxTasksPerServer=1000;
    private Strategy strategy;

    public Scheduler(int maxNoServers){
        //for maxNoServers
        this.threads=new ArrayList<>();
        servers=new ArrayList<>();
        for(int i=0;i<maxNoServers;i++){
            Server sNew= new Server();
            servers.add(sNew); //adaugam o casa de marcat

        }

    }

    public void changeStrategy(SelectionPolicy policy){
        //apply strategy patter to instangtiate the strategy with the concrete
        //strategy corresponding to policy
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteSTrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteSTrategyTime();
        }
    }

    public void dispatchTask(Task t){
        //call the strategy addTask method
        if(this.strategy.equals(SelectionPolicy.SHORTEST_QUEUE)){
            ConcreteSTrategyQueue q=new ConcreteSTrategyQueue();
            q.addTask(this.servers,t);
        }
        else{
            //this.strategy.equals(SelectionPolicy.SHORTEST_TIME)){
            ConcreteSTrategyTime qt=new ConcreteSTrategyTime();
            qt.addTask(this.servers,t);
        }
    }

    public List<Server> getServers(){
        return servers;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
