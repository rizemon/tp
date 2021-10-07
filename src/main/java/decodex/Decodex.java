package decodex;


import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.parser.Parser;
import decodex.ui.Ui;

import java.util.Scanner;

public class Decodex {

    /**
     * Necessary objects to be initialized for Decodex to work properly.
     */
    private static Parser parser;

    /**
     * Logo to be changed - possibly to a welcome message instead for Decodex.
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Initializes the necessary Objects for Decodex.
     */
    public static void initDecodex() {
        parser = new Parser();
    }

    /**
     * Decodex entry-point for the java.decodex.Decodex application.
     */
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        System.out.println("Hello from\n" + LOGO);
        initDecodex();

        Scanner in = new Scanner(System.in);

        // Temporary code, command functions will be moved to parser
        Command command = null;

        do {
            System.out.print("Decodex > ");
            String userInput = in.nextLine();
            //String command = parser.getCommandType(userInput);
            //String userArgument = parser.getUserArgument(userInput);

            switch (userInput) {
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand(dataManager, moduleManager, ui);
                break;
            default:
                // Skeletal - Just "echos" back to us.
                System.out.println(userInput);
                continue;
            }

            command.run();

        } while (!(command instanceof ExitCommand));

        System.exit(0);
    }
}
