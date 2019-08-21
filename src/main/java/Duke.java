import java.util.Scanner;
import java.util.ArrayList;
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


    static void printTaskList(ArrayList<Task> taskList) {
        for(int i = 0; i < taskList.size(); i++){
            System.out.println(i+1 + ".[" + taskList.get(i).getStatusIcon()+"] " + taskList.get(i).description + "\n");
        }
    }


    public static void main(String[] args) {
        Duke d = new Duke();
        ArrayList<String> history = new ArrayList<String>();
        ArrayList<Task> taskList = new ArrayList<Task>();
        int index = 0;
        String input;

        Scanner s = new Scanner(System.in);
        input = s.nextLine();

        while(!(input.equals("bye"))) {
            if((input.equals("list"))) {
                printPartition();
                printTaskList(taskList);
                printPartition();
                input = s.nextLine();
            } else if (input.indexOf("done") == 0) {
                String[] token = input.split(" ");
                /*System.out.println("Checking tokens...\n");
                for(int i = 0 ; i < token.length; i++){
                    System.out.println(token[i]+"\n");
                }*/
                taskList.get(Integer.parseInt(token[1]) - 1).getItDone();
                printPartition();
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("["+taskList.get(Integer.parseInt(token[1]) - 1).getStatusIcon() +"] " + taskList.get(Integer.parseInt(token[1]) - 1).description + "\n");
                printPartition();
                input = s.nextLine();
            } else {
                printPartition();
                System.out.println("added: " + input + "\n");
                printPartition();
                Task t = new Task(input);
                taskList.add(t);
                input = s.nextLine();
            }

        }
        d.functionBye();
    }
}

class Task{
    public String descrition;
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public void getItDone(){
        isDone = true;
    }
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }
}