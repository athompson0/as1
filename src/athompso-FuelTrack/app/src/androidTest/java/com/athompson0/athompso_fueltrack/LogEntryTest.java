package com.athompson0.athompso_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by athompso on 1/31/16.
 */
public class LogEntryTest extends ActivityInstrumentationTestCase2{
    public LogEntryTest() {
        super(ListActivity.class);
    }

    public void testSetAmount() {
        LogEntry log = new LogEntry(new Date(), "station", (float)1, "grade", (float)1, (float)1);
        assertEquals(log.getTcost(), (float)1 * ((float)1/100));

        log.setAmount((float) 2);
        assertEquals(log.getTcost(), (float)2 * ((float)1/100));
    }

    public void testSetUcost() {
        LogEntry log = new LogEntry(new Date(), "station", (float)1, "grade", (float)1, (float)1);
        assertEquals(log.getTcost(), (float)1 * ((float)1/100));

        log.setUcost((float)2);
        assertEquals(log.getTcost(), (float)1 * ((float)2/100));
    }
}
