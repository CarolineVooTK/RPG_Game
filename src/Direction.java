/** Code taken from sample solution for Project 1
 *
 */

import java.util.Random;

public class Direction {
    /**
     * All the parameters so that there is no magic numbers,
     * for different directions and how many directions can be moved
     */
    public static final int MAX_DIRECTION = 4;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    /**
     * This method is for when the actor has to rotate its direction for a new one
     * @param direction this parameter is the old direction
     * @param rotate this parameter is the rotation needed
     * @return int the new direction
     */
    public static int rotateDirection(int direction,int rotate) {
        int newDirection = direction +rotate;
        if (newDirection >= MAX_DIRECTION ){
            return (newDirection - MAX_DIRECTION);
        }
        else{
            return newDirection;
        }
    }
}
