package com.matheus.interrupt.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.matheus.interrupt.front.progressBars.ProcessProgress;

public class MainScreen extends JFrame {

    private Color color = Color.LIGHT_GRAY;
    private LeftSideBar leftSideBar = new LeftSideBar(8, color);
    private RightSideBar rightSideBar = new RightSideBar(8, color);
    private ProcessProgress currentProcessing = new ProcessProgress();
    private ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Processador.png"));

    public MainScreen() {
        setSize(new Dimension(1000, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(leftSideBar, BorderLayout.WEST);
        add(rightSideBar, BorderLayout.EAST);
        JLabel label = new JLabel();
        label.setIcon(image);
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(150, 150));
        imagePanel.setBackground(Color.BLACK);
        imagePanel.add(label);
        ProgressBarHolder progressBarHolder = new ProgressBarHolder(Color.BLACK);
        currentProcessing.setVisible(true);
        progressBarHolder.add(currentProcessing);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.add(imagePanel);
        panel.add(progressBarHolder);
        add(panel, BorderLayout.CENTER);
    }

    public LeftSideBar getLeftSideBar() {
        return leftSideBar;
    }

    public RightSideBar getRightSideBar() {
        return rightSideBar;
    }

    public ProcessProgress getCurrentProcessProgress() {
        return currentProcessing;
    }
}
