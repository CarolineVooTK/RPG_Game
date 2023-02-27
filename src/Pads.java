

public class Pads extends Actor{
    public static final String TYPE = "Pad";

    /**
     * Constructor for the pad
     * @param x x coordinate of the pad
     * @param y y coordinate of the pad
     */
    public Pads(int x, int y) {
        super("res/images/pad.png", TYPE, x, y);
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
