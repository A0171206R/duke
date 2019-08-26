public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.tag = 'T';
    }

    @Override
    public String toString() {
        return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
    }
}