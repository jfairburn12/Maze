/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author James Fairburn
 */
public class ViewGrid {
    private GridSquare[] frontRow = new GridSquare[3];
    private GridSquare[] middleRow = new GridSquare[5];
    private GridSquare[] backRow = new GridSquare[7];
    private GridSquare[] currentRow;
    public ViewGrid(){
        currentRow = frontRow;
    }

    public GridSquare[] getFrontRow() {
        return frontRow;
    }

    public GridSquare[] getBackRow() {
        return backRow;
    }

    public GridSquare[] getMiddleRow() {
        return middleRow;
    }

    public GridSquare[] getCurrentRow() {
        return currentRow;
    }
     
    public void nextRow(){
        if(currentRow == frontRow){
            currentRow = middleRow;
        } else{
            currentRow = backRow;
        }
    }
    
    public GridSquare[] getAboveRow(){
        
        if(currentRow == frontRow){
            return middleRow;
        }
        
        if(currentRow == middleRow){
            return backRow;
        }
        
        return null;
    }
    
    public int getCurrentMiddleValue(){
        return currentRow.length/2;
    }
    
}
