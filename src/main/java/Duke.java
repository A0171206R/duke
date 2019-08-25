import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

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

    public void debug(String[] temp){
        for(int i = 0; i < temp.length; i++){
            System.out.println(temp[i]);
        }
    }

    static void printTaskList(ArrayList<Task> taskList) {
        printPartition();
        for(int i = 0; i < taskList.size(); i++){
            System.out.println(i+1 + ".[" + taskList.get(i).getTag()+ "]" + "[" + taskList.get(i).getStatusIcon()+ "] " + taskList.get(i).description);
        }
        printPartition();
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke d = new Duke();
        ArrayList<Task> taskList = new ArrayList<Task>();
        String input;

        String filepath = "C:\\duke.txt";
        File file = new File(filepath);
        FileReader fr = new FileReader(file);
        BufferedReader reader;
        Scanner s = new Scanner(System.in);

        /*Read file here and insert task objects into taskList first*/
        try{
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line != null){
                Task tSave = new Task(null);
                //System.out.println(line);
                String[] fileInputLine = line.split(" | ", 2);
                if(fileInputLine[0].equals("T")) {
                    String[] fileInputLineMore = fileInputLine[1].split(" | ", 4);
                    tSave =  new Task.Todo(fileInputLineMore[3]);
                    if(fileInputLineMore[1].equals("1")){
                        tSave.getItDone();
                    }
                    taskList.add(tSave);
                }
                else if(fileInputLine[0].equals("D")){
                    String[] fileInputLineMore = fileInputLine[1].split(" | ", 6);
                    //System.out.println(fileInputLineMore[5]);
                    tSave =  new Task.Deadline(fileInputLineMore[3] + " ", " "+ fileInputLineMore[5]);
                    tSave.toString();
                    if(fileInputLineMore[1].equals("1")){
                        tSave.getItDone();
                    }
                    taskList.add(tSave);

                }
                else if(fileInputLine[0].equals("E")){
                    String[] fileInputLineMore = fileInputLine[1].split(" | ", 6);
                    //System.out.println(fileInputLineMore[4]);
                    tSave =  new Task.Event(fileInputLineMore[3] + " ", " " + fileInputLineMore[5]);
                    tSave.toString();
                    if(fileInputLineMore[1].equals("1")){
                        tSave.getItDone();
                    }
                    taskList.add(tSave);

                }
                line = reader.readLine();
            }
            reader.close();
        }catch(Exception e){
            throw e;
            //throw new DukeException("File that is trying to be read does not exist! >:( ");
        }
        /**************************************END OF FILE READ******************************************/

        while(true) {

            String[] split_more = null;
            String command, description = null, after_command = null, by = null, at = null;
            Task t = new Task(description);

            input = s.nextLine();

            if (input.indexOf("todo") == 0 || input.indexOf("deadline") == 0 || input.indexOf("event") == 0 || input.indexOf("done") == 0) {
                try {
                    String[] token = input.split(" ", 2);
                    command = token[0];
                    after_command = token[1];
                }catch(Exception e){
                    throw new DukeException(" ☹ OOPS!!! The description of a " + input + " cannot be empty.");
                }
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
                    //write everything in taskList to file now
                    FileWriter fw = new FileWriter(file);
                    for(int i = 0; i < taskList.size(); i++){
                        char second_param = (taskList.get(i).isDone) ? '1' : '0';
                        char first_param = taskList.get(i).getTag();
                        String temp;
                        String third_param = null, fourth_param = null;
                        if(first_param == 'T'){
                            fw.write(first_param + " | " + second_param + " | " + taskList.get(i).description + "\r\n");
                            fw.flush();
                        }
                        else if(first_param == 'D'){
                            temp = taskList.get(i).description.replaceAll("\\p{P}", "");
                            third_param = temp.split("by", 2 )[0];
                            fourth_param = temp.split("by", 2)[1];
                            //fourth_param.replaceAll(" ", "");

                            fw.write(first_param + " | " + second_param + " | " + third_param + "|" + fourth_param + "\r\n");
                            fw.flush();
                        }
                        else if(first_param == 'E'){
                            temp = taskList.get(i).description.replaceAll("\\p{P}", "");
                            third_param = temp.split("at ", 2 )[0];
                            fourth_param = temp.split("at ", 2)[1];

                            fw.write(first_param + " | " + second_param + " | " + third_param + "| " + fourth_param + "\r\n");
                            fw.flush();
                        }
                    }
                    d.functionBye();
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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
            super.description = super.description + "(at:" + at + ")";
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
            super.description = super.description + "(by:" + by + ")";
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

class DukeException extends Exception{
    public DukeException(String errorMessage){
        super(errorMessage);
    }
}