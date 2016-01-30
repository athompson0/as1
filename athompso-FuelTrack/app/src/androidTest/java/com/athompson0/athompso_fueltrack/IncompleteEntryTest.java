package com.athompson0.athompso_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by athompso on 1/29/16.
 */
public class IncompleteEntryTest extends ActivityInstrumentationTestCase2 {
    public IncompleteEntryTest() {
        super(NewLogActivity.class);
    }

    public void testIsComplete() {
        IncompleteEntry entry = new IncompleteEntry();

        entry.setStation("station");
        assertFalse(entry.isComplete());

        entry.setOdometer((float) 12.0);
        assertFalse(entry.isComplete());

        entry.setGrade("grade");
        assertFalse(entry.isComplete());

        entry.setAmount((float) 12.0);
        assertFalse(entry.isComplete());

        entry.setUcost((float) 12.0);
        assertTrue(entry.isComplete());
    }

    public void testCreateLogEntry() {
        IncompleteEntry entry = new IncompleteEntry();
        entry.setStation("station");
        entry.setOdometer((float) 12.0);
        entry.setGrade("grade");
        entry.setAmount((float) 12.0);
        entry.setUcost((float) 12.0);

        LogEntry log = entry.createLogEntry();

        assertEquals(log.getStation(),"station");
        assertEquals(log.getOdometer(), (float)12.0);
        assertEquals(log.getGrade(),"grade");
        assertEquals(log.getAmount(),(float)12.0);
        assertEquals(log.getUcost(),(float)12.0);
    }
}
