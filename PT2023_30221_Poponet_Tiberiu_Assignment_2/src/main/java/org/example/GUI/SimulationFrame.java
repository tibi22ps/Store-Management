package org.example.GUI;

import org.example.BusinessLogic.SimulationManager;
import org.example.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import  javax.swing.JFrame;



public class SimulationFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton pressButton;
    private JTextArea textArea1;
    private JPanel Panou;
    //private JScrollBar scrollBar1;

    public SimulationFrame(SimulationManager m){
        JFrame frame = new JFrame("Tema2");


        frame.setContentPane(Panou);
        frame.pack();
        frame.setSize(800, 700);
        frame.setVisible(true);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        textArea1.setBackground(Color.darkGray);
        textArea1.setForeground(Color.white);



        Controller c=new Controller();
        pressButton.addActionListener(c.buttonStart(m,this));

    }


    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    //public JScrollBar getScrollBar1() { return scrollBar1;}


}
