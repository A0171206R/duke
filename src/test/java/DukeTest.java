import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains test cases for debugging Duke
 */
public class DukeTest {

    private String testString = null;

    @Test
    public void TodoTest(){
        Task t = new Todo("eat sleep play");
        assertEquals("[T][\u2718] eat sleep play", t.toString());
    }
    @Test
    public void DeadlineTest(){
        Task t = new Deadline("project", " 3/3/2019");
        assertEquals("[D][\u2718] project(by: 03 Mar 2019)", t.toString());
    }
    @Test
    public void EventTest(){
        Task t = new Event("wedding", " 1/2/2019 1500");
        assertEquals("[E][\u2718] wedding(at: 01 Feb 2019, 3:00PM)", t.toString());
    }
}