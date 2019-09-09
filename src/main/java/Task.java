/**
 * Super class of specific tasks like Todo, Deadline and Event
 */

public class Task{

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

}

