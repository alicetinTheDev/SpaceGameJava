package myspacegame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class MySpaceGame extends JFrame{

    public MySpaceGame(String title) throws HeadlessException {
        super(title);
    }

    
  
    public static void main(String[] args){
       
       MySpaceGame screen = new MySpaceGame("Space Game");
       
       screen.setResizable(false);
       screen.setFocusable(false);
       
       screen.setSize(800, 600);
       
       screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       Game game = new Game();
       
       game.requestFocus();
       
       game.addKeyListener(game);
       
       game.setFocusable(true);
       game.setFocusTraversalKeysEnabled(false);
       
       screen.add(game);
       
       screen.setVisible(true);
   }    
}
