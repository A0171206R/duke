public class Deadline extends Task {

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