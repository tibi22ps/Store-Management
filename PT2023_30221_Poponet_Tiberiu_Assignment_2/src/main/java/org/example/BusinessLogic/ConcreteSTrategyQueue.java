package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcreteSTrategyQueue implements  Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server ording=servers.get(0);
        for(Server i:servers){
            if(Arrays.stream(ording.getTasks()).count() > Arrays.stream(i.getTasks()).count()){
                ording=i;
            }
        }
        ording.addTask(t);
    }
}
