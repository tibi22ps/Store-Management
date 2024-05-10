package org.example.Controller;

import org.example.BusinessLogic.*;
import org.example.GUI.SimulationFrame;
import org.example.Model.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    public ActionListener buttonStart(SimulationManager m, SimulationFrame frame){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setMaxArrivalTime(Integer.parseInt(frame.getTextField2().getText()));
                m.setMinArrivalTime(Integer.parseInt(frame.getTextField1().getText()));
                m.setMaxServiceTime(Integer.parseInt(frame.getTextField4().getText()));
                m.setMinServiceTime(Integer.parseInt(frame.getTextField3().getText()));
                m.setNumberOfClients(Integer.parseInt(frame.getTextField5().getText()));
                m.setNumberOfServers(Integer.parseInt(frame.getTextField6().getText()));
                m.setTimeLimit(Integer.parseInt(frame.getTextField7().getText()));

                m.setScheduler( new Scheduler(m.numberOfServers));
                for(Server i:m.getScheduler().getServers()) {
                    Thread aux = new Thread(i);
                    m.getScheduler().getThreads().add(aux);
                }
                if(m.selectionPolicy.equals(SelectionPolicy.SHORTEST_TIME)) {
                    m.getScheduler().setStrategy(new ConcreteSTrategyTime());
                }
                else{
                    m.getScheduler().setStrategy(new ConcreteSTrategyQueue());
                }


                m.call();
                for(Thread s:m.getScheduler().getThreads())
                {
                    s.start();
                }

                Thread t=new Thread(m);
                t.start();

            }
        };
    return a;
    }


}
