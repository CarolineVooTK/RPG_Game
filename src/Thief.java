
public class Thief extends Actor{
    public static final String TYPE = "Thief";
    protected static int direction;
    protected static boolean active;
    protected static boolean carrying;

    protected static boolean consuming;

    /**
     *
     * @param x the x coordinate of the actor
     * @param y the y coordinate of the actor
     */
    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        this.active = super.active;
        this.direction = super.t_direction;
        this.carrying = super.carrying;
        this.consuming= super.consuming;
    }
    @Override
    public void render(){
        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }


    /**
     *
     * @param actor this is the array of all the actors that is used to check if the actor stepped
     *              on a location that changes its direction or stop the program

     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {
        if (super.active) {
            switch (super.t_direction) {
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
