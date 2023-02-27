import bagel.Font;

public class Stockpiles extends Actor{
    public static final String TYPE = "Stockpile";
    protected static int pileFruit;

    /**
     * Constructor for the stockpile
     * @param x coordinate x of the stockpile
     * @param y coordinate y of the stockpile
     */
    public Stockpiles(int x, int y) {

        super("res/images/cherries.png", TYPE, x, y);
        this.pileFruit = super.pileFruit;
    }

    @Override
    public void render(){
        Font font = new Font("res/VeraMono.ttf", 20);
        font.drawString(Integer.toString(super.pileFruit), super.getX(), super.getY());

        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }

    /**update method of the actor
     *
     * @param actor array list of actors
     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}
}

