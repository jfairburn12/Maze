/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author James Fairburn
 */
public class GridSquare {
    public int x;
    public int y;
    private boolean start;
    private boolean end;
    private boolean visted;
    private boolean northWall;
    private boolean eastWall;
    private boolean southWall;
    private boolean westWall;
    
    public GridSquare(int x, int y){
        this.x = x;
        this.y = y;        
        visted = false;
        northWall = true;
        southWall = true;
        eastWall = true;
        westWall = true;
    }
    
    public void setStart(){
        start = true;
    }
    
    public void setEnd(){
        end = true;
    }
    
    public void vist(){
        visted = true;
    }
    
    public void removeNorthWall(){
        northWall = false;
    }
    
    public void removeSouthWall(){
        southWall = false;
    }
    
    public void removeEastWall(){
        eastWall = false;
    }
    
    public void removeWestWall(){
        westWall = false;
    }

    public boolean isVisted() {
        return visted;
    }

    public boolean getEastWall() {
        return eastWall;
    }

    public boolean getNorthWall() {
        return northWall;
    }

    public boolean getSouthWall() {
        return southWall;
    }

    public boolean getWestWall() {
        return westWall;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isEnd() {
        return end;
    }
    
    public int wallCount(){
        int toReturn = 0;
        if(northWall){
            toReturn++;
        }
        if(southWall){
            toReturn++;
        }
        if(westWall){
            toReturn++;
        }
        if(eastWall){
            toReturn++;
        }
        return toReturn;
    }
    
    public boolean doesWallExist(Direction dir){
        if(dir == Direction.EAST && eastWall){
            return true;
        } else if (dir == Direction.NORTH && northWall){
            return true;
        } else if (dir == Direction.SOUTH && southWall){
            return true;
        } else if (dir == Direction.WEST && westWall){
            return true;
        } else {
            return false;
        }
    }
}
