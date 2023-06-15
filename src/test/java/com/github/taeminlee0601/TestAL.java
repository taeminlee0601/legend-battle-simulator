package com.github.taeminlee0601;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestAL implements ActionListener{
    private int count;
    
    public TestAL(int count) {
        this.count = count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;

        System.out.println(count);
    }
}
