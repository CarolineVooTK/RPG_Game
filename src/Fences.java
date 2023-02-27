import bagel.Font;

public class Fences extends Actor{
    public static final String TYPE = "Fence";
    public int x;
    public int y;

    /**
     * Constructor for the fence and where its located
     * @param x coordinate x of the fence
     * @param y coordinate y of the fence
     */
    public Fences(int x, int y) {
        super("res/images/fence.png", TYPE, x, y);
    }

    @Override
    public void render(){
        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }

    /**
     * the update method for the fence
     * @param actor Array list of the actors in the program
     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}
}
