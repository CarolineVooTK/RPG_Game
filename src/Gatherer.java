

/** Code taken from sample solution for Project 1
 *
 */

public class Gatherer extends Actor {
    public static final String TYPE = "Gatherer";
    protected static boolean active;
    protected static int direction;
    protected static boolean carrying;


    /**
     * Constructor for gatherer
     * @param x coordinate x of the gatherer
     * @param y coordinate y of the gatherer
     */
    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        this.active = super.active;
        this.direction = super.g_direction;
        this.carrying = super.carrying;

    }
    @Override
    public void render(){
        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }

    /**
     * Update method for the gatherer and the direction it should move at if active
     * @param actor array list of actor used to check location
     */
     @Override
     public void update(Actor[] actor,Actor actorMove) {
        if (super.active) {
            switch (super.g_direction) {
                case Direction.UP:
                    move(actor, 0, -ShadowLife.TILE_SIZE,actorMove);
                    break;
                case Direction.DOWN:
                    move(actor, 0, ShadowLife.TILE_SIZE,actorMove);
                    break;
                case Direction.LEFT:
                    move(actor, -ShadowLife.TILE_SIZE, 0,actorMove);
                    break;
                case Direction.RIGHT:
                    move(actor, ShadowLife.TILE_SIZE, 0,actorMove);
                    break;
            }
        }

    }
}
