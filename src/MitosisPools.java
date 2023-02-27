

public class MitosisPools extends Actor{
    public static final String TYPE = "Pool";

    /**
     * Constructor for mitosis pool
     * @param x x coordinate of the mitosis pool
     * @param y y coordinate of the mitosis pool
     */
    public MitosisPools(int x, int y) {
        super("res/images/pool.png", TYPE, x, y);
    }

    @Override
    public void render(){

        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }
    /**
     * update method for the actor
     * @param actor Array list of actor

     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}

}
