/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author James Fairburn
 */
public class MazeDisplayPanel extends javax.swing.JPanel {
    private Image dbImage;
    private Graphics dbg;
    private Maze maze = new Maze(40, 30);
    
    /**
     * Creates new form MazeMapPanel
     */
    public MazeDisplayPanel() {
        this.setBounds(0, 0, 400, 400);
    }
   
    @Override
    public void paintComponent(Graphics g) {
        int xSize = 400/maze.xSize;
        int ySize = 400/maze.ySize;
        System.out.println(xSize);
        System.out.println(ySize);
        for(int x = 0; x < maze.xSize; x++){
            for(int y = 0; y < maze.ySize; y++){
                GridSquare currentSquare = maze.mazeGrid[x][y];
                if(currentSquare.isStart()){
                    g.setColor(Color.GREEN);
                } else if(currentSquare.isEnd()){
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.WHITE);
                }
                int xPos = xSize*x;
                int yPos = ySize*y;
                g.fillRect(xPos, yPos, xSize, ySize);
                g.setColor(Color.BLACK);
                if(currentSquare.getNorthWall() == true){
                    g.drawLine(xPos, yPos, xPos+xSize, yPos);
                }
                if(currentSquare.getSouthWall() == true){
                    g.drawLine(xPos, yPos+ySize-1, xPos+xSize, yPos+ySize-1);
                }
                if(currentSquare.getWestWall() == true){
                    g.drawLine(xPos, yPos, xPos, yPos+ySize);
                }
                if(currentSquare.getEastWall() == true){
                    g.drawLine(xPos+xSize-1 , yPos, xPos+xSize-1, yPos+ySize);
                }
                
            }
        }
    }

    public Maze getMaze() {
        return maze;
    }
    
    public void newGen(){
        
        maze.newMaze();
        this.updateUI();
        repaint();
        
    }
    
}
