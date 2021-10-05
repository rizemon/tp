package decodex;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;
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

        boolean isExit = false;

        while (!isExit) {
            System.out.print("Decodex > ");
            String userInput = in.nextLine();

            // Temporary code, command functions will be moved to parser
            Command command = null;

            switch (userInput) {
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand();
                break;
            default:
                // Skeletal - Just "echos" back to us.
                System.out.println(userInput);
            }

            if (command != null){
                command.run(dataManager, moduleManager, ui);
                isExit = command.isExit();
            }
        }

        System.out.print("Goodbye!");
        System.exit(0);
    }
}
