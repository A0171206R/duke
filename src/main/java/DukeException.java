class DukeException extends Exception {
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
<<<<<<< HEAD
=======

>>>>>>> 6a5a1b66c37e042b2b50f5fd21dd64a703fc7864
