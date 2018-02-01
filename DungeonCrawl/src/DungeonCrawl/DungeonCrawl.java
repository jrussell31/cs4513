/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawl;
import controller.Animator;
import javax.swing.JFrame;
import model.GameData;
import view.GameWindow;
import view.MainPanel;
import view.MetricPanel;
import view.MainWindow;
/**
 *
 * @author cameron
 */
public class DungeonCrawl {
    public static GameData gameData;
    public static Animator animator;
    public static MetricPanel metricPanel;
    public static MainPanel gamePanel;
    public static Thread thread;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new MainPanel();
        metricPanel = new MetricPanel();
        
        JFrame gameForReal = new GameWindow();
        gameForReal.setTitle("Level 1!");
        gameForReal.setSize(750, 810);
        gameForReal.setLocation(0, 0);
        gameForReal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameForReal.setVisible(true);
        
        thread = new Thread(animator);
        thread.start();
    }
}
