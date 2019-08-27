import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Event extends Task {

    protected String at, temp;
    protected Date dateInput;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.tag = 'E';
    }

    @Override
    public String toString() {

        //rewrite by string into DDth of MM YYYY, HHmm(am/pm)
        temp = at;
        if(at.isEmpty()){
            DukeException.emptyDateErr();
            return null;
        }
        else if(temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4}) ([0-9]{4})")){
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy, h:mma");
            try {
                dateInput = parser.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            at = " " + formatter.format(dateInput);
        }
        else if(temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")){
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy");
            try {
                dateInput = parser.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            at = " " + formatter.format(dateInput);
        }

        super.description = super.description + "(at:" + at + ")";
        return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
    }
}