package view;

import DungeonCrawl.DungeonCrawl;
import controller.KeyController;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.GameData;
import model.GameObject;

public class GameWindow extends JFrame 
{
    private Graphics graphics;
    private Image dbImage = null;
    
    public GameWindow() 
    {
        Container c = getContentPane();

        c.add(DungeonCrawl.metricPanel, "North");
        c.add(DungeonCrawl.gamePanel, "Center");
        
        KeyController keyListener = new KeyController();
        DungeonCrawl.gamePanel.addKeyListener(keyListener);
        this.setResizable(false);
    }
}