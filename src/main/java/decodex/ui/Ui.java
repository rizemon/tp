package decodex.ui;

import decodex.ui.messages.AssertMessages;
import decodex.ui.messages.RegularMessages;
import java.util.Scanner;

/**
 * The Ui class handles all user input and message output ot the console.
 */
public class Ui {

    private static final String PROMPT_HEADER = "Decodex > ";
    private static final String SUCCESS_ICON = "[+]";
    private static final String ERROR_ICON = "[x]";
    private static final String INPUT_PREFIX = "Input:";
    private static final String OUTPUT_PREFIX = "Output:";

    public Ui() {
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        System.out.println(RegularMessages.GREETING);
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        System.out.println(RegularMessages.GOODBYE);
    }

    /**
     * Reads user input from console.
     *
     * @param in Scanner object.
     * @return String of the user input.
     */
    public String readInput(Scanner in) {
        printPromptHeader();
        return in.nextLine();
    }

    /**
     * Prints the read user input.
     *
     * @param input The user input read.
     */
    public void printInput(String input) {
        assert input != null : AssertMessages.INPUT_NOT_NULL;
        assert !input.isBlank() : AssertMessages.INPUT_NOT_EMPTY;
        printSuccess(String.format("%s \"%s\"", INPUT_PREFIX, input));
    }

    /**
     * Prints the output after data manipulation.
     *
     * @param output String output of the data.
     */
    public void printOutput(String output) {
        assert output != null : AssertMessages.OUTPUT_NOT_NULL;
        printSuccess(String.format("%s \"%s\"", OUTPUT_PREFIX, output));
    }

    /**
     * Prints the list of available modules.
     *
     * @param moduleList String of the list of available modules.
     */
    public void printModuleList(String moduleList) {
        assert moduleList != null : AssertMessages.MODULE_LIST_NOT_NULL;
        assert !moduleList.isBlank() : AssertMessages.MODULE_LIST_NOT_EMPTY;
        System.out.println(RegularMessages.LIST_MODULES + "\n" + moduleList);
    }

    /**
     * Prints a message with a success status icon.
     *
     * @param message Success message
     */
    public void printSuccess(String message) {
        assert message != null : AssertMessages.MESSAGE_NOT_NULL;
        assert !message.isBlank() : AssertMessages.MESSAGE_NOT_EMPTY;
        System.out.println(SUCCESS_ICON + " " + message);
    }

    /**
     * Prints the error message from the thrown exception with an error icon.
     *
     * @param exception The thrown exception.
     */
    public void printError(Exception exception) {
        assert exception.getMessage() != null : AssertMessages.EXCEPTION_MESSAGE_NOT_NULL;
        assert !exception.getMessage().isBlank() : AssertMessages.EXCEPTION_MESSAGE_NOT_EMPTY;
        System.out.println(ERROR_ICON + " " + exception.getMessage());
    }

    /**
     * Prints the prompt header.
     */
    private void printPromptHeader() {
        System.out.print(PROMPT_HEADER);
    }
}
