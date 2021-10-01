package seedu.duke;

import java.util.Scanner;

public class Duke {
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
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Decodex > ");
            String userInput = in.nextLine();

            switch (userInput) {
            case EXIT_COMMAND:
                System.out.println("Goodbye!");
                break;
            default:
                // Skeletal - Just "echos" back to us.
                System.out.println(userInput);
            }
        }
    }
}
