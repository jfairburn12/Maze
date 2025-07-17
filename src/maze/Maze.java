/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * 
 */
public class Maze {
    final int MAX_SIZE = 64;
    final int MIN_SIZE = 10;
    int xSize;
    int ySize;
    GridSquare startSqaure;
    GridSquare[][] mazeGrid;
    
    public Maze(int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
        if(xSize > MAX_SIZE){ xSize = 64;}
        if(ySize > MAX_SIZE){ ySize = 64;}
        if(xSize < MIN_SIZE){ xSize = 10;}
        if(ySize < MIN_SIZE){ ySize = 10;}
        
        mazeGrid = new GridSquare[xSize][ySize];
        initGrid();
        generateMaze();
    }
    
    private void initGrid(){
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                mazeGrid[x][y] = new GridSquare(x, y);
            }   
        }
    }
    
    @SuppressWarnings("empty-statement")
    public Map getUnvistedNeighbors(int x, int y){
        Map<Direction, GridSquare> toReturn = new HashMap<>();
        if(x != 0){
            if(!mazeGrid[x -1][y].isVisted())
                toReturn.put(Direction.WEST, mazeGrid[x -1][y]);
        }
        if(y != ySize -1){
            if(!mazeGrid[x][y +1].isVisted())
                toReturn.put(Direction.SOUTH, mazeGrid[x][y +1]);
        } 
        if(x != xSize -1){
            if(!mazeGrid[x + 1][y].isVisted())
                toReturn.put(Direction.EAST, mazeGrid[x + 1][y]);
        }
        if(y != 0){
            if(!mazeGrid[x][y-1].isVisted())
                toReturn.put(Direction.NORTH, mazeGrid[x][y-1]);
        }
        return toReturn;
    }
    
    public Map getAllNeighbors(int x, int y){
        Map<Direction, GridSquare> toReturn = new HashMap<>();
        if(x != 0){
                toReturn.put(Direction.WEST, mazeGrid[x -1][y]);
        }
        if(y != ySize -1){
                toReturn.put(Direction.SOUTH, mazeGrid[x][y +1]);
        } 
        if(x != xSize -1){
                toReturn.put(Direction.EAST, mazeGrid[x + 1][y]);
        }
        if(y != 0){
                toReturn.put(Direction.NORTH, mazeGrid[x][y-1]);
        }
        return toReturn;
    }
    
    public void removeWall(GridSquare current, GridSquare next, Direction relation){
        if(relation == Direction.WEST){
            current.removeWestWall();
            next.removeEastWall();
        }
        if(relation == Direction.SOUTH){
            current.removeSouthWall();
            next.removeNorthWall();
        }
        if(relation == Direction.EAST){
            current.removeEastWall();
            next.removeWestWall();
        }
        if(relation == Direction.NORTH){
            current.removeNorthWall();
            next.removeSouthWall();
        }
    }
    
    private void generateMaze(){
        
        ArrayList<GridSquare> deadEndList = new ArrayList<>();
        Stack vistedStack = new Stack();
        GridSquare current = mazeGrid[randomNum(0, xSize-1)][randomNum(0, ySize -1)];
        current.setEnd();
        current.vist();
        vistedStack.add(current);
        while(!vistedStack.isEmpty()){
            current = (GridSquare) vistedStack.peek();
            Map neigbourMap =  getUnvistedNeighbors(current.x, current.y);
            if(getUnvistedNeighbors(current.x, current.y).size() != 0){
                Object[] neigboursArray = neigbourMap.values().toArray();
                Object[] keyArray = neigbourMap.keySet().toArray();
                int nextIndex = randomNum(0, neigbourMap.size()-1);
                Direction relation = (Direction )  keyArray[nextIndex];
                GridSquare nextSquare = (GridSquare) neigboursArray[nextIndex]; 
                removeWall(current, nextSquare, relation);
                current.vist();
                nextSquare.vist();
                vistedStack.add(nextSquare);
            } else {
                neigbourMap = getAllNeighbors(current.x, current.y);
                int wallRemoveChance = randomNum(0, 2);
                if(wallRemoveChance == 1 && canPostWallRemoval(current.x, current.y) ){
                    Object[] neigboursArray = neigbourMap.values().toArray();
                    Object[] keyArray = neigbourMap.keySet().toArray();
                    int nextIndex = randomNum(0, neigbourMap.size()-1);
                    Direction relation = (Direction)  keyArray[nextIndex];
                    GridSquare nextSquare = (GridSquare) neigboursArray[nextIndex]; 
                    removeWall(current, nextSquare, relation);
                }
                if(current.wallCount() == 3 && !current.isEnd()){
                    deadEndList.add(current);
                }
                vistedStack.pop();
            }
        }
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                checkAndRemoveNorthFloatWalls(x, y);
                checkAndRemoveEastFloatWalls(x, y);
            }   
        }
        int endIndex = randomNum(0, deadEndList.size() -1);
        deadEndList.get(endIndex).setStart();
        startSqaure = deadEndList.get(endIndex);
    }
    
    public void newMaze(){
        initGrid();
        generateMaze();
    }
    
    public boolean canPostWallRemoval(int x, int y){
        GridSquare currentSquare = mazeGrid[x][y];
        if(currentSquare.wallCount() == 3){
            return false;
        }
        Map neighbours = getAllNeighbors(x, y);
        for(x = 0; x < neighbours.size(); x++){
            GridSquare neighbour = (GridSquare) neighbours.values().toArray()[x];
            if(neighbour.wallCount() == 3){
                return false;
            }
        }
        return true;
    }
    
    public int randomNum(int start, int end){
        return (int) (start + (Math.random() * (end - start +1)));
    }
    
    public GridSquare getGridSquare(int x,int y){
        return mazeGrid[x][y];
    }
    
    private void checkAndRemoveNorthFloatWalls(int x, int y){
        GridSquare current = mazeGrid[x][y];
        if(current.getNorthWall() && y != 0 && x!=0 && x!=xSize-1){
            GridSquare yMinus = mazeGrid[x][y-1];
            GridSquare xPlus = mazeGrid[x+1][y];
            GridSquare xMinus = mazeGrid[x-1][y];
            if(!yMinus.getEastWall() && !yMinus.getWestWall() && !xPlus.getNorthWall() && !xMinus.getNorthWall()
               && !current.getEastWall() && !current.getWestWall()){
                    System.out.println(x + " " + y);
                    current.removeNorthWall();
                    yMinus.removeSouthWall();
            }
        }
    }
    
    private void checkAndRemoveEastFloatWalls(int x, int y){
        GridSquare current = mazeGrid[x][y];
        if(current.getEastWall() && y!= 0 && x!= xSize-1 && y!= ySize-1){
            GridSquare xPlus = mazeGrid[x+1][y];
            GridSquare yPlus = mazeGrid[x][y+1];
            GridSquare yMinus = mazeGrid[x][y-1];
            if(!xPlus.getNorthWall() && !xPlus.getSouthWall() && !yMinus.getEastWall() && !yPlus.getEastWall()
                && !current.getNorthWall() && !current.getSouthWall()){
                current.removeEastWall();
                xPlus.removeWestWall();
            }
        }
    }
    
    
    public ViewGrid getPlayerView(int x,int y, Direction facing){
    ViewGrid toReturn = new ViewGrid();
    GridSquare current = mazeGrid[x][y];
    for(int row = 1; row <= 3; row++){
        if(!current.doesWallExist(facing)){
            if(row > 1){
                toReturn.nextRow();
            }
            System.out.println(row);
            current = (GridSquare) getAllNeighbors(current.x, current.y).get(facing);
            int middle = toReturn.getCurrentMiddleValue();
            toReturn.getCurrentRow()[middle] = current;
            
            if(!current.doesWallExist(facing.getLeft())){
                GridSquare left = (GridSquare) getAllNeighbors(current.x, current.y).get(facing.getLeft());
                toReturn.getCurrentRow()[middle -1] = left;
                if(row >= 2 && !left.doesWallExist(facing.getLeft())){
                    GridSquare leftBy2 = (GridSquare) getAllNeighbors(left.x, left.y).get(facing.getLeft());
                    toReturn.getCurrentRow()[middle -2] = leftBy2;
                    if (row == 3 && !leftBy2.doesWallExist(facing.getLeft())){
                        GridSquare leftBy3 = (GridSquare) getAllNeighbors(leftBy2.x, leftBy2.y).get(facing.getLeft());
                        toReturn.getCurrentRow()[middle -3] = leftBy3;
                    }
                }
            }
            
            if(!current.doesWallExist(facing.getRight())){
                GridSquare right = (GridSquare) getAllNeighbors(current.x, current.y).get(facing.getRight()); 
                toReturn.getCurrentRow()[middle + 1] = right;
                if(row >= 2 && !right.doesWallExist(facing.getRight())){
                    GridSquare rightBy2 = (GridSquare) getAllNeighbors(right.x, right.y).get(facing.getRight());
                    toReturn.getCurrentRow()[middle + 2] = rightBy2;
                    if (row == 3 && !rightBy2.doesWallExist(facing.getRight())){
                        GridSquare rightBy3 = (GridSquare) getAllNeighbors(rightBy2.x, rightBy2.y).get(facing.getRight());
                        toReturn.getCurrentRow()[middle + 3] = rightBy3;
                    }
                }
            }
            
            //get grid you can see but has a wall on the left or the right
             if(row < 3){
                 for(int i = 0; i < toReturn.getCurrentRow().length; i++){
                     if(toReturn.getCurrentRow()[i] != null && !toReturn.getCurrentRow()[i].doesWallExist(facing)){
                        toReturn.getAboveRow()[i+1] = (GridSquare) getAllNeighbors(toReturn.getCurrentRow()[i].x, 
                                toReturn.getCurrentRow()[i].y).get(facing);
                        int io = 0;
                     }
                 }
             }
            
        }
    }

    return toReturn;
    }
}
