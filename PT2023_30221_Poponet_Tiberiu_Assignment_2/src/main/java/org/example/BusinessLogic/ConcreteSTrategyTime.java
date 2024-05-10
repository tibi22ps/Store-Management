package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.List;

public class ConcreteSTrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t){
        Server.orderByWaitingPeriod(servers); //or
        servers.get(0).addTask(t);
    }
}
