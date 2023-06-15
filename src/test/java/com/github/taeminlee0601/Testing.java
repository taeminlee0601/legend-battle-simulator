package com.github.taeminlee0601;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Testing {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(900,600);
        // Setting the frame to not be resizeable
        frame.setResizable(false);
        // Makeing the frame be able to be exited when the x is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the window be on the middle of the screen
        frame.setLocationRelativeTo(null);
        // Setting the title of the frame
        frame.setTitle("Legend Battle Simulator");
        // Making the frame visible
        frame.setVisible(true);

        JButton button = new JButton("Testing");
        button.setBounds(100, 100, 200, 100);

        int count = 0;

        button.addActionListener(new TestAL(count));

        frame.add(button);
    }
}
