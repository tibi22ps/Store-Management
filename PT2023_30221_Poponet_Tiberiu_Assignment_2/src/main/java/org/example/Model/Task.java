package org.example.Model;

public class Task {
    private int arrivalTime;
    private int remainedTime;
    private int id;

    private static int i;

    public Task(int arrivalTime, int remainedTime){
        this.arrivalTime=arrivalTime;
        this.remainedTime=remainedTime;

        i++;
        id=i;
    }

    public void setRemainedTime(int remainedTime) {
        this.remainedTime = remainedTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getRemainedTime() {
        return remainedTime;
    }

    public int getId() {
        return id;
    }

}
