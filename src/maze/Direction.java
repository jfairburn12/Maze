/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author James Fairburn
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;
    
    private Direction left;
    private Direction right;
    
    static {
        NORTH.left = WEST;
        NORTH.right = EAST;
        EAST.left = NORTH;
        EAST.right = SOUTH;
        SOUTH.left = EAST;
        SOUTH.right = WEST;
        WEST.left = SOUTH;
        WEST.right = NORTH;
    }
    
    public Direction getLeft(){
        return  left;
    }
    
    public Direction getRight(){
        return right;
    }
    
    
}
