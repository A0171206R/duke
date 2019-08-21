import java.util.Scanner;
import java.lang.String;

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


    static void printHistory(String[] history, int index) {
        for(int i = 0; i < index; i++){
            System.out.println(i+1 + ". " + history[i] + "\n");
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        String[] history = new String[100];
        int index = 0;
        String input;

        Scanner s = new Scanner(System.in);
        input = s.nextLine();

        while(!(input.equals("bye"))) {
            if(!(input.equals("list"))) {
                printPartition();
                System.out.println("added: " + input + "\n");
                printPartition();
                history[index] = input;
                index++;
                input = s.nextLine();
            }
            else {
                printPartition();
                printHistory(history, index);
                printPartition();
                input = s.nextLine();
            }
        }
        d.functionBye();
    }
}