import bagel.Font;

/** Code taken from sample solution for Project 1
 *
 */

public class Tree extends Actor {
    public static final String TYPE = "Tree";
    protected static int treeFruit;

    /**
     * Constructor for the Tree
     * @param x coordinate x of the Tree
     * @param y coordinate y of the Tree
     */
    public Tree(int x, int y) {

        super("res/images/tree.png", TYPE, x, y);
        this.treeFruit = super.treeFruit;
    }


    @Override
    public void render(){
        Font font = new Font("res/VeraMono.ttf", 20);
        font.drawString(Integer.toString(super.treeFruit), super.getX(), super.getY());

        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }


    /**
     * Update method for the actor
     * @param actor array list of actor used to check location if non static
     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}
}
