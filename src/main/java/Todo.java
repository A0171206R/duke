/**
 * Handles Todo Object
 */
public class Todo extends Task {

    /**
     * Takes in a String description of the Todo task
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
        super.tag = 'T';
    }

    @Override
    public String toString() {
        return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
    }
}