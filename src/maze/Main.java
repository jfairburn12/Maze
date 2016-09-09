/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.Map;

/**
 *
 * @author James Fairburn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestMazeFrame dis = new TestMazeFrame();
        dis.setVisible(true);
        MazeDisplayPanel mdp = (MazeDisplayPanel) dis.getMazePanel().getComponent(0);
        Maze m = mdp.getMaze();
        ViewGrid view = m.getPlayerView(0, 2, Direction.EAST);
        System.out.println("lol");
    }
}
