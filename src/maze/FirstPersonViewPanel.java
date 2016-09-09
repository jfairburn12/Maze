/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author James Fairburn
 */
public class FirstPersonViewPanel extends javax.swing.JPanel {
    final int xSize = 600;
    final int ySize = 400;
    private Maze maze;
    public FirstPersonViewPanel(Maze maze){
        this.setBounds(0, 0, xSize, ySize);
        this.maze = maze;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Point CurrentTopLeft = new Point(0, 0);
        Point CurrentTopRight = new Point (600, 0);
        Point CurrentBottemLeft = new Point(0, 400);
        Point CurrentBottemRight = new Point(600, 400);
        Point frontLeftBottemLeft = new Point(0, 300);
        Point frontLeftTopLeft = new Point(0, 100);
        Point frontRightBottemRight = new Point(600, 300);
        Point frontRightTopRight = new Point(600, 100);
        Point frontMiddleTopLeft = new Point(150, 100);
        Point frontMiddleBottemLeft = new Point(150, 300);
        Point frontMiddleTopRight = new Point(450, 100);
        Point frontMiddleBottemRight = new Point(450, 300);
        Point middleMiddleTopLeft = new Point(215, 145);
        Point middleMiddleBottemLeft = new Point(215, 255);
        Point middleMiddleTopRight = new Point(385, 145);
        Point middleMiddleBottemRight = new Point(385, 255);
        Point middleLeftTopLeft = new Point(105,145);
        Point middleLeftBottemLeft = new Point(105, 255);
        Point middleRightTopRight = new Point(500, 145);
        Point middleRightBottemRight = new Point(500, 255);
        Point farMiddleTopLeft = new Point(250, 170);
        Point farMiddleBottemLeft = new Point(250, 230);
        Point farMiddleTopRight = new Point(350, 170);
        Point farMiddleBottemRight = new Point (350, 230);
        Point farLeftTopLeft = new Point(170, 170);
        Point farLeftBottemLeft = new Point(170, 230);
        Point farRightTopRight = new Point(430, 170);
        Point farRightBottemRight = new Point(430, 230);
        
        g.setColor(Color.BLACK);
        g.drawLine(CurrentTopLeft.getX(), CurrentTopLeft.getY(), farMiddleTopLeft.getX(), farMiddleTopLeft.getY());
        g.drawLine(CurrentTopRight.getX(), CurrentTopRight.getY(), farMiddleTopRight.getX(), farMiddleTopRight.getY());
        g.drawLine(CurrentBottemLeft.getX(), CurrentBottemLeft.getY(), farMiddleBottemLeft.getX(), farMiddleBottemLeft.getY());
        g.drawLine(CurrentBottemRight.getX(), CurrentBottemRight.getY(), farMiddleBottemRight.getX(), farMiddleBottemRight.getY());
        g.drawLine(frontMiddleTopLeft.getX(), frontMiddleTopLeft.getY(), frontMiddleBottemLeft.getX(), frontMiddleBottemLeft.getY());
        g.drawLine(frontMiddleTopRight.getX(), frontMiddleTopRight.getY(), frontMiddleBottemRight.getX(), frontMiddleBottemRight.getY());
        g.drawLine(frontLeftBottemLeft.getX(), frontLeftBottemLeft.getY(), frontMiddleBottemLeft.getX(), frontMiddleBottemLeft.getY());
        g.drawLine(frontLeftTopLeft.getX(), frontLeftTopLeft.getY(), farLeftTopLeft.getX(), farLeftTopLeft.getY());
        g.drawLine(frontLeftTopLeft.getX(), frontLeftTopLeft.getY(), frontMiddleTopLeft.getX(), frontMiddleTopLeft.getY());
        g.drawLine(farLeftBottemLeft.getX(), farMiddleBottemLeft.getY(), frontLeftBottemLeft.getX(), frontLeftBottemLeft.getY());
        g.drawLine(farLeftTopLeft.getX(), farLeftTopLeft.y, farMiddleTopLeft.getX(), farMiddleTopLeft.getY());
        g.drawLine(middleLeftTopLeft.getX(), middleLeftTopLeft.getY(), middleLeftBottemLeft.getX(), middleLeftBottemLeft.getY());
        g.drawLine(middleMiddleTopLeft.getX(), middleMiddleTopLeft.getY(), middleMiddleBottemLeft.getX(), middleMiddleBottemLeft.getY());
        g.drawLine(middleLeftTopLeft.getX(), middleLeftTopLeft.getY(), middleMiddleTopLeft.getX(), middleMiddleTopLeft.getY());
        g.drawLine(middleLeftBottemLeft.getX(), middleLeftBottemLeft.getY(), middleMiddleBottemLeft.getX(), middleMiddleBottemLeft.getY());
        g.drawLine(frontRightTopRight.getX(), frontMiddleTopRight.getY(), farRightTopRight.getX(), farRightTopRight.getY());
        g.drawLine(frontRightBottemRight.getX(), frontRightBottemRight.getY(), farRightBottemRight.getX(), farRightBottemRight.getY());
        g.drawLine(middleRightTopRight.getX(), middleRightTopRight.getY(), middleRightBottemRight.getX(), middleRightBottemRight.getY());
        g.drawLine(middleMiddleTopRight.getX(), middleMiddleTopRight.getY(), middleMiddleBottemRight.getX(), middleMiddleBottemRight.getY());
        
    }
    
    
}
