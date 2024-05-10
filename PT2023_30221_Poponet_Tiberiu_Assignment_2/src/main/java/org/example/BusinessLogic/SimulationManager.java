package org.example.BusinessLogic;

import org.example.GUI.SimulationFrame;
import org.example.Model.Server;
import org.example.Model.Task;

import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit ;
    public int maxServiceTime ;
    public int minServiceTime ;
    public int minArrivalTime ;
    public int maxArrivalTime ;

    public int numberOfServers ;
    public int numberOfClients ;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    //entity resposible with queue management and client distribution
    private Scheduler scheduler;
    //frame for displaying simulation
    private SimulationFrame frame;
    //pool of tasks (client shopping in the store)
    private List<Task> generatedTasks;

    public SimulationManager(){
        this.generatedTasks=new ArrayList<>();



        this.frame=new SimulationFrame(this);



    }

    private void generateNRandomTasks(){
        Random r=new Random();

        for(int itterator=0;itterator<this.numberOfClients;itterator++){
            int serviceTime = r.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
            int arrivalTime= r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            Task task=new Task(arrivalTime,serviceTime);
            this.generatedTasks.add(task);
        }
    }

    public void call(){
        this.generateNRandomTasks();
    }


    public String  print(int currentTime){

        String string="";
        string = string + "Time" + currentTime + "\n" + "Waiting clients:";
        for(Task t:this.generatedTasks){
            string=string + "(" + t.getId() + "," + t.getArrivalTime() + "," + t.getRemainedTime() + ")";
        }
        string=string + "\n";
        for(int i=1;i<=this.numberOfServers;i++)
        for(Server s:this.scheduler.getServers()){
            if(s != null && i==s.getId()) {
                string = string + "Casa" + s.getId() + ": ";
                for (Task t : s.getTasks()) {
                    if(t != null) {
                        string = string + "(" + t.getId() + "," + t.getArrivalTime() + "," + t.getRemainedTime() + ")";
                    }
                }
                string = string + "\n";
            }
        }
        return string;
    }

    @Override
    public void run() {
        int currentTime=0;
        System.out.println(timeLimit);
        while (currentTime < timeLimit) {
            if (this.EmptyQueues() !=0) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                List<Task> auxArray = new ArrayList<>();
                for (Task task : this.generatedTasks) {
                    if (task.getArrivalTime() == currentTime) {
                        this.scheduler.dispatchTask(task);
                        auxArray.add(task);
                    }
                }

                for (Task task : auxArray) {
                    this.generatedTasks.remove(task);
                }

                this.frame.getTextArea1().setText(this.frame.getTextArea1().getText() + print(currentTime));
                for (Server s : this.scheduler.getServers()) {
                    synchronized (s.getObj()) {
                        s.getObj().notifyAll();
                    }
                }
                //update UI frame
                currentTime++;
                // wait an interval of 1 second
            }
            else{
                currentTime=timeLimit+1;
            }

        }
    }

    public int EmptyQueues() {
        int empty=0;
        if(!this.generatedTasks.isEmpty()){
            empty++;
        }
        for(Server s:this.scheduler.getServers()){
            if(!s.getBlockTasks().isEmpty()) {
                empty++;
                break;
            }
        }
        return empty;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public static void main(String[] args){
        SimulationManager gen=new SimulationManager();

       // SimulationFrame f=new SimulationFrame(gen);


    }


}
