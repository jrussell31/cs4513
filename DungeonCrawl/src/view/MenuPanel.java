
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.GameData;

public class MenuPanel extends JPanel{
    private JTextField txtLevel;
    private JLabel lblSubMessage;
    
    public static int pwidth, pheight;
    
    private String menuText;
    private String restartText;

 public MenuPanel(int pwidth, int pheight)
    {
        MenuPanel.pwidth = pwidth;
        MenuPanel.pheight = pheight;
        
        initComponents();
    }
 
 private void initComponents(){
        setPreferredSize(new Dimension(MenuPanel.pwidth, MenuPanel.pheight));
        setBorder(BorderFactory.createLineBorder(Color.gray, 20));
        // Could be moved to class where level attributes are tracked
        /*
        menuText = "Main menu Options coming soon" ;
        txtLevel = new JTextField(menuText);
        txtLevel.setFont(new Font("Tahoma", 0, 36));
        txtLevel.setHorizontalAlignment(JTextField.CENTER);
        txtLevel.setEditable(false);
        txtLevel.setFocusable(false);
        */
        
        restartText = "Press \"R\" to restart game";
        txtLevel = new JTextField(restartText);
        txtLevel.setFont(new Font("Tahoma", 0, 36));
        txtLevel.setHorizontalAlignment(JTextField.CENTER);
        txtLevel.setEditable(false);
        txtLevel.setFocusable(false);
        
        lblSubMessage = new JLabel("Press spacebar to continue", JLabel.CENTER);
        
        this.setLayout(new BorderLayout());
        this.add(txtLevel, BorderLayout.CENTER); 
        this.add(lblSubMessage, BorderLayout.SOUTH);
        this.setFocusable(false);
    }
 
 public void setMenuText(String newmenuText){
        this.restartText = newmenuText;
        txtLevel.setText(this.restartText);
    }
}