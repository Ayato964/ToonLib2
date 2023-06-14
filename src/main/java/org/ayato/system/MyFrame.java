package org.ayato.system;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyFrame extends JFrame {
    public String NAME;
    private final MainPanel panel;
    public int DW;
    public int DH;
    public Rectangle DESCTOP_BOUNDS;
    public Graphics g;


    public MyFrame(String title){
        //JOptionPane.showMessageDialog(new JFrame(), getClass().getClassLoader().getResource("assets/").toString());
        setTitle(title);
        this.setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        NAME = title;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DESCTOP_BOUNDS = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        DW = (int) DESCTOP_BOUNDS.getWidth() / 198;
        DH =(int) DESCTOP_BOUNDS.getHeight() / 108;
        panel = new MainPanel();
        add("Center", panel);
        pack();
        g = panel.getGraphics();
        repaint();
    }

    private class MainPanel extends JLabel{
        BufferedImage image;
        final Graphics hackingPazzle;
        public MainPanel(){
            image = new BufferedImage((int) DESCTOP_BOUNDS.getWidth(),(int) DESCTOP_BOUNDS.getHeight(), BufferedImage.TYPE_INT_RGB);
            this.setIcon(new ImageIcon(image));
            hackingPazzle = image.createGraphics();
            repaint();
        }
        public Graphics getGraphics(){
            return hackingPazzle;
        }

    }
}
