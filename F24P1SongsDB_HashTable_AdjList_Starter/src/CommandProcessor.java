import java.io.PrintWriter;
import java.util.Scanner;

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

    public CommandProcessor(Controller c) {
        controller = c;
        // hello this is adam
    }


    @Override
    public void interpretAllLines(Scanner input, PrintWriter output) {
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            // Skip over any blank input lines
            if (!line.isEmpty()) {
                this.interpretLine(new Scanner(line), output, input);
            }
        }
    }


    // this is where I have to print to output
    // ---------------------------------------------------------------
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
                String artist = oneLine.next();
                String song = oneLine.next();
                
                //how do I determine if the key was added or if the hash doubles in size
                //pass it to the controller and the hash to print
                //maybe pass it just to controller
                //Controller.insertArtist(String artist);
                //Controller.insertSong(String song);
                break;
            case "remove":
                // remove {artist|song}{name}
                String artistOrName = oneLine.next();
                // captures the remainder of the line
                String name = oneLine.findInLine(".*");
                switch (artistOrName) {
                    case "artist":
                        // controller.removeArtist(name);
                        break;
                    case "song":
                        // controller.removeSong(name);
                        break;
                }
                break;

            case "print":
                // print {artist|song|graph}
                switch (oneLine.next()) {
                    case "artist":
                        // controller.printArtist(PrintWriter output);
                        break;
                    case "song":
                        // controller.printSong(PrintWriter output);
                        break;
                    case "graph":
                        // controller.printGraph(PrintWriter output);
                        break;
                }
                break;
            default:
                // invalid command??
                break;
        }
    }

}
