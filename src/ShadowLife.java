/** Code taken from sample solution for Project 1
 *
 */

import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.String;


public class ShadowLife extends AbstractGame {
    /**
     * Commands for ending the program and so that there is no magic numbers
     */
    public static final String COMMAND_ERROR = "usage: ShadowLife <tick rate> <max ticks> <world file>";
    public static final int MAX_COMMAND = 3;
    public static final int TILE_SIZE = 64;

    public static final String PROGRAM_END = "Timed out";
    private final int tickRate;
    private final int maxTicks ;
    private final String file;
    private static long lastTick = 0;

    private Actor[] actors;
    private int numActors = 0;


    private static int ticks = 0;

    private final Image background = new Image("res/images/background.png");

    /** This method is used to load all the Actors in the csv file into an Array
     *
     */
    private void loadActors() {
        int count = 0;


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int size = (int) Files.lines(Path.of(file)).count();
            actors = new Actor[size];

            String line;
            while ((line = reader.readLine()) != null) {
                // Line format is: type,x,y
                String[] parts = line.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                switch (type) {
                    case Tree.TYPE:
                        actors[count++] = new Tree(x, y);
                        break;
                    case Gatherer.TYPE:
                        actors[count++] = new Gatherer(x, y);
                        numActors++;
                        break;
                    case Fences.TYPE:
                        actors[count++] = new Fences(x,y);
                        break;
                    case Stockpiles.TYPE:
                        actors[count++] = new Stockpiles(x,y);
                        break;
                    case Hoards.TYPE:
                        actors[count++] = new Hoards(x,y);
                        break;
                    case Thief.TYPE:
                        actors[count++] = new Thief(x,y);
                        numActors++;
                        break;
                    case Signs.UP:
                        actors[count++] = new Signs(Signs.UP,x,y);
                        break;
                    case Signs.DOWN:
                        actors[count++] = new Signs(Signs.DOWN,x,y);
                        break;
                    case Signs.LEFT:
                        actors[count++] = new Signs(Signs.LEFT,x,y);
                        break;
                    case Signs.RIGHT:
                        actors[count++] = new Signs(Signs.RIGHT,x,y);
                        break;
                    case MitosisPools.TYPE:
                        actors[count++] = new MitosisPools(x,y);
                        break;
                    case Pads.TYPE:
                        actors[count++] = new Pads(x,y);
                        break;
                    case GoldenTrees.TYPE:
                        actors[count++] = new GoldenTrees(x,y);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("error: file <"+file+"> not found");
            System.exit(-1);
        }
    }

    /** This function is to store the information given from the command line into
     * the program
     *
     * @param tickRate This is the first parameter is the millisecond we need to wait until we move our
     *                 actor
     * @param maxTicks This is the maximum moves we can make for the actors
     * @param file This is the file given to read where and which actor will be in the image
     */
    public ShadowLife(int tickRate,int maxTicks,String file) {
        this.tickRate = tickRate;
        this.maxTicks = maxTicks;
        this.file = file;
        loadActors();
    }

    /**
     * This is the update method for our actors to move per tick and make sure
     * the actors would stop moving when reaching the give maximum tick time
     */
    @Override
    protected void update(Input input) {
        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > tickRate) {
            lastTick = System.currentTimeMillis();
            for (Actor actor : actors) {
                if (actor != null) {
                    actor.tick(actors,ticks,numActors,actor);
                }
            }
            if (ticks == maxTicks){
                System.out.println(PROGRAM_END);
                System.exit(-1);
            }
            else{
                ticks++;
            }
        }

        // Draw all elements
        background.drawFromTopLeft(0, 0);

        for (Actor actor : actors) {
            if (actor != null) {
                actor.render();
            }
        }
    }

    /**
     * This is the main method that runs the ShadowLife program and reads the command line
     */
    public static void main(String[] args) {
        int tickRate;
        int maxTicks;
        String file;
        if  (args.length < MAX_COMMAND) {
            System.out.println(COMMAND_ERROR);
            System.exit(-1);
        }
        else{
            tickRate = Integer.parseInt(args[0]);
            maxTicks = Integer.parseInt(args[1]);
            file = args[2];
            if (tickRate < 0 && maxTicks < 0){
                System.out.println(COMMAND_ERROR);
                System.exit(-1);
            }
            else {
                new ShadowLife(tickRate, maxTicks, file).run();
            }
        }
    }
}
