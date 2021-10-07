package decodex;

import decodex.commands.Command;
import decodex.commands.DataCommand;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;
import java.util.Arrays;
import java.util.Scanner;

public class Decodex {

    /**
     * Logo to be changed - possibly to a welcome message instead for Decodex.
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Decodex entry-point for the java.decodex.Decodex application.
     */
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        System.out.println("Hello from\n" + LOGO);

        Scanner in = new Scanner(System.in);

        // Temporary code, command functions will be moved to parser
        Command command = null;

        do {
            System.out.print("Decodex > ");
            String userInput = in.nextLine();
            String[] tokens = userInput.split(" ");
            if (tokens.length < 1) {
                continue;
            }
            String[] arguments = Arrays.copyOfRange(tokens,1, tokens.length);

            switch (tokens[0]) {
            case DataCommand.COMMAND_WORD:
                command = new DataCommand(dataManager, moduleManager, ui, arguments);
                break;
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand(dataManager, moduleManager, ui);
                break;
            default:
                // Skeletal - Just "echos" back to us.
                System.out.println(userInput);
                continue;
            }
            try {
                command.run();
            } catch (CommandException err) {
                System.out.println(err.getMessage());
            }

        } while (!(command instanceof ExitCommand));

        System.exit(0);
    }
}
