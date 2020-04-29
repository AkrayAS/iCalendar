package app;

import junit.framework.TestCase;
import java.time.LocalDateTime;

public class VCalendarTest extends TestCase {

    public void testAddVEvent() {
        VCalendar vc = new VCalendar("\"-//Arthur A.//Blá Blá//PT_BR\"");
        LocalDateTime dtStart = LocalDateTime.of(2020, 04, 8, 13, 30, 00);
        LocalDateTime dtEnd = LocalDateTime.of(2020, 04, 8, 23, 59, 00);

        assertTrue("O valor deve ser true",vc.addVEvent("Projeto", dtStart, dtEnd, "Casa"));
    }

    public void testRemoveVEvent() {
        VCalendar vc = new VCalendar("\"-//Arthur A.//Blá Blá//PT_BR\"");
        LocalDateTime dtStart = LocalDateTime.of(2020, 04, 8, 13, 30, 00);
        LocalDateTime dtEnd = LocalDateTime.of(2020, 04, 8, 23, 59, 00);

        vc.addVEvent("Projeto", dtStart, dtEnd, "Casa");

        assertTrue("O valor deve ser true", vc.removeVEvent("Projeto"));
    }

    public void testUpdateVEvent() {
        VCalendar vc = new VCalendar("\"-//Arthur A.//Blá Blá//PT_BR\"");
        LocalDateTime dtStart = LocalDateTime.of(2020, 04, 8, 13, 30, 00);
        LocalDateTime dtEnd = LocalDateTime.of(2020, 04, 8, 23, 59, 00);

        vc.addVEvent("Projeto", dtStart, dtEnd, "Casa");

        assertTrue("O valor deve ser true", vc.updateVEvent("Projeto", "Projeto2",dtStart, dtEnd, "Casa"));
    }
}