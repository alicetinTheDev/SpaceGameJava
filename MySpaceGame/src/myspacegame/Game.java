package myspacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener,ActionListener{
    private int timerMiliSeconds = 1;
    
    Timer timer = new Timer(timerMiliSeconds,this);
    
    private int elapsedTime = 0;    
    
    private int fireCounter = 0;
    
    private BufferedImage spaceShipImg;
    
    private ArrayList<Fire> allFires = new ArrayList<Fire>();
    
    private int fireDirY = 1;
    
    private int targetBallX = 0;
    
    private int targetBallDirX = 2;
    
    private int spaceShipX = 0;
    
    private int spaceDirX = 20;

    public boolean checkOut(){
        
        for (Fire fire : allFires) {
            if(new Rectangle(fire.getX(), fire.getY(),10,20).intersects(new Rectangle(targetBallX,0,20,20))){
                return true;
            }
        }
        return false;
    }
    
    public Game() {
        
            try {
                spaceShipImg = ImageIO.read(new FileImageInputStream(new File("D:/Backup/Belgelerim/GitHub/JavaSpaceGame/MySpaceGame/src/assets/spaceship.png")));
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            setBackground(Color.BLACK);
            
            timer.start();
                       
    }      

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        elapsedTime += timerMiliSeconds;
        
        g.setColor(Color.red);
        
        g.fillOval(targetBallX, 0, 20, 20);
        
        g.drawImage(spaceShipImg, spaceShipX, 490, spaceShipImg.getWidth() / 10, spaceShipImg.getHeight() / 10, this);
    
        for(Fire fire : allFires){
            if(fire.getY() < 0)
                allFires.remove(fire);
        }
        
        g.setColor(Color.BLUE);
        
        for(Fire fire : allFires){
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }
        
        if(checkOut()){
           timer.stop();
           String finalMessage = "You won!\n" +
                                 "Fires: " + fireCounter +
                                 "\nElapsed time: " + elapsedTime/100 + " seconds..";
           
           JOptionPane.showMessageDialog(this, finalMessage);
        }                    
    }
    
    @Override
    public void repaint(Rectangle r) {
        super.repaint(r); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            
            if(spaceShipX <= 0){
                spaceShipX = 0;
            }else{
                spaceShipX -= spaceDirX;
            }               
                
        }else if(c == KeyEvent.VK_RIGHT){
        
            if(spaceShipX >= 740){
                spaceShipX = 740;
            }else{
                spaceShipX += spaceDirX;
            } 
        }else if(c == KeyEvent.VK_CONTROL){
            allFires.add(new Fire(spaceShipX+15, 470));
            
            fireCounter++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (Fire fire : allFires){
            fire.setY(fire.getY() - (fireDirY+10));
            
        }
        
        targetBallX += targetBallDirX;
        
        if(targetBallX >= 750)
            targetBallDirX = -targetBallDirX;
            
        if(targetBallX <= 0)
            targetBallDirX = -targetBallDirX;
        
        repaint();
    }   
    
}
