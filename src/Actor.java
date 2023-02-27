/** Code taken from sample solution for Project 1
 *
 */

import bagel.Image;



public abstract class Actor {
    private int x;
    private int y;
    private static Actor[] actors;

    private final Image image;
    public final String type;

    private static int numActors;
    private static int numTicks;

    public int treeFruit = 3;
    public boolean active = true;
    public int g_direction= Direction.LEFT;
    public boolean carrying= false;
    public int pileFruit = 0;
    public int hoardFruits = 0;
    public boolean consuming = false;
    public int t_direction = Direction.UP;

    private final static int TWO_SEVENTY_DEGREES = 3;
    private final static int NINETY_DEGREES = 1;
    private static final int ONE_EIGHTY_DEGREES = 2;
    //normal static

    /**
     *
     * @param filename the filename for the image
     * @param type actor type
     * @param x actor x coordinate
     * @param y actor y coordinate
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public Image getImage() {
        return image;
    }

    /**
     * This method is used to store the number of actors, ticks for every update of the actor
     * @param actor array list of the actors
     * @param ticks the amount of ticks since start of program
     * @param numActor the number of gatherers and thief in the image
     */
    public final void tick(Actor[] actor, int ticks, int numActor,Actor actorMove) {
        numActors = numActor;
        numTicks = ticks;
        actors = actor;
        update(actor,actorMove);
    }

    /**
     * This method is to draw the actors into the image and draw the amount of fruits in
     * the trees, stockpiles, hoards
     */
    public void render() {
    }

    /**
     * This method is to check if all the actors has already reached fence
     * and stop the program once they are all at the fence
     * @param numActors the number of actors left that still have not reach the fence
     */
    public void checkActors(int numActors){
        for (Actor actor :actors){
            if (actor.type.equals(Gatherer.TYPE) || actor.type.equals(Thief.TYPE)) {
                if (!actor.active) {
                    numActors--;
                }
            }
        }
        if (numActors == 0){
            System.out.println(numTicks + 1 + " ticks");
            for (Actor actor :actors){
                if (actor.type.equals(Stockpiles.TYPE)){
                    System.out.println(actor.pileFruit);
                }
                else if (actor.type.equals(Hoards.TYPE)){
                    System.out.println(actor.hoardFruits);
                }

            }
            System.exit(0);
        }

    }
    /**
     * This method is used to check if the actors have reached different locations
     * @param actors array list of actors to help check if they stepped on the stationary actors
     * @param x coordinate x of the location before the actor steps on it
     * @param y coordinate y of the location before the actor steps on it
     * @return boolean This returns whether they have reached a location that needs to change
     * their direction or end the program
     */

    public boolean checkLocation(Actor[] actors,int x, int y,Actor actorMove){
        for (Actor actor : actors) {
            if ((x == actor.getX()) && y == actor.getY()) {
                //check if at fence
                switch (actor.type) {
                    case Fences.TYPE:
                        actorMove.active = false;
                        checkActors(numActors);
                        return false;

                    //check if at tree
                    case( Tree.TYPE):
                        if (actorMove.type.equals(Gatherer.TYPE) && !Gatherer.carrying) {
                            if (actor.treeFruit > 0) {
                                actorMove.carrying = true;
                                actorMove.g_direction = (Direction.rotateDirection(
                                        actorMove.g_direction, ONE_EIGHTY_DEGREES));
                                actor.treeFruit = actor.treeFruit - 1;
                            }
                        } else if (actorMove.type.equals(Thief.TYPE) && !actorMove.carrying) {
                            if (actor.treeFruit > 0) {
                                actorMove.carrying = true;
                                actor.treeFruit = actor.treeFruit - 1;
                            }
                        }
                        break;
                    //check if at Golden tree
                    case( GoldenTrees.TYPE):
                        if (actorMove.type.equals(Gatherer.TYPE) && !Gatherer.carrying) {
                            actorMove.carrying = true;
                            actorMove.g_direction = (Direction.rotateDirection(
                                    actorMove.g_direction, ONE_EIGHTY_DEGREES));
                            actor.treeFruit = actor.treeFruit - 1;
                        } else if (actorMove.type.equals(Thief.TYPE) && !actorMove.carrying) {
                            actorMove.carrying = true;
                            actor.treeFruit = actor.treeFruit - 1;
                        }
                        break;
                    //check if at stockpile
                    case Stockpiles.TYPE:
                        if (actorMove.type.equals(Gatherer.TYPE)) {
                            if (actorMove.carrying) {
                                actorMove.carrying = false;
                                actor.pileFruit++;
                            }
                            actorMove.g_direction = (Direction.rotateDirection(
                                    actorMove.g_direction, ONE_EIGHTY_DEGREES));

                        } else if (actorMove.type.equals(Thief.TYPE)) {
                            if (!actorMove.carrying) {
                                if (actor.pileFruit > 0) {
                                    actorMove.carrying = true;
                                    actor.pileFruit--;
                                    actorMove.consuming = false;
                                    actorMove.t_direction = (Direction.rotateDirection(
                                            actorMove.t_direction, NINETY_DEGREES));
                                }
                            } else {
                                actorMove.t_direction = (Direction.rotateDirection(
                                        actorMove.t_direction, NINETY_DEGREES));
                            }
                        }
                        break;

                    //check if at hoard
                    case Hoards.TYPE:
                        if (actorMove.type.equals(Gatherer.TYPE)) {
                            if (actorMove.carrying) {
                                actorMove.carrying = false;
                                actor.hoardFruits++;
                            }
                            actorMove.g_direction = (Direction.rotateDirection(
                                    actorMove.g_direction, ONE_EIGHTY_DEGREES));
                        } else if (actorMove.type.equals(Thief.TYPE)) {
                            if (actorMove.consuming) {
                                actorMove.consuming = false;
                                if (!actorMove.carrying) {
                                    if (actor.hoardFruits > 0) {
                                        actor.hoardFruits--;
                                        actorMove.carrying = true;
                                    } else {
                                        actorMove.t_direction = (Direction.rotateDirection(
                                                actorMove.t_direction, NINETY_DEGREES));

                                    }
                                }
                            } else if (actorMove.carrying) {
                                actorMove.carrying = false;
                                actor.hoardFruits++;
                                actorMove.t_direction = (Direction.rotateDirection(
                                        actorMove.t_direction, NINETY_DEGREES));
                            }
                        }
                        break;

                    //check if at sign
                    case Signs.UP:
                        if (actorMove.type.equals(Gatherer.TYPE)) {
                            actorMove.g_direction = Direction.UP;
                        } else if (actorMove.type.equals(Thief.TYPE)) {
                            actorMove.t_direction = Direction.UP;
                        }
                        break;
                    case Signs.LEFT:
                        if (actorMove.type.equals(Gatherer.TYPE)) {
                            actorMove.g_direction = Direction.LEFT;
                        } else if (actorMove.type.equals(Thief.TYPE)) {
                            actorMove.t_direction = Direction.LEFT;
                        }
                        break;
                    case Signs.DOWN:
                        if (actorMove.type.equals(Gatherer.TYPE)) {
                            actorMove.g_direction = Direction.DOWN;
                        } else if (actorMove.type.equals(Thief.TYPE)) {
                            actorMove.t_direction = Direction.DOWN;
                        }
                        break;
                    case Signs.RIGHT:
                        if (actorMove.type.equals(Gatherer.TYPE)) {
                            actorMove.g_direction = Direction.RIGHT;
                        } else if (actorMove.type.equals(Thief.TYPE)) {
                            actorMove.t_direction = Direction.RIGHT;
                        }
                        break;
                    //thief standing on pad
                    case Pads.TYPE:
                        if (actorMove.type.equals(Thief.TYPE)) {
                            actorMove.consuming = true;
                        }
                        break;
                    case Gatherer.TYPE:
                        if (actorMove.type.equals(Thief.TYPE)) {
                            actorMove.t_direction = (Direction.rotateDirection(
                                    actorMove.t_direction, TWO_SEVENTY_DEGREES));
                        }
                        break;
                }
            }
        }
        return true;
    }

    /**
     * This method is to update the actor's new move
     * @param actor Array list of actors
     * @param deltaX this parameter is add onto the actor's current location for up down
     * @param deltaY this parameter is add onto the actor's current location for left right
     */
     public void move(Actor[] actor,int deltaX, int deltaY,Actor actorMove) {
         if (checkLocation(actor,x+deltaX,y+deltaY,actorMove)){
             x += deltaX;
             y += deltaY;
         }


    }

    public abstract void update(Actor[] actor,Actor actorMove);
}
