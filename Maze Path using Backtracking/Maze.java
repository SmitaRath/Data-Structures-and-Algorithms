import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 *
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /**
     * The maze
     */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /**
     * Wrapper method.
     */
    public boolean findMazePath() {
        //calling the findMazePath to display the path in the maze @SmitaRath
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     *
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     * otherwise, false
     * @pre Possible path cells are in BACKGROUND color;
     * barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     * PATH color; all cells that were visited but are
     * not on the path are in the TEMPORARY color.
     */
    public boolean findMazePath(int x, int y) {
        //checking if the x and y are outside the grid id yes return false @SmitaRath
        if (x >= maze.getNCols() || y >= maze.getNRows() || x < 0 || y < 0)
            return false;
        //checking if the cell color is not the RED COLOR which can form the path @SmitaRath
        else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;
        }
        //if the cell is exit color the cell to PATH color and return true
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        } else {
            //if none of the conditions true then mark the cell to path color and check the neighbours recursilvely @SmitaRath
            maze.recolor(x, y, PATH);
            if (findMazePath(x - 1, y)
                    || findMazePath(x + 1, y)
                    || findMazePath(x, y - 1)
                    || findMazePath(x, y + 1)) {
                return true;
            } else {
                //if none of the neighbours are not forming the PATH setting it to temporary color for backtracking @SmitaRath
                maze.recolor(x, y, TEMPORARY);
                return false;
            }

        }

    }

    //helper method to check all the possible paths in the maze @SmitaRath
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        //checking if the x and y are outside the grid id yes return @SmitaRath
        if (x >= maze.getNCols() || y >= maze.getNRows() || x < 0 || y < 0)
            return;
        //checking if the cell color is not the RED COLOR which can form the path @SmitaRath
        else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return;
        }
        //if the cell is exit to the maze then pushing the cell to the stack and whole stack to the ArrayList and return @SmitaRath
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            result.add(new ArrayList(trace));
            trace.pop();
            return;
        } else {
            //pushing the cell to the stack and checking for all the neighbours of the cell for the exit path to the maze @SmitaRath
            trace.push(new PairInt(x, y));
            //coloring the cell the the Path of color @SmitaRath
            maze.recolor(x, y, PATH);
            //recursively calling all the neighbours of the cell @SmitaRath
            findMazePathStackBased(x - 1, y, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x, y + 1, result, trace);
            //popping the pushed item from the stack @SmitaRath
            trace.pop();
            //setting the visited cell to NON_BACKGROUND color to find the other possible methods
            maze.recolor(x, y, NON_BACKGROUND);

        }

    }

    //Problem 2 method to find the list of all the possible paths in the maze @SmitaRath
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        //calling findMazePathStackBased method to get all the paths @SmitaRath
        findMazePathStackBased(0, 0, result, trace);
        maze.recolor(PATH, NON_BACKGROUND);
        maze.recolor(TEMPORARY,NON_BACKGROUND);
        return result;
    }

    //Problem 3 method to find the shortest path in the maze. @SmitaRath
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        //calling the findAllMazePaths to get the list of all the methods in the maze. @SmitaRath
        ArrayList<ArrayList<PairInt>> result = findAllMazePaths(x, y);
        int size = Integer.MAX_VALUE;
        int index = 0;
        int arrayIndex = 0;
        //finding the shortest path. @SmitaRath
        for (ArrayList<PairInt> arr : result) {
            if (arr.size() < size) {
                size = arr.size();
                arrayIndex = index;
            }
            index++;
        }

        maze.recolor(PATH, NON_BACKGROUND);
        maze.recolor(TEMPORARY,NON_BACKGROUND);
        //if the list is not empty then returning the shortest path  @SmitaRath
        if (!result.isEmpty())
            return result.get(arrayIndex);
        return null;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
