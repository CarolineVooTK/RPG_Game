import bagel.Font;

public class Hoards extends Actor {
    public static final String TYPE = "Hoard";
    protected static int hoardFruits;

    /** Constructor for Hoards
     *
     * @param x x coordinate of the hoard
     * @param y y coordinate of the hoard
     */
    public Hoards(int x, int y) {

        super("res/images/hoard.png", TYPE, x, y);
        this.hoardFruits = super.hoardFruits;
    }
    @Override
    public void render(){
        Font font = new Font("res/VeraMono.ttf", 20);
        font.drawString(Integer.toString(super.hoardFruits), super.getX(), super.getY());

        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }

    /**
     * update method for the actor
     * @param actor Array list of actor
     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}
}
