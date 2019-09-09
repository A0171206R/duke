import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Handles Deadline Object
 */
public class Deadline extends Task {

    protected String by, temp;
    protected Date dateInput;

    /**
     * Takes in the String description of the Deadline Task and the String by which is the time or the date
     * @param description The description of the task
     * @param by The time / date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.tag = 'D';
    }

    /**
     *
     * @return a String formatted in the proper way
     */
    @Override
    public String toString() {
        //rewrite by string into DDth of MM YYYY, HHmm(am/pm)
        temp = by;
        if(by.isEmpty()){
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
            by = " " + formatter.format(dateInput);
        }
        else if(temp.matches(" ([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")){
            SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyy");
            try {
                dateInput = parser.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            by = " " + formatter.format(dateInput);
        }

        super.description = super.description + "(by:" + by + ")";
        return  "[" + super.tag + "]" + "[" + super.getStatusIcon() + "] "+ super.description;
    }
}
