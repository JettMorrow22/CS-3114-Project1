import java.io.PrintWriter;
import java.util.Scanner;

/**
 * CommandProcessor class that implements Interpreter
 * CP reads lines from the input file and calls appriopriate method on
 * Controller
 * 
 * We got InterpretAllLines from OPENDSA 2.7
 * 
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class CommandProcessor implements Interpreter {

    // implement interpreter
    // read in commands
    // has controller object and calls methods on it

    // commands are insert, remove, print
    private Controller controller;

    // I have to print in this file
    // print |name| is added to blank when we add sometihng to a hashTable
    // print if we double size of hash

    // remove
    // print if key is found and removed or not found

    /**
     * General constructor for CP
     * 
     * @param c
     *            the controller
     */
    public CommandProcessor(Controller c) {
        controller = c;
    }


    /**
     * method to interprete one line at a time
     * 
     * @param input
     *            the scanner
     * @param output
     *            the output
     */
    @Override
    public void interpretAllLines(Scanner input, PrintWriter output) {
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            // Skip over any blank input lines
            if (!line.isEmpty()) {
                this.interpretLine(new Scanner(line), output, input);
            }
        }
        output.close();
    }


    /**
     * Where each line is procesessed
     * the first word determines the main command which determines what the next
     * possible words could be and mean, all input is send to controller with
     * approiorate methods
     * 
     * @param oneLine
     *            scanner object
     * @param output
     *            our output function
     * @param remainingInputLines
     *            the remainder of the input file
     */
    @Override
    public void interpretLine(
        Scanner oneLine,
        PrintWriter output,
        Scanner remainingInputLines) {

        // we first have to process the first word (insert, remove, print)
        // depending on first word they have dif following input
        String command = oneLine.next();

        switch (command) {
            case "insert":
                // insert {artist-name}<SEP>{song-name}
                oneLine.useDelimiter("<SEP>");
                String artist = oneLine.next().trim();
                String song = oneLine.next().trim();
                controller.insert(output, artist, song);
                break;
            case "remove":
                // remove {artist|song}{name}
                String artistOrName = oneLine.next();
                // captures the remainder of the line
                String name = oneLine.nextLine().trim();
                switch (artistOrName) {
                    case "artist":
                        controller.removeArtist(output, name);
                        break;
                    case "song":
                        controller.removeSong(output, name);
                        break;
                }
                break;

            case "print":
                // print {artist|song|graph}
                switch (oneLine.next()) {
                    case "artist":
                        controller.printArtist(output);
                        break;
                    case "song":
                        controller.printSong(output);
                        break;
// case "graph":
// // controller.printGraph(PrintWriter output);
// break;
                }
                break;
        }
        output.flush();
    }

}
