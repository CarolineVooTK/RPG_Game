

public class Signs extends Actor{
    /**
     * Static parameters for different signs
     */
    public static final String UP = "SignUp";
    public static final String DOWN = "SignDown";
    public static final String LEFT = "SignLeft";
    public static final String RIGHT = "SignRight";


    /**
     * Constructor for the signs
     * @param x x coordinate of the sign
     * @param y y coordinate of the sign
     */
    public Signs(String type,int x, int y){
        super("res/images/"+type+".png", type, x, y);
    }

    @Override
    public void render(){
        super.getImage().drawFromTopLeft(super.getX(), super.getY());

    }
    /**update method of the actor
     *
     * @param actor array list of actors

     */
    @Override
    public void update(Actor[] actor,Actor actorMove) {}
}
