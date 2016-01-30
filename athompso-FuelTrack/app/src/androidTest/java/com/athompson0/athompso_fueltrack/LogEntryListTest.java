package com.athompson0.athompso_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by athompso on 1/29/16.
 */
public class LogEntryListTest extends ActivityInstrumentationTestCase2 {
    public LogEntryListTest () {
        super(ListActivity.class);
    }

    public void testGetTotal() {
        LogEntryList logs = new LogEntryList();
        LogEntry log1 = new LogEntry("station", (float)1, "grade", (float)1, (float)1);
        LogEntry log2 = new LogEntry("station", (float)2, "grade", (float)2, (float)2);

        logs.add(log1);
        assertEquals(logs.getTotal(), log1.getTcost());

        logs.add(log1);
        assertEquals(logs.getTotal(), log1.getTcost() * 2);

        logs.add(log2);
        assertEquals(logs.getTotal(), (log1.getTcost() * 2) + log2.getTcost());
    }

    public void testSetLogs() {
        LogEntryList logs = new LogEntryList();
        LogEntry log1 = new LogEntry("station", (float)1, "grade", (float)1, (float)1);
        LogEntry log2 = new LogEntry("station", (float)2, "grade", (float)2, (float)2);
        ArrayList<LogEntry> logList = new ArrayList<LogEntry>();

        logs.add(log1);
        assertEquals(logs.getTotal(), log1.getTcost());

        logList.add(log1);
        logList.add(log2);

        logs.setLogs(logList);
        assertEquals(logs.getTotal(), log1.getTcost() + log2.getTcost());
    }

    public void testReplace() {
        LogEntryList logs = new LogEntryList();
        LogEntry log1 = new LogEntry("station", (float)1, "grade", (float)1, (float)1);
        LogEntry log2 = new LogEntry("station", (float)2, "grade", (float)2, (float)2);

        logs.add(log1);
        assertEquals(logs.getTotal(), log1.getTcost());

        logs.replace(log1, log2);
        assertEquals(logs.getTotal(), log2.getTcost());
    }
}
