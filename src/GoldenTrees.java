
public class GoldenTrees extends Actor {
    public static final String TYPE = "GoldenTree";

    /**
     * Constructor for the Golden Tree
     * @param x coordinate x of the Golden Tree
     * @param y coordinate y of the Golden Tree
     */
    public GoldenTrees(int x, int y) {
        super("res/images/gold-tree.png", TYPE, x, y);
    }
    @Override
    public void render(){
        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }
    /**update method of the golden tree
     *
     * @param actor array list of actors
     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}
}
