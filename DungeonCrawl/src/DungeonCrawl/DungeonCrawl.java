package DungeonCrawl;
import controller.Animator;
import java.awt.Color;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import model.GameData;
import view.MainWindow;
import view.GamePanel;
import view.InventoryPanel;
import view.BannerPanel;

public class DungeonCrawl {
    public static GameData gameData;
    public static Animator animator;
    public static JFrame mainWindow;
    public static InventoryPanel inventoryPanel;
    public static GamePanel gamePanel;
    public static BannerPanel bannerPanel;
    public static Thread thread;

    public static void main(String[] args) {
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel(1000, 900);
        inventoryPanel = new InventoryPanel(350, 900);
        bannerPanel =  new BannerPanel(650, 300);
        
        mainWindow = new MainWindow();
        mainWindow.setTitle("Level 1!");
        mainWindow.setSize(1500, 1000);
        mainWindow.setLocation(0, 0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        
       thread = new Thread(animator);
    }
    
    public static void startGame(){         
        GameData.resetGameData();
        thread.start();
        
        new Thread(new Runnable() {
            boolean running = false;
            long currentTime, previousTime;
            
            public void run() {
                running = true;
                currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) % 1000;
                previousTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) % 1000;
                
                while(running){
                    currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) % 1000;
                    if(currentTime > previousTime){
                        previousTime = currentTime;
                        if(GameData.time > 0){
                            GameData.time--;
                        }
                    }
                }
            }
        }).start();
    }
}
