/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DungeonCrawl.DungeonCrawl;
import controller.KeyController;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

/**
 *
 * @author russe_000
 */
public class MainWindow extends JFrame {    
    public MainWindow(){
        initComponents();
        
        KeyController keyListener = new KeyController();
        DungeonCrawl.gamePanel.addKeyListener(keyListener);
        this.setResizable(false);
    }
    
    private void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Game Panel layout section
        GroupLayout gamePanelLayout = new GroupLayout(DungeonCrawl.gamePanel);
        DungeonCrawl.gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, GamePanel.pwidth, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, GamePanel.pheight, Short.MAX_VALUE)
        );
        
        // Inventory Panel layout section
        GroupLayout inventoryPanelLayout = new GroupLayout(DungeonCrawl.inventoryPanel);
        DungeonCrawl.inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, InventoryPanel.pwidth, Short.MAX_VALUE)
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, InventoryPanel.pwidth, Short.MAX_VALUE)
        );
        
        // Entire Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(DungeonCrawl.gamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(DungeonCrawl.inventoryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(DungeonCrawl.inventoryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(DungeonCrawl.gamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }
}
