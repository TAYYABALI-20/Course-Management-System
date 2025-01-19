package main;

import javax.swing.JFrame;
import loaders.Load;

public class Main {

    public void start() {

        JFrame frame = new JFrame("Course Management System");

        Load server = new Load();

        frame.add(server);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);

    }

}