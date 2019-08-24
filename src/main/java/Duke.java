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
        System.out.println("____________________________________________________________");
    }

    public void functionBye(){
        printPartition();
        System.out.println("Bye. Hope to see you again soon!");
        printPartition();
        System.exit(1);
    }


    static void printTaskList(ArrayList<Task> taskList) {
        printPartition();
        for(int i = 0; i < taskList.size(); i++){
            System.out.println(i+1 + ".[" + taskList.get(i).getTag()+ "]" + "[" + taskList.get(i).getStatusIcon()+ "] " + taskList.get(i).description);
        }
        printPartition();
    }

    public static void main(String[] args) {
        Duke d = new Duke();

        ArrayList<Task> taskList = new ArrayList<Task>();
        String input;

        Scanner s = new Scanner(System.in);

        while(true) {

            String[] split_more = null;
            String command, description = null, after_command = null, by = null, at = null;
            Task t = new Task(description);

            input = s.nextLine();
            if(input.indexOf("todo") == 0 || input.indexOf("deadline") == 0 || input.indexOf("event") == 0 || input.indexOf("done") == 0) {
                String[] token = input.split(" ", 2);
                command = token[0];
                after_command = token[1];
            }
            else{
                command = input;
            }

            switch (command) {

                case "list":
                    printTaskList(taskList);
                    break;
                case "todo":
                    description = after_command;
                    t = new Task.Todo(description);
                    taskList.add(t);
                    printPartition();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskList.size() + " in the list.");
                    printPartition();
                    break;
                case "deadline":
                    split_more = after_command.split("/by", 2);
                    description = split_more[0];
                    by = split_more[1];
                    t = new Task.Deadline(description, by);
                    taskList.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskList.size() + " in the list.");
                    printPartition();
                    break;
                case "event":
                    split_more = after_command.split("/at", 2);
                    description = split_more[0];
                    at = split_more[1];
                    t = new Task.Event(description, at);

                    taskList.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + taskList.size() + " in the list.");
                    printPartition();
                    break;
                case "done":
                    taskList.get(Integer.parseInt(after_command) - 1).getItDone();
                    printPartition();
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.println("["+taskList.get(Integer.parseInt(after_command) - 1).getStatusIcon() +"] " + taskList.get(Integer.parseInt(after_command) - 1).description);
                    printPartition();
                    break;
                case "bye":
                    d.functionBye();
                default:
                    break;
            }

        }
    }
}

class Task{

    protected String description;
    protected boolean isDone;
    protected char tag;

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
    public char getTag(){return tag;}

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
            super.tag = 'E';
        }

        @Override
        public String toString() {
            super.description = super.description + " (at: " + at + ")";
            return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
            super.tag = 'D';
        }

        @Override
        public String toString() {
            super.description = super.description + " (by: " + by + ")";
            return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
        }
    }
    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
            super.tag = 'T';
        }

        @Override
        public String toString() {
            return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
        }
    }
}