class Event extends Task {

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