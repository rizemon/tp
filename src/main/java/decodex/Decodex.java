package decodex;

import decodex.parser.Parser;

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
     * Command Prefix Constants.
     */
    private static final String EXIT_COMMAND = "exit";

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
        System.out.println("Hello from\n" + LOGO);
        initDecodex();

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Decodex > ");
            String userInput = in.nextLine();
            String command = parser.getCommandType(userInput);
            String userArgument = parser.getUserArgument(userInput);

            switch (command) {
            case EXIT_COMMAND:
                System.out.print("Goodbye!");
                System.exit(0);
                break;
            default:
                // Skeletal - Just "echos" back to us.
                System.out.println(userInput);
            }
        }
    }
}
