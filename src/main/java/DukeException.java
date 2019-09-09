/**
 * DukeException class is a custom exception class
 * that handles errors specifically to duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public static void fileNotFoundErr(){
        System.out.println("File that is trying to be read does not exist! >:( ");
    }

    public static void emptyDateErr(){
        System.out.println("OOPS!! Date parameter cannot be empty!");
    }

    public static void commandNotValid(){
        System.out.println("OOPS!! The command you entered must have arguments!");
    }

}

