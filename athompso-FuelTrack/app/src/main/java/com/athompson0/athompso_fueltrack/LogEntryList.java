package com.athompson0.athompso_fueltrack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by athompso on 1/29/16.
 */
public class LogEntryList implements Serializable {
    private ArrayList<LogEntry> logs;
    private float total;

    public LogEntryList() {
        logs = new ArrayList<LogEntry>();
        total = 0;
    }

    public void add(LogEntry log) {
        logs.add(log);
        total += log.getTcost();
    }

    public float getTotal() {
        return total;
    }

    public void replace(LogEntry oldLog, LogEntry newLog) {
        int index = logs.indexOf(oldLog);
        logs.remove(index);
        logs.add(index, newLog);
    }
}
