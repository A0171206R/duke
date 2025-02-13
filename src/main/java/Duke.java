import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

/**
 * Main class for duke
 */
public class Duke{

    public Duke() {
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


    /**
     * Prints the lines for partitioning IO
     */
    static void printPartition() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Terminates the program and saves the state
     */
    public void functionBye() {
        printPartition();
        System.out.println("Bye. Hope to see you again soon!");
        printPartition();
        System.exit(0);
    }

    public void debug(String[] temp) {
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }
    }

    /**
     * Prints the contents of the list of Task Objects
     * @param taskList An ArrayList that takes in Task Objects
     */
    static void printTaskList(ArrayList<Task> taskList) {
        printPartition();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ".[" + taskList.get(i).getTag() + "]" + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).description);
        }
        printPartition();
    }

    public static void main(String[] args) throws DukeException, ArrayIndexOutOfBoundsException, IOException {
        Duke d = new Duke();
        ArrayList<Task> taskList = new ArrayList<Task>();
        String input;
        BufferedReader reader = null;
        FileReader fr = null;
        File file = null;
        Scanner s = new Scanner(System.in);

        String filepath = "C:\\duke.txt";
        try {
            file = new File(filepath);
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            DukeException.fileNotFoundErr();
            //throw new DukeException("File that is trying to be read does not exist! >:( ");
        }

        /*Read file here and insert task objects into taskList first*/
        reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            Task tSave = new Task(null);
            //System.out.println(line);
            String[] fileInputLine = line.split(" | ", 2);
            if (fileInputLine[0].equals("T")) {
                String[] fileInputLineMore = fileInputLine[1].split(" | ", 4);
                tSave = new Todo(fileInputLineMore[3]);
                if (fileInputLineMore[1].equals("1")) {
                    tSave.getItDone();
                }
                taskList.add(tSave);
            } else if (fileInputLine[0].equals("D")) {
                String[] fileInputLineMore = fileInputLine[1].split(" | ", 6);
                //System.out.println(fileInputLineMore[5]);
                tSave = new Deadline(fileInputLineMore[3] + " ", " " + fileInputLineMore[5]);
                tSave.toString();
                if (fileInputLineMore[1].equals("1")) {
                    tSave.getItDone();
                }
                taskList.add(tSave);

            } else if (fileInputLine[0].equals("E")) {
                String[] fileInputLineMore = fileInputLine[1].split(" | ", 6);
                //System.out.println(fileInputLineMore[4]);
                tSave = new Event(fileInputLineMore[3] + " ", " " + fileInputLineMore[5]);
                tSave.toString();
                if (fileInputLineMore[1].equals("1")) {
                    tSave.getItDone();
                }
                taskList.add(tSave);

            }
            line = reader.readLine();
        }
        reader.close();

        /**************************************END OF FILE READ******************************************/

        while (true) {

            String[] split_more = null;
            String command = null, description = null, after_command = null, by = null, at = null;
            Task t = new Task(description);

            input = s.nextLine();
            try {
                if (input.indexOf("todo") == 0 || input.indexOf("deadline") == 0 || input.indexOf("event") == 0 || input.indexOf("done") == 0 || input.indexOf("delete") == 0 || input.indexOf("find") == 0) {
                    String[] token = input.split(" ", 2);
                    command = token[0];
                    after_command = token[1];
                } else {
                    command = input;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                printPartition();
                DukeException.commandNotValid();
                printPartition();
                command = "";
            }
            /****************************************RUNS THE COMMAND INPUT BY USER***********************************/
            switch (command) {
                case "list":
                    printTaskList(taskList);
                    break;
                case "todo":
                    description = after_command;
                    t = new Todo(description);
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
                    if(!by.isEmpty()) {
                        t = new Deadline(description, by);
                        taskList.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + taskList.size() + " in the list.");
                        printPartition();
                    }else{
                        printPartition();
                        DukeException.emptyDateErr();
                        printPartition();
                    }
                    break;
                case "event":
                    split_more = after_command.split("/at", 2);
                    description = split_more[0];
                    at = split_more[1];
                    if(!at.isEmpty()) {
                        t = new Event(description, at);

                        taskList.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                        System.out.println("Now you have " + taskList.size() + " in the list.");
                        printPartition();
                    }
                    else{
                        printPartition();
                        DukeException.emptyDateErr();
                        printPartition();
                    }
                    break;
                case "done":
                    taskList.get(Integer.parseInt(after_command) - 1).getItDone();
                    printPartition();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + taskList.get(Integer.parseInt(after_command) - 1).getStatusIcon() + "] " + taskList.get(Integer.parseInt(after_command) - 1).description);
                    printPartition();
                    break;
                case "delete":
                    printPartition();
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("[" + taskList.get(Integer.parseInt(after_command) - 1).getTag() + "]" + "[" + taskList.get(Integer.parseInt(after_command) - 1).getStatusIcon() + "] " + taskList.get(Integer.parseInt(after_command) - 1).description);
                    printPartition();
                    taskList.remove(Integer.parseInt(after_command) -1 );
                    break;
                case "find":
                    int x = 1;
                    printPartition();
                    System.out.println("here are the matching tasks in your list:");
                    for(int j = 0; j < taskList.size(); j++){
                        if(taskList.get(j).description.contains(after_command) ){
                            System.out.println(x + ".[" + taskList.get(j).getTag() + "]" + "[" + taskList.get(j).getStatusIcon() + "] " + taskList.get(j).description);
                            x++;
                        }
                    }
                    printPartition();
                    break;
                case "":
                    //handles the commands without arguments
                    break;
                case "bye":
                    //write everything in taskList to file now
                    FileWriter fw = new FileWriter(file);
                    for (int i = 0; i < taskList.size(); i++) {
                        char second_param = (taskList.get(i).isDone) ? '1' : '0';
                        char first_param = taskList.get(i).getTag();
                        String temp;
                        String third_param = null, fourth_param = null;
                        if (first_param == 'T') {
                            fw.write(first_param + " | " + second_param + " | " + taskList.get(i).description + "\r\n");
                            fw.flush();
                        } else if (first_param == 'D') {
                            //System.out.println(taskList.get(i).description);
                            temp = taskList.get(i).description.replaceAll("\\(", "").replaceAll("\\)", "");
                            //System.out.println("After: " + temp);
                            third_param = temp.split("by:", 2)[0];
                            fourth_param = temp.split("by:", 2)[1];

                            fw.write(first_param + " | " + second_param + " | " + third_param + "|" + fourth_param + "\r\n");
                            fw.flush();
                        } else if (first_param == 'E') {
                            temp = taskList.get(i).description.replaceAll("\\(", "").replaceAll("\\)", "");
                            third_param = temp.split("at: ", 2)[0];
                            fourth_param = temp.split("at: ", 2)[1];

                            fw.write(first_param + " | " + second_param + " | " + third_param + "| " + fourth_param + "\r\n");
                            fw.flush();
                        }
                    }
                    d.functionBye();
                    break;
                default:
                    printPartition();
                    DukeException.commandNotValid();
                    printPartition();
            }
        }
    }
}
