import java.io.PrintWriter;
import java.util.Scanner;

public class CommandProcessor implements Interpreter {

    // implement interpreter
    // read in commands
    // has controller object and calls methods on it

    // commands are insert, remove, print
    private Controller controller;
    
    public CommandProcessor(Controller c) {
        controller = c;
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


    @Override
    public void interpretLine(
        Scanner oneLine,
        PrintWriter output,
        Scanner remainingInputLines) {
        // TODO Auto-generated method stub

    }

}
