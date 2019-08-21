import java.util.Scanner;

public class Duke {

    public Duke(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________\n");
    }

    static void printPartition() {
        System.out.println("____________________________________________________________\n");
    }

    public void functionBye(){
        printPartition();
        System.out.println("Bye. Hope to see you again soon!\n");
        printPartition();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while(!(input.equals("bye"))) {
            printPartition();
            System.out.println(input);
            printPartition();
            input = s.nextLine();
        }
        d.functionBye();
    }
}
